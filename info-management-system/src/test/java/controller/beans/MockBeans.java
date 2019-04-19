package controller.beans;

import model.testModel.Model3;
import model.testModel.Model4;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc //content-type and accept header, generally content negotiation won't work
// and its equivalent to <mvc:annotation-driven/>
public class MockBeans {
    @Bean
    public Model3 model3(){
        return Mockito.mock(Model3.class);
    }
    @Bean
    public Model4 model4(){
        return Mockito.mock(Model4.class);
    }

}
