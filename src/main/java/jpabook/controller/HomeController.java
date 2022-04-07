package jpabook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        log.info("home controller");
        return "home"; // hello 라는 thymeleaf라는 파일을 사용한다.
    }
}
