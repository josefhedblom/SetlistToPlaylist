package com.example.sl2pl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class FrontendController {
    @RequestMapping("/{path:[^\\.]*}")
    public String forward() {
        return "forward:/index.html";
    }
}
