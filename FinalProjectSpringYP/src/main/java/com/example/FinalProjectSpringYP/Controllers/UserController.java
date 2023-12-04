package com.example.FinalProjectSpringYP.Controllers;

import com.example.FinalProjectSpringYP.Models.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/User")
@Controller
public class UserController {
    public String baseUrl = "http://localhost:8080/";
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/Index")
    public String UserIndex(Model model) {
        Book[] requestGetBook = getRestTemplate().getForObject(baseUrl+"book", Book[].class);
        Shop[] requestGetShop = getRestTemplate().getForObject(baseUrl+"shop", Shop[].class);
        model.addAttribute("objectsBook",requestGetBook);
        model.addAttribute("objectsShop",requestGetShop);
        return "/User/Index";
    }

    @PostMapping("/Index")
    public String UserIndex(@RequestParam(name = "address",required = false) String address,
                            @RequestParam(name = "name", required = false) String name,
                            Model model) {
        Book[] requestGetBook = getRestTemplate().getForObject(baseUrl+"book/"+address+"/"+name, Book[].class);
        Shop[] requestGetShop = getRestTemplate().getForObject(baseUrl+"shop", Shop[].class);
        model.addAttribute("objectsShop",requestGetShop);
        model.addAttribute("objectsBook",requestGetBook);
        return "/User/Index";
    }

    @GetMapping("/Book/{id}")
    public String BookGet(@PathVariable(value = "id") Long id, Model model) {
        Book requestGetBook = getRestTemplate().getForObject(baseUrl+"book/"+id, Book.class);
        User[] requestGetUser = getRestTemplate().getForObject(baseUrl+"user", User[].class);
        model.addAttribute("objectBook",requestGetBook);
        model.addAttribute("objectUser", requestGetUser);
        return "/User/Book";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PostMapping("/Book/{id}")
    public String BookAddUser(@PathVariable(value = "id") Long id,
                                   User user, UserBuy userBuy)
    {
        user.setUsername(getCurrentUsername());
        User requestGetUser = getRestTemplate().postForObject(baseUrl+"searchUser",user ,User.class);
        Book requestGetBook = getRestTemplate().getForObject(baseUrl+"book/"+id, Book.class);
        userBuy.setBook(requestGetBook);
        userBuy.setUser(requestGetUser);
        UserBuy postUserBuy = getRestTemplate().postForObject(baseUrl+"userBuy",userBuy,UserBuy.class);
        return "redirect:/User/Index";
    }

    @GetMapping("/Order")
    public String UserOrder(User user, Model model) {
        user.setUsername(getCurrentUsername());
        UserBuy[] requestGetShop = getRestTemplate().postForObject(baseUrl+"searchOrder",user ,UserBuy[].class);
        model.addAttribute("objectsUserBuy",requestGetShop);
        return "/User/Order";
    }
    @GetMapping("/Order/{id}")
    public String BookDelete(@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(baseUrl+"userBuy/"+id);
        return "redirect:/User/Order";
    }
}
