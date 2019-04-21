package controller.testController;

import controller.beans.MockBeans;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
//here we define the controller class and mock beans configuration class
@ContextConfiguration(classes = {MockBeans.class,PutPatchMethodExploration.class})
@WebAppConfiguration
public class PutPatchMethodExplorationTest {
    @Autowired
    private WebApplicationContext wac;



    protected MockMvc mockMvc;

    @Before
    public void setUp() {
//      We need to use web application context if we have to do integration testing (although we will do unit testing)
// i.e. multiple class is involved. For e.g. Controller that is dependent on multiple java class
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void putTest(){
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("fName","apollo");
            jsonObject.put("mName"," ");
            jsonObject.put("lName","doley");
            jsonObject.put("gender","male");
            jsonObject.put("country","india");
            jsonObject.put("hobby","cricket");
            jsonObject.put("userName","adoley");
            jsonObject.put("password","pass");
            jsonObject.put("role","admin");
            MvcResult result=this.mockMvc.perform(put("/put")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonObject.toJSONString())
            )
                    .andExpect(status().isOk())
                    .andReturn();
            Assert.assertNotNull(result.getResponse());
            Assert.assertNotNull(result.getResponse().getContentAsString());

        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
}