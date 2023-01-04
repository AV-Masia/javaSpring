package fs.spring.vue.controller;

import fs.spring.vue.model.Role;
import fs.spring.vue.model.User;
import fs.spring.vue.model.form.RegistrationForm;
import fs.spring.vue.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(JUnitParamsRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class UserControllerTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter springSecurityFilterChain;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .addFilter(springSecurityFilterChain)
                .dispatchOptions(true).build();
        MockitoAnnotations.openMocks(this);

    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    private static class TestDataStorage {
        User user1 = User.builder().id(1L).role(Role.ADMIN).email("yakusik@mail.ru").password("111")
                .firstName("Anastasia").lastName("Vasko")
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
        User user2 = User.builder().id(2L).role(Role.USER).email("petya@gmail.com").password("ytrewq")
                .firstName("Alena").lastName("Gurkova")
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();

        RegistrationForm registrationFormUser1 = RegistrationForm.builder().id(1L)
                .email("yakusik@mail.ru")
                .firstName("Anastasia")
                .lastName("Vasko")
                .password("")
                .confirmPassword("")
                .build();
        RegistrationForm registrationFormUser2 = RegistrationForm.builder().id(2L)
                .email("petya@gmail.com")
                .firstName("Alena")
                .lastName("Gurkova")
                .password("ytrewq")
                .confirmPassword("ytrewq")
                .build();
        RegistrationForm registrationFormUser3 = RegistrationForm.builder().id(2L)
                .email("")
                .firstName("")
                .lastName("")
                .password("")
                .confirmPassword("")
                .build();
    }

    public static final TestDataStorage testDataStorage = new TestDataStorage();


    @Parameterized.Parameters
    private Object[] paramsEmailUserAndEntityUser() {
        return new Object[][]{
                {"yakusik@mail.ru", testDataStorage.registrationFormUser1}
//                {"petya@gmail.com", testDataStorage.registrationFormUser2}
        };
    }

    @Parameterized.Parameters
    private Object[] paramsUpdateUser() {
        return new Object[][]{
                {testDataStorage.registrationFormUser1, testDataStorage.user1},
                {testDataStorage.registrationFormUser2, testDataStorage.user2}
        };
    }


    @Test
    @Parameters(method = "paramsEmailUserAndEntityUser")
    public void verifyGetUserByContext(String email, RegistrationForm expectedRF) throws Exception{
        when(userService.getUserByEmail(email)).thenReturn(testDataStorage.user1);
        ResultActions resultActions = mockMvc.perform(
                        get("/user.html")
                .with(SecurityRequestPostProcessors.userDetailsService(email))
                )
                .andDo(print())
                .andExpect(model().attributeExists("registrationForm"));

        RegistrationForm actualRF = (RegistrationForm) Objects.requireNonNull(resultActions.andReturn()
                .getModelAndView()).getModel().get("registrationForm");

        verify(userService, times(1)).getUserByEmail(anyString());
        assertEquals(actualRF, expectedRF);
    }

    @Test
    public void verifyDeleteUser() throws Exception {
        long id = 5L;
        doNothing().when(userService).deleteUserById(id);

        mockMvc.perform(
                        delete("/deleteUser").param("id", String.valueOf(id)))
                .andExpect(status().is3xxRedirection());

        verify(userService, times(1)).deleteUserById(id);
    }

    @Test
    @Parameters(method = "paramsUpdateUser")
    public void verifyUpdateUser_status200(RegistrationForm registrationForm, User user) throws Exception {
        doNothing().when(userService).updateUser(registrationForm);
        when(userService.getUserByEmail(registrationForm.getEmail())).thenReturn(user);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/user.html")
                        .param("id", registrationForm.getId().toString())
                        .param("firstName",registrationForm.getFirstName())
                        .param("lastName",registrationForm.getLastName())
                        .param("email",registrationForm.getEmail())
                        .param("password",registrationForm.getPassword())
                        .param("confirmPassword",registrationForm.getConfirmPassword())
                        )
                .andExpect(model().attributeExists("updated"))
                .andExpect(MockMvcResultMatchers.view().name("user"));

        boolean actualRF = (boolean) Objects.requireNonNull(result.andReturn()
                .getModelAndView()).getModel().get("updated");

        verify(userService, times(1)).updateUser(registrationForm);
        assertTrue(actualRF);
    }

    @Test
    public void verifyUpdateUser_status204() throws Exception {

        RegistrationForm registrationForm = testDataStorage.registrationFormUser1;
        doNothing().when(userService).updateUser(registrationForm);
        when(userService.getUserByEmail(registrationForm.getEmail())).thenReturn(testDataStorage.user1);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/user.html")
                        .param("id", registrationForm.getId().toString())
                        .param("firstName",registrationForm.getFirstName())
                        .param("lastName",registrationForm.getLastName())
                        .param("email",registrationForm.getEmail())
                        .param("password",registrationForm.getPassword())
                        .param("confirmPassword",registrationForm.getConfirmPassword())
                )
                .andExpect(model().attributeExists("registrationForm"))
                .andExpect(MockMvcResultMatchers.view().name("user"));

        RegistrationForm actualRF = (RegistrationForm) Objects.requireNonNull(result.andReturn()
                .getModelAndView()).getModel().get("registrationForm");

        verify(userService, times(1)).updateUser(registrationForm);
        assertEquals(actualRF, registrationForm);
    }


}
