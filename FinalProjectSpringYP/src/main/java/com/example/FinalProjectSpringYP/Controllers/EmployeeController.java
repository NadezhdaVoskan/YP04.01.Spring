package com.example.FinalProjectSpringYP.Controllers;

import com.example.FinalProjectSpringYP.Models.*;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
@RequestMapping("/Employee")
@Controller
public class EmployeeController {

    public String baseUrl = "http://localhost:8080/";

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/Index")
    public String Index(Model model) {
        return "/Employee/Index";
    }


    @GetMapping("/Book/Index")
    public String BookIndex(Model model) {
        Book[] requestGet = getRestTemplate().getForObject(baseUrl+"book", Book[].class);
        model.addAttribute("objects",requestGet);
        return "/Employee/Book/Index";
    }


    @GetMapping("/Book/Add")
    public String BookAdd(@ModelAttribute("book") Book book, Model model) {
        Genre[] requestGetGenre = getRestTemplate().getForObject(baseUrl+"genre", Genre[].class);
        TypeLiterature[] requestGetTypeLiterature = getRestTemplate().getForObject(baseUrl+"typeLiterature", TypeLiterature[].class);
        model.addAttribute("objectsGenre",requestGetGenre);
        model.addAttribute("objectsTypeLiterature",requestGetTypeLiterature);
        return "/Employee/Book/Add";
    }


    @PostMapping("/Book/Add")
    public String BookPost(@Valid @ModelAttribute("book") Book book,
                              BindingResult bindingResult,
                              @RequestParam(name="idTypeLiterature") Long idTypeLiterature,
                              @RequestParam(name="idGenre") Long idGenre,
                              Model model) {
        if (bindingResult.hasErrors())
        {
            Genre[] requestGetGenre = getRestTemplate().getForObject(baseUrl+"genre", Genre[].class);
            TypeLiterature[] requestTypeLiterature = getRestTemplate().getForObject(baseUrl+"typeLiterature", TypeLiterature[].class);
            model.addAttribute("objectsGenre",requestGetGenre);
            model.addAttribute("objectsTypeLiterature",requestTypeLiterature);
            return "/Employee/Book/Add";
        }
        Genre requestGetGenreId = getRestTemplate().getForObject(baseUrl+"genre/"+idGenre, Genre.class);
        TypeLiterature requestGetTypeLiteratureId = getRestTemplate().getForObject(baseUrl+"typeLiterature/"+idTypeLiterature, TypeLiterature.class);
        book.setBookGenre(requestGetGenreId);
        book.setBookTypeLiterature(requestGetTypeLiteratureId);
        Book requestPost = getRestTemplate().postForObject(baseUrl+"book",book, Book.class);
        return "redirect:/Employee/Book/Index";
    }

    @GetMapping("/Book/Index/{id}")
    public String BookDelete(@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(baseUrl+"book/"+id);
        return "redirect:/Employee/Book/Index";
    }

    @GetMapping("/Book/Update/{id}")
    public String BookUpdate(@PathVariable Long id, Model model) {
        Book requestGet = getRestTemplate().getForObject(baseUrl+"book/"+id, Book.class);
        model.addAttribute("object",requestGet);
        Genre[] requestGetCategory = getRestTemplate().getForObject(baseUrl+"genre", Genre[].class);
        TypeLiterature[] requestGetTypeLiterature = getRestTemplate().getForObject(baseUrl+"typeLiterature", TypeLiterature[].class);
        model.addAttribute("objectsGenre",requestGetCategory);
        model.addAttribute("objectsTypeLiterature",requestGetTypeLiterature);
        return "/Employee/Book/Update";
    }

