package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccidentService {

    private final AccidentHibernate hbm;

    public AccidentService(AccidentHibernate jdbc) {
        this.hbm = jdbc;
    }

    public List<Accident> findAllAccident() {
        return hbm.getAllAccident();
    }

    public Collection<AccidentType> findAllTypes() {
        return hbm.getAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return hbm.getAllRules();
    }

    public Accident findAccidentById(int id) {
        return hbm.findAccidentById(id);
    }

    public void save(Accident accident, String[] ids) {
        AccidentType accidentType = hbm.findTypeById(accident.getType().getId());
        accident.setType(accidentType);
        int[] idRules = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
        List<Integer> id = IntStream.of(idRules).boxed().collect(Collectors.toList());
        accident.setRules(hbm.findRulesById(id));
        if (accident.getId() == 0) {
            hbm.crate(accident);
        } else {
            hbm.update(accident);
        }
    }
}
