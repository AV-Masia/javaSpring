//package fs.spring.vue.service.impl;
//
//import fs.spring.vue.email.EmailService;
//import fs.spring.vue.model.Role;
//import fs.spring.vue.model.User;
//import fs.spring.vue.model.form.RegistrationForm;
//import fs.spring.vue.repository.UserRepository;
//import fs.spring.vue.security.config.CryptConfiguration;
//import fs.spring.vue.service.UserService;
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//import org.assertj.core.util.Lists;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.mail.MessagingException;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(JUnitParamsRunner.class)
//@SpringBootTest
//public class UserServiceImplTest {
//
//    @Mock
//    private EmailService emailService;
//
//    @Mock
//    private CryptConfiguration cryptConfiguration;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService = new UserServiceImpl();
//
//    @Mock
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private static class TestDataStorage {
//        User user1 = User.builder().id(1L).role(List.of(Role.USER)).email("yakusik@mail.ru").password("111")
//                .firstName("Sofia").lastName("Vasko")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//        User user2 = User.builder().id(2L).role(List.of(Role.USER)).email("petya@gmail.com").password("ytrewq")
//                .firstName("Alena").lastName("Gurkova")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//
//        List<User> users1 = Lists.newArrayList(user1, user2);
//        List<User> users2 = List.of(user1);
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
//                .firstName("Sofia")
//                .lastName("Gurkova")
//                .password("qwerty")
//                .confirmPassword("ytrewq")
//                .build();
//        RegistrationForm registrationFormUser3 = RegistrationForm.builder().id(1L)
//                .email("yakusik@mail.ru")
//                .firstName("Sofia")
//                .lastName("Vasko")
//                .password("111")
//                .confirmPassword("111")
//                .build();
//        RegistrationForm registrationFormUser4 = RegistrationForm.builder().id(2L)
//                .email("petya@gmail.com")
//                .firstName("Alena")
//                .lastName("Gurkova")
//                .password("qwerty")
//                .confirmPassword("ytrewq")
//                .build();
//
//        User createUser1 = User.builder().id(1L).role(List.of(Role.USER))
//                .email("yakusik@mail.ru").password("111")
//                .firstName("Sofia").lastName("Vasko")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//        User createUser2 = User.builder().id(2L).role(List.of(Role.USER))
//                .email("petya@gmail.com").password("ytrewq")
//                .firstName("Alena").lastName("Gurkova")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//
//        User expectedUpdateUser1 = User.builder().id(1L).role(List.of(Role.USER))
//                .email("yakusik@mail.ru").password("111")
//                .firstName("Anastasia").lastName("Vasko")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//        User expectedUpdateUser2 = User.builder().id(2L).role(List.of(Role.USER))
//                .email("petya@gmail.com").password("qwerty2")
//                .firstName("Sofia").lastName("Gurkova")
//                .credentialsNonExpired(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .enabled(true)
//                .build();
//    }
//
//    private static final TestDataStorage testDataStorage = new TestDataStorage();
//
//    @SuppressWarnings("unused")
//    @Parameterized.Parameters
//    private Object[] paramsEmailUserAndEntityUser() {
//        return new Object[][]{
//                {"yakusik@mail.ru", testDataStorage.user1},
//                {"petya@gmail.com", testDataStorage.user2}
//        };
//    }
//
//    @SuppressWarnings("unused")
//    @Parameterized.Parameters
//    private Object[] paramsEmailUserAndEntityUserAndPassword() {
//        return new Object[][]{
//                {"yakusik@mail.ru", testDataStorage.user1, testDataStorage.user1.getPassword()},
//                {"petya@gmail.com", testDataStorage.user2, testDataStorage.user2.getPassword()}
//        };
//    }
//
//    @SuppressWarnings("unused")
//    @Parameterized.Parameters
//    private Object[] paramsCreateUserFromRegisterForm() {
//        return new Object[][]{
//            {testDataStorage.registrationFormUser3, testDataStorage.user1,
//                    testDataStorage.createUser1, },
//            {testDataStorage.registrationFormUser4, testDataStorage.user2,
//                    testDataStorage.createUser2}
//        };
//    }
//
//    @SuppressWarnings("unused")
//    @Parameterized.Parameters
//    private Object[] paramsRegisterForm() {
//        return new Object[][]{
//                {testDataStorage.registrationFormUser1, testDataStorage.user1,
//                        testDataStorage.expectedUpdateUser1},
//                {testDataStorage.registrationFormUser2, testDataStorage.user2,
//                        testDataStorage.expectedUpdateUser2}
//        };
//    }
//
//    @Test
//    public void verifyGetUserByEmail() {
//        String email = "yakusik@mail.ru";
//        User expectedUser = testDataStorage.user1;
//        when(userRepository.findByEmail(email)).thenReturn(expectedUser);
//
//        User actual = userService.getUserByEmail(email);
//
//        verify(userRepository, times(1)).findByEmail(email);
//        assertEquals(actual, expectedUser);
//    }
//
//    @Test
//    public void verifyGetUserById() {
//        when(userRepository.getById(1L)).thenReturn(testDataStorage.user1);
//
//        User actual = userService.getUserById(1L);
//
//        verify(userRepository, times(1)).getById(1L);
//        assertEquals(actual, testDataStorage.user1);
//    }
//
//    @Test
//    @Parameters(method = "paramsEmailUserAndEntityUserAndPassword")
//    public void verifyUpdateUserPassword(String email, User expectedUser, String oldPassword) {
//        when(userRepository.findByEmail(email)).thenReturn(expectedUser);
//        when(cryptConfiguration.passwordEncoder()).thenReturn(bCryptPasswordEncoder);
//        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("111");
//        doNothing().when(userRepository).updateUserPassword(expectedUser.getId(), "111");
//
//        User actual = userService.updateUserPassword(email);
//
//        verify(userRepository, times(1)).findByEmail(email);
//        verify(userRepository, times(1)).updateUserPassword(expectedUser.getId(), "111");
//        assertNotEquals(actual.getPassword(), oldPassword);
//    }
//
//
//    @Test
//    public void verifyDeleteUserById() {
//        doNothing().when(userRepository).deleteById(2L);
//        userService.deleteUserById(2L);
//        verify(userRepository, times(1)).deleteById(2L);
//
//    }
//
//    @Test
//    @Parameters(method = "paramsCreateUserFromRegisterForm")
//    public void createUser(RegistrationForm registrationForm, User actual, User expectedUser) throws MessagingException {
//        when(cryptConfiguration.passwordEncoder()).thenReturn(bCryptPasswordEncoder);
//        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("qwerty2");
//        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
//        doNothing().when(emailService).sendRegister(registrationForm.getFirstName(),
//                registrationForm.getEmail());
//
//        userService.createUserFromRegistrationForm(registrationForm);
//
//        verify(userRepository, times(1)).save(any(User.class));
//        assertEquals(actual, expectedUser);
//    }
//
//    @Test
//    @Parameters(method = "paramsRegisterForm")
//    public void updateUser(RegistrationForm registrationForm,  User actual, User expectedUser) {
//        when(userRepository.getById(registrationForm.getId())).thenReturn(actual);
//        when(cryptConfiguration.passwordEncoder()).thenReturn(bCryptPasswordEncoder);
//        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("qwerty2");
//        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
//
//        userService.updateUser(registrationForm);
//
//        verify(userRepository, times(1)).getById(registrationForm.getId());
//        verify(userRepository, times(1)).save(any(User.class));
//        assertEquals(actual, expectedUser);
//
//
//    }
//
//
//}
