package com.example.APIForFinalProject.Controllers;
import com.example.APIForFinalProject.Models.Shop;
import com.example.APIForFinalProject.Repositories.ShopRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/shop")
    public ResponseEntity<List<Shop>> getShop() {
        List<Shop> shops = shopRepository.findAll();

        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    @GetMapping("/shop/{idShop}")
    public ResponseEntity<Shop> oneShop(@PathVariable Long idShop) {
        Optional<Shop> optionalShop = shopRepository.findById(idShop);

        if (optionalShop.isPresent()) {
            return new ResponseEntity<>(optionalShop.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/shop")
    public ResponseEntity<Shop> createShop(@Valid @RequestBody Shop shopRequest) {
        Shop shop = shopRepository.save(shopRequest);

        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @PutMapping("/shop/{idShop}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long idShop,
                                           @Valid @RequestBody Shop shopRequest) {
        Optional<Shop> shopOptional = shopRepository.findById(idShop);

        if (shopOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Shop shop = shopOptional.get();

        shop.setIdShop(shopRequest.getIdShop());
        shop.setAddress(shopRequest.getAddress());
        shop.setBook(shopRequest.getBook());
        shop.setSchedule(shopRequest.getSchedule());

        Shop shopUpdate = shopRepository.save(shop);

        return new ResponseEntity<>(shopUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/shop/{idShop}")
    public ResponseEntity<?> deleteshop(@PathVariable Long idShop) {
        Optional<Shop> shopOptional = shopRepository.findById(idShop);

        if (shopOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Shop shop = shopOptional.get();

        shopRepository.delete(shop);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
