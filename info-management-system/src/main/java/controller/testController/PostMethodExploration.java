package controller.testController;

import model.User;
import model.testModel.Model3;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostMethodExploration {
    private Logger logger=Logger.getLogger(PostMethodExploration.class);
    @Autowired
    Model3 model3;

    /**
     * This post controller return String as response
     * @return String
     */
    @RequestMapping(value ="/post", method =RequestMethod.POST)
    @ResponseBody
    public String post(){
        logger.info("Entering post controller");
        logger.info("Exiting post controller");
        return "inside post";
    }

    /**
     * This controller return String as a response from Model3 class
     * @return
     */
    @RequestMapping(value ="/post1", method =RequestMethod.POST)
    @ResponseBody
    public String post1(){
        logger.info("Entering post1 controller");
        model3.setModel3Msg("hi from model 3");
        logger.info("Exiting post1 controller");
        return model3.getCombinedMsg();
    }

    /**
     * This controller take Json Object as input parameter and return Json Object as response
     * @param body
     * @return Json Object
     */
    @RequestMapping(value ="/postJson", method =RequestMethod.POST)
    @ResponseBody
    public JSONObject postJson(@RequestBody JSONObject body){
        logger.info("Entering postJson controller");
        body.put("hello1","world1");
        logger.info("Exiting postJson controller");
        return body;
    }

    /**
     * This controller take String as input and return String as response
     * @param body
     * @return String
     */
    @RequestMapping(value ="/postString", method =RequestMethod.POST)
    @ResponseBody
    public String postString(@RequestBody String body){
        logger.info("Entering postString controller");
        body=body+" "+"from postString controller";
        logger.info("Exiting postString controller");
        return body;
    }

    /**
     *
     * @param model
     * @param userDetails
     * @return
     */
    @RequestMapping(value ="/postModelAttribute", method =RequestMethod.POST)
    public String postModelAttribute(ModelMap model, @ModelAttribute("user")User userDetails){
        logger.info("Entering postModelAttribute controller");
        model.addAttribute("user",userDetails);
        logger.info("Exiting postModelAttribute controller");
        return "postTest";
    }
}
