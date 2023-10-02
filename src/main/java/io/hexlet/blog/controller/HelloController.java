package io.hexlet.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/about")
    public String index() {
        return "A boilerplate created by Hexlet";
    }
}
