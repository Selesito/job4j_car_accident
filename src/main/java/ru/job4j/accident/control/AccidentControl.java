package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem accidentMem;

    public AccidentControl(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentMem.getTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        AccidentType type = accidentMem.findAccidentTypeById(accident.getType().getId());
        accident.setType(type);
        accidentMem.saveAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentMem.findAccidentById(id));
        return "accident/update";
    }
}
