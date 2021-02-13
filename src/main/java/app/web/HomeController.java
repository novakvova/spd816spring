package app.web;

import app.entities.User;
import app.repositories.UserRepository;
import app.seeder.SeederDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final SeederDb seederDb;

    @Autowired
    public HomeController(UserRepository userRepository,
                          SeederDb seederDb) {
        this.userRepository = userRepository;
        this.seederDb=seederDb;
        this.seederDb.SeedAllTabels();
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> users =  userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid User user, BindingResult result, Model model) {
        if(result.hasErrors())
            return "create";

        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
