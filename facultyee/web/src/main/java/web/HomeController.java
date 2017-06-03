package web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ivan on 10.05.2017.
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){return "SURPRISE MOTHERFU**** ";}





}
