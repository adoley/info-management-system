package controller.testController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners
public class TestGetMethodTest {

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        Object arr[]=new Object[1];
        arr[0]= new GetMethodTest();
//      Here we need to pass all the controller that we want to test
        this.mockMvc= standaloneSetup(arr).build();
    }

    @Test
    public void testViewPositive(){
        try {

            this.mockMvc.perform(get("/view"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("getTestView"));
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testViewNegative(){
        try {
            MvcResult result=this.mockMvc.perform(get("/view"))
                                        .andExpect(status().isOk())
                                        .andReturn();

            Assert.assertNotEquals("anyViewName",result.getModelAndView().getViewName());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testModelAddAttribute(){
        try {
            MvcResult result=this.mockMvc.perform(get("/model.addAttribute"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("getTestModelAddAttribute"))
                                        .andReturn();
            Assert.assertNotNull(result.getModelAndView().getModel());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("message"));
            Assert.assertEquals("From model.addAttribute controller",result.getModelAndView().getModel().get("message"));
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testPathParam(){
        try {
            MvcResult result=this.mockMvc.perform(get("/pathVariable/hi"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("getTestGeneralPage"))
                                        .andReturn();
            Assert.assertNotNull(result.getModelAndView().getModel());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("message"));
            Assert.assertEquals("hi",result.getModelAndView().getModel().get("message"));
            Assert.assertTrue(result.getRequest().getPathInfo().endsWith("hi"));
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testMultiplePathParam(){
        try {
            MvcResult result=this.mockMvc.perform(get("/multiple/pathVariable/pathVariable1/next/pathVariable2"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("getTestGeneralPage"))
                                        .andReturn();
            Assert.assertNotNull(result.getModelAndView().getModel());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("message"));
            Assert.assertEquals("pathVariable1 pathVariable2",result.getModelAndView().getModel().get("message"));
            Assert.assertEquals("/multiple/pathVariable/pathVariable1/next/pathVariable2",result.getRequest().getPathInfo());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testRequestParam(){
        try {
            MvcResult result=this.mockMvc.perform(get("/requestParam?requestParam1=hi"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("getTestGeneralPage"))
                                        .andReturn();
            Assert.assertNotNull(result.getModelAndView().getModel());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("message"));
            Assert.assertEquals("hi",result.getModelAndView().getModel().get("message"));
            Assert.assertEquals("/requestParam",result.getRequest().getPathInfo());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testMultipleRequestParam(){
        try {
            MvcResult result=this.mockMvc.perform(get("/multipleRequestParam?requestParam1=hi&requestParam2=hello"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("getTestGeneralPage"))
                                        .andReturn();
            Assert.assertNotNull(result.getModelAndView().getModel());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("message"));
            Assert.assertEquals("hi hello",result.getModelAndView().getModel().get("message"));
            Assert.assertEquals("/multipleRequestParam",result.getRequest().getPathInfo());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testCombinationOfPathAndRequestParam(){
        try {
            MvcResult result=this.mockMvc.perform(get("/combinationOfPathAndRequestParam/pathParam1/pathParam2" +
                    "?requestParam1=hi&requestParam2=hello"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("getTestGeneralPage"))
                                        .andReturn();
            Assert.assertNotNull(result.getModelAndView().getModel());
            Assert.assertTrue(result.getModelAndView().getModelMap().containsAttribute("message"));
            Assert.assertEquals("hi hello pathParam1 pathParam2",result.getModelAndView().getModel().get("message"));
            Assert.assertEquals("/combinationOfPathAndRequestParam/pathParam1/pathParam2",result.getRequest().getPathInfo());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testJson(){
        try {

            String expected="{\"fName\":\"apollo\",\"mName\":\" \",\"lName\":\"doley\",\"gender\":\"male\",\"country\":\"India\",\"hobby\":\"music\",\"userName\":\"username\",\"password\":\"password\",\"role\":\"admin\"}\n";
            MvcResult result=this.mockMvc.perform(get("/json"))
                        .andExpect(status().isOk())
                        .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());

        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
}
