package com.example.FinalProjectSpringYP.Controllers;

import com.example.FinalProjectSpringYP.Models.User;
import com.example.FinalProjectSpringYP.Models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/Admin")
@Controller
public class AdminController {
    public String baseUrl = "http://localhost:8080/";
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/Index")
    public String AdminIndex(Model model) {
        User[] requestGet = getRestTemplate().getForObject(baseUrl+"user", User[].class);
        model.addAttribute("users",requestGet);
        return "/Admin/Index";
    }

    @GetMapping("/Update/{id}")
    public String AdminUpdate(@PathVariable Long id, Model model)
    {
        User user = getRestTemplate().getForObject(baseUrl+"user/"+id, User.class);
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "/Admin/Update";
    }

    @PostMapping("/Update/{id}")
    public String AdminUpdate(@RequestParam(name="roles[]", required = false) String[] roles, @PathVariable Long id)
    {
        if(roles == null)
        {
            return "redirect:/Admin/Update/"+id;
        }
        getRestTemplate().put(baseUrl+"user/"+id, roles, Void.class);
        return "redirect:/Admin/Index";
    }}
