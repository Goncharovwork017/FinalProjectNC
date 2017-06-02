package by.goncharov.nc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ivan on 01.06.2017.
 */
@RestController
public class HomeController {


    @RequestMapping("/")
    public String home(){return "SURPRISE MOTHERFU**** ";}

}
