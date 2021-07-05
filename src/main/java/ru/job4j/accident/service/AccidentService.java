package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void save(Accident accident, String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String rsl : ids) {
            Rule rule = accidentMem.findRuleById(Integer.parseInt(rsl));
            rules.add(rule);
        }
        AccidentType type = accidentMem.findAccidentTypeById(accident.getType().getId());
        accident.setType(type);
        accident.setRules(rules);
        accidentMem.saveAccident(accident);
    }

    public List<AccidentType> findAllTypes() {
        return accidentMem.getTypes();
    }

    public List<Rule> findAllRules() {
        return accidentMem.getRules();
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findAccidentById(id);
    }
}
