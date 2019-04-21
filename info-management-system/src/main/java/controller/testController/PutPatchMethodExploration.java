package controller.testController;

import model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PutPatchMethodExploration {
    /**
     * Basically Put method is use when we want to update resource. Method signature of PUT is similar to POST
     * method.
     * Patch method is use when we can to partially update the resource.
     * For example:
     * There are four attribute like firstName,mName,id,country.
     * if we want to update the whole attribute values then we should use PUT method
     * If we want to update one or two or three attribute at a time then we should use PATCH method
     */
    private Logger logger=Logger.getLogger(PutPatchMethodExploration.class);

    /**
     * This is controller of method type PUT that accept input of type User
     * and return the same details back
     * @param userDetails
     * @return
     */
    @RequestMapping(value = "/put",method = RequestMethod.PUT)
    @ResponseBody
    public User put(@RequestBody User userDetails){
        logger.info("Entering put controller");
        logger.info("Exiting put controller");
        return userDetails;
    }
}
