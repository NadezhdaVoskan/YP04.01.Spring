package com.example.APIForFinalProject.Controllers;
import com.example.APIForFinalProject.Models.Genre;
import com.example.APIForFinalProject.Repositories.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getGenre() {
        List<Genre> genre = genreRepository.findAll();

        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping("/genre/{idGenre}")
    public ResponseEntity<Genre> oneGenre(@PathVariable Long idGenre) {
        Optional<Genre> optionalGenre = genreRepository.findById(idGenre);

        if (optionalGenre.isPresent()) {
            return new ResponseEntity<>(optionalGenre.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/genre")
    public ResponseEntity<Genre> createGenre(@Valid @RequestBody Genre genreRequest) {
        Genre genre = genreRepository.save(genreRequest);

        return new ResponseEntity<>(genre, HttpStatus.CREATED);
    }

    @PutMapping("/genre/{idGenre}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long idGenre,
                                             @Valid @RequestBody Genre genreRequest) {
        Optional<Genre> genreOptional = genreRepository.findById(idGenre);

        if (genreOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Genre genre = genreOptional.get();

        genre.setIdBookGenre(genreRequest.getIdBookGenre());
        genre.setNameGenre(genreRequest.getNameGenre());

        Genre genreUpdate = genreRepository.save(genre);

        return new ResponseEntity<>(genreUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/genre/{idGenre}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long idGenre) {
        Optional<Genre> genreOptional = genreRepository.findById(idGenre);

        if (genreOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Genre genre = genreOptional.get();

        genreRepository.delete(genre);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
