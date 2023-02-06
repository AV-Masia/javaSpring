//package fs.spring.vue.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import fs.spring.vue.model.Role;
//import fs.spring.vue.model.User;
//import fs.spring.vue.model.form.RegistrationForm;
//import fs.spring.vue.service.UserService;
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.rules.SpringClassRule;
//import org.springframework.test.context.junit4.rules.SpringMethodRule;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.Filter;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(JUnitParamsRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @ClassRule
//    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
//    @Rule
//    public final SpringMethodRule springMethodRule = new SpringMethodRule();
//
//    @MockBean
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController = new UserController();
//
//    @Mock
//    private WebDataBinder webDataBinder;
//
//    @Mock
//    private BeanPropertyBindingResult beanPropertyBindingResult;
//
//    @Mock
//    private BindingResult bindingResult;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    @Qualifier("springSecurityFilterChain")
//    private Filter springSecurityFilterChain;
//
//    @Before
//    public void setup() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
//                .addFilter(springSecurityFilterChain)
//                .dispatchOptions(true).build();
//        MockitoAnnotations.openMocks(this);
//
//    }
//
//    @After
//    public void tearDown() {
//        SecurityContextHolder.clearContext();
//    }
//
//    private static class TestDataStorage {
//        User user1 = User.builder().id(1L).role(Role.ADMIN).email("yakusik@mail.ru").password("111")
//                .firstName("Anastasia").lastName("Vasko")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//        User user2 = User.builder().id(2L).role(Role.USER).email("petya@gmail.com").password("ytrewq")
//                .firstName("Alena").lastName("Gurkova")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//
//        RegistrationForm registrationFormUser1 = RegistrationForm.builder().id(1L)
//                .email("yakusik@mail.ru")
//                .firstName("Anastasia")
//                .lastName("Vasko")
//                .password("")
//                .confirmPassword("")
//                .build();
//        RegistrationForm registrationFormUser2 = RegistrationForm.builder().id(2L)
//                .email("petya@gmail.com")
//                .firstName("Alena")
//                .lastName("Gurkova")
//                .password("ytrewq")
//                .confirmPassword("ytrewq")
//                .build();
//        RegistrationForm registrationFormUser3 = RegistrationForm.builder().id(2L)
//                .email("")
//                .firstName("")
//                .lastName("")
//                .password("")
//                .confirmPassword("")
//                .build();
//    }
//
//    public static final TestDataStorage testDataStorage = new TestDataStorage();
//
//
//    @Parameterized.Parameters
//    private Object[] paramsEmailUserAndEntityUser() {
//        return new Object[][]{
//                {"yakusik@mail.ru", testDataStorage.user1},
//                {"petya@gmail.com", testDataStorage.user2}
//        };
//    }
//
//    @Parameterized.Parameters
//    private Object[] paramsUpdateUser() {
//        return new Object[][]{
//                {testDataStorage.registrationFormUser1, testDataStorage.user1},
//                {testDataStorage.registrationFormUser2, testDataStorage.user2}
//        };
//    }
//
//
//    @Test
//    @Parameters(method = "paramsEmailUserAndEntityUser")
//    public void verifyGetUserByContext(String email, User user) throws Exception{
//        when(userService.getUserByEmail(email)).thenReturn(user);
//        mockMvc.perform(get("/api/user").param("userName", String.valueOf(email)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(user)));
//
//        verify(userService, times(1)).getUserByEmail(email);
//    }
//
//    @Test
//    public void verifyDeleteUser() throws Exception {
//        String email = "petya@gmail.com";
//        Long id = 2L;
//        when(userService.deleteUserById(id)).thenReturn(true);
//        mockMvc.perform(
//                        delete("/api/deleteUser")
//                                .with(SecurityRequestPostProcessors.userDetailsService(email))
//                                .param("id", String.valueOf(id)))
//                .andExpect(status().is2xxSuccessful());
//
//        verify(userService, times(1)).deleteUserById(id);
//    }
//
//    @Test
//    @Parameters(method = "paramsUpdateUser")
//    public void verifyUpdateUser_status200(RegistrationForm registrationForm, User user) throws Exception {
//        doNothing().when(userService).updateUser(registrationForm);
//        when(userService.getUserByEmail(registrationForm.getEmail())).thenReturn(user);
//
//                mockMvc.perform(post("/api/user")
//                                .content(objectMapper.writeValueAsString(registrationForm))
//                                .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().is2xxSuccessful());
//
//        verify(userService, times(1)).updateUser(registrationForm);
//
//    }
//
//    @Test
//    @Parameters(method = "paramsUpdateUser")
//    public void verifyUpdateUser_status204(RegistrationForm registrationForm, User user) throws Exception {
//        doNothing().when(userService).updateUser(registrationForm);
//        when(userService.getUserByEmail(registrationForm.getEmail())).thenReturn(user);
//
////        when(webDataBinder.getBindingResult()).thenReturn(result);
//        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
//
//        mockMvc.perform(post("/api/user")
//                        .content(objectMapper.writeValueAsString(registrationForm))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is5xxServerError());
//
//        verify(userService, times(1)).updateUser(registrationForm);
//
//    }
//
//}
