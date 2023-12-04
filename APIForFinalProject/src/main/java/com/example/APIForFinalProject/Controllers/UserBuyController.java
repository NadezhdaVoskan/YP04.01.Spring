package com.example.APIForFinalProject.Controllers;
import com.example.APIForFinalProject.Models.User;
import com.example.APIForFinalProject.Models.UserBuy;
import com.example.APIForFinalProject.Repositories.UserBuyRepository;
import com.example.APIForFinalProject.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserBuyController {
    @Autowired
    private UserBuyRepository userBuyRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/searchOrder")
    public ResponseEntity<List<UserBuy>> searchOrder(@Valid @RequestBody User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername());
        if (userFromDB == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<UserBuy> userBuys = userBuyRepository.findPersonShopByPerson(userFromDB.getIdUser());

        return new ResponseEntity<>(userBuys, HttpStatus.OK);
    }

    @GetMapping("/userBuy/{idUserBuy}")
    public ResponseEntity<UserBuy> oneUserBuy(@PathVariable Long idUserBuy) {
        Optional<UserBuy> optionalUserBuy = userBuyRepository.findById(idUserBuy);

        if (optionalUserBuy.isPresent()) {
            return new ResponseEntity<>(optionalUserBuy.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/userBuy")
    public ResponseEntity<UserBuy> createUserBuy(@Valid @RequestBody UserBuy userBuyRequest) {
        UserBuy userBuy = userBuyRepository.save(userBuyRequest);

        return new ResponseEntity<>(userBuy, HttpStatus.CREATED);
    }

    @PutMapping("/userBuy/{idUserBuy}")
    public ResponseEntity<UserBuy> updateUserBuy(@PathVariable Long idUserBuy,
                                                       @Valid @RequestBody UserBuy personShopRequest) {
        Optional<UserBuy> optionalUserBuy = userBuyRepository.findById(idUserBuy);

        if (optionalUserBuy.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserBuy userBuy = optionalUserBuy.get();

        userBuy.setIdUserBuy(personShopRequest.getIdUserBuy());
        userBuy.setBook(personShopRequest.getBook());
        userBuy.setUser(personShopRequest.getUser());

        UserBuy userBuyUpdate = userBuyRepository.save(userBuy);

        return new ResponseEntity<>(userBuyUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/userBuy/{idUserBuy}")
    public ResponseEntity<?> deleteUserBuy(@PathVariable Long idUserBuy) {
        Optional<UserBuy> optionalUserBuy = userBuyRepository.findById(idUserBuy);

        if (optionalUserBuy.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserBuy personShop = optionalUserBuy.get();

        userBuyRepository.delete(personShop);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
