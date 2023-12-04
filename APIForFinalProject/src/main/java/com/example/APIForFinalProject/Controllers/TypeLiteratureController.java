package com.example.APIForFinalProject.Controllers;

import com.example.APIForFinalProject.Models.TypeLiterature;
import com.example.APIForFinalProject.Repositories.TypeLiteratureRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class TypeLiteratureController {
    @Autowired
    private TypeLiteratureRepository typeLiteratureRepository;

    @GetMapping("/typeLiterature")
    public ResponseEntity<List<TypeLiterature>> getTypeLiterature() {
        List<TypeLiterature> typeLiteratures = typeLiteratureRepository.findAll();

        return new ResponseEntity<>(typeLiteratures, HttpStatus.OK);
    }

    @GetMapping("/typeLiterature/{idTypeLiterature}")
    public ResponseEntity<TypeLiterature> oneTypeLiterature(@PathVariable Long idTypeLiterature) {
        Optional<TypeLiterature> optionalTypeLiterature = typeLiteratureRepository.findById(idTypeLiterature);

        if (optionalTypeLiterature.isPresent()) {
            return new ResponseEntity<>(optionalTypeLiterature.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/typeLiterature")
    public ResponseEntity<TypeLiterature> createTypeLiterature(@Valid @RequestBody TypeLiterature typeLiteratureRequest) {
        TypeLiterature typeLiterature = typeLiteratureRepository.save(typeLiteratureRequest);

        return new ResponseEntity<>(typeLiterature, HttpStatus.CREATED);
    }

    @PutMapping("/typeLiterature/{idTypeLiterature}")
    public ResponseEntity<TypeLiterature> updateTypeLiterature(@PathVariable Long idTypeLiterature,
                                                                 @Valid @RequestBody TypeLiterature typeLiteratureRequest) {
        Optional<TypeLiterature> typeLiteratureOptional = typeLiteratureRepository.findById(idTypeLiterature);

        if (typeLiteratureOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TypeLiterature typeLiterature = typeLiteratureOptional.get();

        typeLiterature.setIdBookTypeLiterature(typeLiteratureRequest.getIdBookTypeLiterature());
        typeLiterature.setNameTypeLiterature(typeLiteratureRequest.getNameTypeLiterature());

        TypeLiterature typeLiteratureUpdate = typeLiteratureRepository.save(typeLiterature);

        return new ResponseEntity<>(typeLiteratureUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/typeLiterature/{idTypeLiterature}")
    public ResponseEntity<?> deleteTypeLiterature(@PathVariable Long idTypeLiterature) {
        Optional<TypeLiterature> typeLiteratureOptional = typeLiteratureRepository.findById(idTypeLiterature);

        if (typeLiteratureOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TypeLiterature typeLiterature = typeLiteratureOptional.get();

        typeLiteratureRepository.delete(typeLiterature);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
