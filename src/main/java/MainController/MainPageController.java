package MainController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;

/**
 * Created by Daniel_D'AGE on 02.10.2018.
 */
@Controller
public class MainPageController {
    @GET
    @RequestMapping("/")
    public String showMainPage(){
        return "index";
    }

}
