package controller.testController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetMethodTest {
    /**
     * This controller is for testing GET method that return a view name getTestView.jsp
     * http://localhost:8080/info-management-system/view
     * @return getTestView.jsp
     */
    @RequestMapping(value="/view",method = RequestMethod.GET)
    public String view()
    {
        return "getTestView";
    }

    /**
     * This controller is for testing GET method that return a view with a message to be displayed in view/jsp
     * http://localhost:8080/info-management-system//model.addAttribute
     * @param model
     * @return view/jsp
     */
    @RequestMapping(value="/model.addAttribute",method = RequestMethod.GET)
    public String modelAddAttribute(ModelMap model)
    {
        model.addAttribute("message","From model.addAttribute controller");
        return "getTestModelAddAttribute";
    }

    /**
     * This controller is to test GET method with path variable as shown below
     * http://localhost:8080/info-management-system/pathVariable/hi
     * @param model
     * @param pathVariable
     * @return
     */
    @RequestMapping(value="/pathVariable/{pathVariable}",method = RequestMethod.GET)
    public String pathParam(ModelMap model,@PathVariable String pathVariable)
    {
        model.addAttribute("message",pathVariable);
        return "getTestGeneralPage";
    }

    /**
     * This controller is to test GET method with multiple path variable
     * http://localhost:8080/info-management-system/multiple/pathVariable/pathVariable1/next/pathVariable2
     * @param model
     * @param pathVariable1
     * @param pathVariable2
     * @return view/jsp
     */
    @RequestMapping(value="/multiple/pathVariable/{pathVariable1}/next/{pathVariable2}",method = RequestMethod.GET)
    public String multiplePathParam(ModelMap model,@PathVariable String pathVariable1,@PathVariable String pathVariable2)
    {
        model.addAttribute("message",pathVariable1+" "+pathVariable2);
        return "getTestGeneralPage";
    }

    /**
     * This controller is to test GET method that takes single request parameter
     * http://localhost:8080/info-management-system/requestParam?requestParam1=hi
     * @param model
     * @param requestParam
     * @return view/jsp
     */
    @RequestMapping(value = "/requestParam",method = RequestMethod.GET)
    public String requestParam(ModelMap model, @RequestParam("requestParam1") String requestParam){
        model.addAttribute("message",requestParam);
        return "getTestGeneralPage";
    }

    /**
     * This Controller is to test GET method that takes multiple request parameters
     * http://localhost:8080/info-management-system/multipleRequestParam?requestParam1=hi&requestParam2=hello
     * @param model
     * @param requestParam1
     * @param requestParam2
     * @return view/jsp
     */
    @RequestMapping(value = "/multipleRequestParam",method = RequestMethod.GET)
    public String multipleRequestParam(ModelMap model, @RequestParam("requestParam1") String requestParam1,
                                       @RequestParam("requestParam2") String requestParam2) {
        model.addAttribute("message",requestParam1+" "+requestParam2);
        return "getTestGeneralPage";
    }

    /**
     * This controller is to test GET method that take combination of path variable and query parameter/request parameter
     * http://localhost:8080/info-management-system/combinationOfPathAndRequestParam/pathParam1/pathParam2
     * ?requestParam1=hi&requestParam2=hello
     * @param model
     * @param pathParam1
     * @param pathParam2
     * @param requestParam1
     * @param requestParam2
     * @return view/jsp
     */
    @RequestMapping(value = "/combinationOfPathAndRequestParam/{pathParam1}/{pathParam2}",method = RequestMethod.GET)
    public String combinationOfPathAndRequestParam(ModelMap model,@PathVariable String pathParam1,
                                                   @PathVariable String pathParam2,
                                                   @RequestParam("requestParam1") String requestParam1,
                                                   @RequestParam("requestParam2") String requestParam2) {
        model.addAttribute("message",requestParam1+" "+requestParam2+" "+pathParam1+" "+pathParam2);
        return "getTestGeneralPage";
    }
//    @RequestMapping("/json")
//    public String json()
//    {
//        return "index";
//    }
//
//    @RequestMapping("/string")
//    public String string()
//    {
//        return "index";
//    }
}