    @PostMapping("/Book/Update/{id}")
    public String BookUpdate(@PathVariable Long id,
                                @RequestParam(name= "idTypeLiterature") Long idTypeLiterature,
                                @RequestParam(name="idGenre") Long idGenre,
                                @Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            Genre[] requestGetGenre = getRestTemplate().getForObject(baseUrl+"genre", Genre[].class);
            TypeLiterature[] requestGetTypeLiterature = getRestTemplate().getForObject(baseUrl+"typeLiterature", TypeLiterature[].class);
            model.addAttribute("objectsGenre",requestGetGenre);
            model.addAttribute("objectsTypeLiterature",requestGetTypeLiterature);
            return "redirect:/Employee/Book/Update/"+id;
        }
        book.setIdBook(id);
        Genre requestGetGenreId = getRestTemplate().getForObject(baseUrl+"genre/"+idGenre, Genre.class);
        TypeLiterature requestGetTypeLiteratureId = getRestTemplate().getForObject(baseUrl+"typeLiterature/"+ idTypeLiterature, TypeLiterature.class);
        book.setBookGenre(requestGetGenreId);
        book.setBookTypeLiterature(requestGetTypeLiteratureId);
        getRestTemplate().put(baseUrl+"book/"+id, book, Void.class);
        return "redirect:/Employee/Book/Index";
    }

    @GetMapping("/Genre/Index")
    public String GenreIndex(Model model) {
        Genre[] requestGet = getRestTemplate().getForObject(baseUrl+"genre", Genre[].class);
        model.addAttribute("objects",requestGet);
        return "/Employee/Genre/Index";
    }

    @GetMapping("/Genre/Add")
    public String GenreAdd(@ModelAttribute("genre") Genre genre, Model model) {
        return "/Employee/Genre/Add";
    }


    @PostMapping("/Genre/Add")
    public String GenrePost(@Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            return "/Employee/Genre/Add";
        }
        Genre requestPost = getRestTemplate().postForObject(baseUrl+"genre",genre, Genre.class);
        return "redirect:/Employee/Genre/Index";
    }

    @GetMapping("/Employee/Index/{id}")
    public String GenreDelete(@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(baseUrl+"genre/"+id);
        return "redirect:/Employee/Genre/Index";
    }

    @GetMapping("/Genre/Update/{id}")
    public String GenreUpdate(@PathVariable Long id, Model model) {
        Genre requestGet = getRestTemplate().getForObject(baseUrl+"genre/"+id, Genre.class);
        model.addAttribute("object",requestGet);
        return "/Employee/Genre/Update";
    }

    @PostMapping("/Genre/Update/{id}")
    public String GenreUpdate(@PathVariable Long id, @Valid Genre genre,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            return "redirect:/Employee/Genre/Update/"+id;
        }
        genre.setIdBookGenre(id);
        getRestTemplate().put(baseUrl+"genre/"+id, genre, Void.class);
        return "redirect:/Employee/Genre/Index";
    }


    @GetMapping("/TypeLiterature/Index")
    public String TypeLiteratureIndex(Model model) {
        TypeLiterature[] requestGet = getRestTemplate().getForObject(baseUrl+"typeLiterature", TypeLiterature[].class);
        model.addAttribute("objects",requestGet);
        return "/Employee/TypeLiterature/Index";
    }

    @GetMapping("/TypeLiterature/Add")
    public String TypeLiteratureAdd(@ModelAttribute("typeLiterature") TypeLiterature typeLiterature, Model model) {
        return "/Employee/TypeLiterature/Add";
    }


    @PostMapping("/TypeLiterature/Add")
    public String TypeLiteraturePost(@Valid @ModelAttribute("typeLiterature") TypeLiterature typeLiterature, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            return "/Employee/TypeLiterature/Add";
        }
        TypeLiterature requestPost = getRestTemplate().postForObject(baseUrl+"typeLiterature",typeLiterature, TypeLiterature.class);
        return "redirect:/Employee/TypeLiterature/Index";
    }

    @GetMapping("/TypeLiterature/Index/{id}")
    public String TypeLiteratureDelete(@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(baseUrl+"typeLiterature/"+id);
        return "redirect:/Employee/TypeLiterature/Index";
    }

    @GetMapping("/TypeLiterature/Update/{id}")
    public String TypeLiteratureUpdate(@PathVariable Long id, Model model) {
        TypeLiterature requestGet = getRestTemplate().getForObject(baseUrl+"typeLiterature/"+id, TypeLiterature.class);
        model.addAttribute("object",requestGet);
        return "/Employee/TypeLiterature/Update";
    }

    @PostMapping("/TypeLiterature/Update/{id}")
    public String TypeLiteratureUpdate(@PathVariable Long id, @Valid TypeLiterature typeLiterature,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            return "redirect:/Employee/TypeLiterature/Update/"+id;
        }
        typeLiterature.setIdBookTypeLiterature(id);
        getRestTemplate().put(baseUrl+"typeLiterature/"+id, typeLiterature, Void.class);
        return "redirect:/Employee/TypeLiterature/Index";
    }

    @GetMapping("/Shop/Index")
    public String ShopIndex(Model model) {
        Shop[] requestGet = getRestTemplate().getForObject(baseUrl+"shop", Shop[].class);
        model.addAttribute("objects",requestGet);
        return "/Employee/Shop/Index";
    }

    @GetMapping("/Shop/Add")
    public String ShopAdd(@ModelAttribute("shop") Shop shop, Model model) {
        Book[] requestGetBook = getRestTemplate().getForObject(baseUrl+"book", Book[].class);
        Schedule[] requestGetSchedule = getRestTemplate().getForObject(baseUrl+"schedule", Schedule[].class);
        model.addAttribute("objectsBook",requestGetBook);
        model.addAttribute("objectsSchedule",requestGetSchedule);
        return "/Employee/Shop/Add";
    }


    @PostMapping("/Shop/Add")
    public String ShopPost(@Valid @ModelAttribute("shop") Shop shop,
                           BindingResult bindingResult,
                           @RequestParam(name="idBook") Long idBook,
                           @RequestParam(name="idSchedule") Long idSchedule,
                           Model model) {
        if (bindingResult.hasErrors())
        {
            Book[] requestGetBook = getRestTemplate().getForObject(baseUrl+"book", Book[].class);
            Schedule[] requestGetSchedule = getRestTemplate().getForObject(baseUrl+"schedule", Schedule[].class);
            model.addAttribute("objectsBook",requestGetBook);
            model.addAttribute("objectsSchedule",requestGetSchedule);
            return "/Employee/Shop/Add";
        }
        Book requestGetBookId = getRestTemplate().getForObject(baseUrl+"book/"+idBook, Book.class);
        Schedule requestGetScheduleId = getRestTemplate().getForObject(baseUrl+"schedule/"+idSchedule, Schedule.class);
        shop.setBook(requestGetBookId);
        shop.setSchedule(requestGetScheduleId);
        Shop requestPost = getRestTemplate().postForObject(baseUrl+"shop",shop, Shop.class);
        return "redirect:/Employee/Shop/Index";
    }

    @GetMapping("/Shop/Index/{id}")
    public String ShopDelete(@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(baseUrl+"shop/"+id);
        return "redirect:/Employee/Shop/Index";
    }

    @GetMapping("/Shop/Update/{id}")
    public String ShopUpdate(@PathVariable Long id, Model model) {
        Shop requestGet = getRestTemplate().getForObject(baseUrl+"shop/"+id, Shop.class);
        model.addAttribute("object",requestGet);
        Book[] requestGetBook = getRestTemplate().getForObject(baseUrl+"book", Book[].class);
        Schedule[] requestGetSchedule = getRestTemplate().getForObject(baseUrl+"schedule", Schedule[].class);
        model.addAttribute("objectsBook",requestGetBook);
        model.addAttribute("objectsSchedule",requestGetSchedule);
        return "/Employee/Shop/Update";
    }

    @PostMapping("/Shop/Update/{id}")
    public String ShopUpdate(@Valid Shop shop,
                             BindingResult bindingResult,
                             @PathVariable Long id,
                             @RequestParam(name="idBook") Long idBook,
                             @RequestParam(name="idSchedule") Long idSchedule,
                             Model model) {
        if (bindingResult.hasErrors())
        {
            Book[] requestGetBook = getRestTemplate().getForObject(baseUrl+"book", Book[].class);
            Schedule[] requestGetSchedule = getRestTemplate().getForObject(baseUrl+"schedule", Schedule[].class);
            model.addAttribute("objectsBook",requestGetBook);
            model.addAttribute("objectsSchedule",requestGetSchedule);
            return "redirect:/Employee/Shop/Update/"+id;
        }
        shop.setIdShop(id);
        Book requestGetBookId = getRestTemplate().getForObject(baseUrl+"book/"+idBook, Book.class);
        Schedule requestGetScheduleId = getRestTemplate().getForObject(baseUrl+"schedule/"+idSchedule, Schedule.class);
        shop.setBook(requestGetBookId);
        shop.setSchedule(requestGetScheduleId);
        getRestTemplate().put(baseUrl+"shop/"+id, shop, Void.class);
        return "redirect:/Employee/Shop/Index";
    }
    //Schedule
    @GetMapping("/Schedule/Index")
    public String ScheduleIndex(Model model) {
        Schedule[] requestGet = getRestTemplate().getForObject(baseUrl+"schedule", Schedule[].class);
        model.addAttribute("objects",requestGet);
        return "/Employee/Schedule/Index";
    }


    @GetMapping("/Schedule/Add")
    public String ScheduleAdd(@ModelAttribute("schedule") Schedule schedule, Model model) {
        return "/Employee/Schedule/Add";
    }


    @PostMapping("/Schedule/Add")
    public String SchedulePost(@Valid @ModelAttribute("schedule") Schedule schedule, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            return "/Employee/Schedule/Add";
        }
        Schedule requestPost = getRestTemplate().postForObject(baseUrl+"schedule",schedule, Schedule.class);
        return "redirect:/Employee/Schedule/Index";
    }

    @GetMapping("/Schedule/Index/{id}")
    public String ScheduleDelete(@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(baseUrl+"schedule/"+id);
        return "redirect:/Employee/Schedule/Index";
    }

    @GetMapping("/Schedule/Update/{id}")
    public String ScheduleUpdate(@PathVariable Long id, Model model) {
        Schedule requestGet = getRestTemplate().getForObject(baseUrl+"schedule/"+id, Schedule.class);
        model.addAttribute("object",requestGet);
        return "/Employee/Schedule/Update";
    }

    @PostMapping("/Schedule/Update/{id}")
    public String ScheduleUpdate(@PathVariable Long id, @Valid Schedule schedule, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            return "redirect:/Employee/Schedule/Update/"+id;
        }
        schedule.setIdSchedule(id);
        getRestTemplate().put(baseUrl+"schedule/"+id, schedule, Void.class);
        return "redirect:/Employee/Schedule/Index";
    }
}
