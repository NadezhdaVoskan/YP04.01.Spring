package com.example.FinalProjectSpringYP.Controllers;

import com.example.FinalProjectSpringYP.Models.User;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthRegController {

    public String baseUrl = "http://localhost:8080/";

    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/Registration")
    public String Registration(Model model) {
        return "Registration";
    }

    @PostMapping("/Registration")
    private String Registration(@Valid User user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "/Registration";
        }
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        User result = getRestTemplate().postForObject(baseUrl+"signUp",user, User.class);
        return "redirect:/Authorization";
    }
}
