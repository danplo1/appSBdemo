package SpringBoot.appSBdemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Daniel_D'AGE on 02.10.2018.
 */

@Configuration
public class ServletInitializer extends SpringBootServletInitializer{


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(AppdemoApplication.class);
    }


}
