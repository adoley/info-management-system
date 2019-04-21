package controller.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeleteMethodExploration {
    /**
     * Delete method signature is very much similar to GET method.
     *
     */

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String value){
        return value;
    }
}
