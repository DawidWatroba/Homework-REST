package pl.dawid.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dawid.model.Flight;
import pl.dawid.repository.FlightRepository;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightControllerMvc {

    private FlightRepository flightRepo;

    public FlightControllerMvc(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    @GetMapping
    public String showFlights(Model model){
        List<Flight> flights = flightRepo.findAll();
        model.addAttribute("flights", flights);
        return "flightsList";
    }

    @PostMapping
    public String addFlight(@ModelAttribute Flight modelFlight, RedirectAttributes redirectAttr){
        flightRepo.save(modelFlight);
        redirectAttr.addFlashAttribute("message", "Flight added successfully");
        return "redirect:/";
    }
}
