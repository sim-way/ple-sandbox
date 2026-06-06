package com.example.ple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootController {

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
