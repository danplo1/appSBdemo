package Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Daniel_D'AGE on 02.10.2018.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder pwdEncrypt(){

        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        return bcp;
    }

}
