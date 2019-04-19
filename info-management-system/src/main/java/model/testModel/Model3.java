package model.testModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * beans using java-based configuration
 */
@Component
public class Model3 {
    @Autowired
    Model4 model4;

    private String model3Msg;

    public String getModel3Msg() {
        return model3Msg;
    }

    public void setModel3Msg(String model3Msg) {
        this.model3Msg = model3Msg;
    }

    public String getCombinedMsg(){
        return model3Msg+" "+model4.getModel4Msg();
    }
}
