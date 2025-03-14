package org.example.alaa.dockerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private Repo repo;


    @GetMapping
    public String home() {
        return "Docker Running!!";
    }

    @PostMapping("/add")
    public User saveUser(@RequestBody User user) {
       return repo.save(user);
    }


}
