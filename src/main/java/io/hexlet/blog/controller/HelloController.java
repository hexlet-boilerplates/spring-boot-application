package io.hexlet.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HelloController {
    @GetMapping("/about")
    public String index() {
        return "A boilerplate created by Hexlet";
    }
    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView("/about");
    }
}
