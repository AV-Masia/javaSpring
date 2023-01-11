//package fs.spring.vue.controller;
//
//import junitparams.JUnitParamsRunner;
//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.rules.SpringClassRule;
//import org.springframework.test.context.junit4.rules.SpringMethodRule;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@RunWith(JUnitParamsRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@AutoConfigureMockMvc
//public class PageControllerTest {
//
//    @ClassRule
//    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
//    @Rule
//    public final SpringMethodRule springMethodRule = new SpringMethodRule();
//
//    @Autowired
//    private MockMvc mockMvc ;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//
//    @Test
//    public void verifyGetIndexPage() throws Exception {
//        mockMvc.perform(get("/index.html"))
//                .andExpect(view().name("index"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void verifyGetHeader() throws Exception {
//        mockMvc.perform(get("/header.html"))
//                .andExpect(view().name("structure/header"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void verifyGetFooter() throws Exception {
//        mockMvc.perform(get("/footer.html"))
//                .andExpect(view().name("structure/footer"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getReset() throws Exception{
//        mockMvc.perform(get("/reset.html"))
//                .andExpect(view().name("reset"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void verifyLoginWithError() throws Exception{
//        mockMvc.perform(get("/login.html").param("error",  "error"))
//                .andExpect(model().attributeExists("error1"))
//                .andExpect(view().name("login"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void verifyLoginWithoutError() throws Exception{
//        mockMvc.perform(get("/login.html"))
//                .andExpect(model().attributeDoesNotExist("error1"))
//                .andExpect(view().name("login"))
//                .andExpect(status().isOk());
//    }
//}