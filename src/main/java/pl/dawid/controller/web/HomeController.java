package pl.dawid.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dawid.model.Flight;
import pl.dawid.model.Tourist;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("modelFlight", new Flight());
        model.addAttribute("modelTourist", new Tourist());

        return "index";
    }
}
