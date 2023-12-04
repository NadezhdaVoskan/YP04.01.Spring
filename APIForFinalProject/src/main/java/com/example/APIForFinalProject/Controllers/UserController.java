package com.example.APIForFinalProject.Controllers;

import com.example.APIForFinalProject.Models.Role;
import com.example.APIForFinalProject.Models.User;
import com.example.APIForFinalProject.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername());
        if (userFromDB != null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(user.getPassword());
        user.setActive(true);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/searchUser")
    public ResponseEntity<User> signIn(@Valid @RequestBody User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername());
        if (userFromDB == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userFromDB, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<User> oneUser(@PathVariable Long idUser) {
        Optional<User> optionalUser = userRepository.findById(idUser);

        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/roles")
    public ResponseEntity<Role[]> getRole() {

        return new ResponseEntity<>(Role.values(), HttpStatus.OK);
    }

    @PutMapping("/user/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable Long idUser,
                                               @RequestBody String[] roles) {
        Optional<User> userOptional = userRepository.findById(idUser);

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        user.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(Role.valueOf(role));
            }
        }

        User userUpdate = userRepository.save(user);

        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }
}
