package aggregator.newsrss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome() {
        return "index";

    }
    @RequestMapping("/news")
    public String news() {
        return "news";
    }
}
