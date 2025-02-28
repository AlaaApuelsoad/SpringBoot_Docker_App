package org.example.alaa.dockerapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String home() {
        return "Docker Running!!";
    }
}
