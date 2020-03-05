package pl.dawid.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dawid.model.Tourist;
import pl.dawid.repository.TouristRepository;

import java.util.List;

@Controller
@RequestMapping("/tourists")
public class TouristControllerMvc {

    private TouristRepository touristRepo;

    @Autowired
    public TouristControllerMvc(TouristRepository touristRepo) {
        this.touristRepo = touristRepo;
    }

    @GetMapping
    public String listTourist(Model model){
        List<Tourist> tourists = touristRepo.findAll();
        model.addAttribute("tourists", tourists);
        return "touristsList";
    }
    @PostMapping
    public String addTourist(@ModelAttribute Tourist modelTourist, RedirectAttributes redirectAttr){
        touristRepo.save(modelTourist);
        redirectAttr.addFlashAttribute("message", "Tourist added successfully to data base.");
        return "redirect:/";
    }



}
