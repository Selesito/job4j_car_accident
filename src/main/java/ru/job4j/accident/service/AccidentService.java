package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate jdbc;

    public AccidentService(AccidentJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> findAllAccident() {
        return jdbc.getAllAccident();
    }

    public Collection<AccidentType> findAllTypes() {
        return jdbc.getAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return jdbc.getAllRules();
    }

    public Accident findAccidentById(int id) {
        return jdbc.findAccidentById(id);
    }

    public void save(Accident accident, String[] ids) {
        String idRules = "";
        for (String rsl : ids) {
            idRules += rsl + " ";
        }
        jdbc.save(accident, idRules);
    }
}
