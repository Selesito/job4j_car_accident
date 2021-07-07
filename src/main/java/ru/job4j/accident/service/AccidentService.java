package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccidentService {
    @Autowired
    private AccidentRepository accidentRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private AccidentTypeRepository accidentTypeRepository;

    public AccidentService(AccidentRepository accidentRepository,
                           RuleRepository ruleRepository,
                           AccidentTypeRepository accidentTypeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.accidentTypeRepository = accidentTypeRepository;
    }

    public List<Accident> findAllAccident() {
        return accidentRepository.getAccident();
    }

    public Collection<AccidentType> findAllTypes() {
        List<AccidentType> types = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(types::add);
        return types;
    }

    public Collection<Rule> findAllRules() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }

    public void save(Accident accident, String[] ids) {
        AccidentType accidentType = accidentTypeRepository.getTypeId(accident.getType().getId());
        accident.setType(accidentType);
        int[] idRules = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
        List<Integer> id = IntStream.of(idRules).boxed().collect(Collectors.toList());
        if (accident.getId() == 0) {
            accident = accidentRepository.save(accident);
        }
        accident.setRules(new HashSet<>((List<Rule>) ruleRepository.findAllById(id)));
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.getAccidentById(id);
    }
}

