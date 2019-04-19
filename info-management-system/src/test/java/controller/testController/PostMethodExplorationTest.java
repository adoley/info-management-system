package controller.testController;

import controller.beans.MockBeans;
import model.User;
import model.testModel.Model3;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
//here we define the controller class and mock beans configuration class
@ContextConfiguration(classes = {MockBeans.class,PostMethodExploration.class})
@WebAppConfiguration
public class PostMethodExplorationTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    Model3 model3;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
//      We need to use web application context if we have to do integration testing (although we will do unit testing)
// i.e. multiple class is involved. For e.g. Controller that is dependent on multiple java class
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void postTest(){
        try {
            MvcResult result=this.mockMvc.perform(post("/post"))
                                        .andExpect(status().isOk())
                                        .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());
            Assert.assertEquals("inside post",result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void post1Test(){
        try {
            when(model3.getCombinedMsg()).thenReturn("mock response");

            MvcResult result=this.mockMvc.perform(post("/post1"))
                                         .andExpect(status().isOk())
                                         .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());
            Assert.assertEquals("mock response",result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void postJsonTest(){
        try {

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("hi","hello");
            jsonObject.put("hello","world");
            String output="{\"hi\":\"hello\",\"hello1\":\"world1\",\"hello\":\"world\"}";
            MvcResult result=this.mockMvc.perform(
                                            post("/postJson")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(jsonObject.toJSONString())
                                          )
                                         .andExpect(status().isOk())
                                         .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());
            Assert.assertEquals(output,result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void postStringTest(){
        try {
            String output="Hi from postString controller";
            MvcResult result=this.mockMvc.perform(
                    post("/postString")
                            .contentType(MediaType.TEXT_PLAIN)
                            .content("Hi")
                    )
                    .andExpect(status().isOk())
                    .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());
            Assert.assertEquals(output,result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void postModelAttributeTest(){
        try {
            String viewName="postTest";
            User user=new User();
            user.setRole("admin");
            user.setUserName("apollo");
            user.setPassword("pass");
            user.setGender("male");
            MvcResult result=this.mockMvc.perform(post("/postModelAttribute")
                                         .flashAttr("user", user)
                                        )
                                        .andExpect(status().isOk())
                                        .andReturn();
            Assert.assertNotNull(result.getResponse());
            Assert.assertEquals(viewName,result.getModelAndView().getViewName());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("user"));
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

}