package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentMem {
    private static final AtomicInteger ID = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    private AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        types.put(4, AccidentType.of(4, "Машина и олень"));
        types.put(5, AccidentType.of(5, "Человек и велосипед"));
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

   public AccidentType findAccidentTypeById(int id) {
        return types.get(id);
   }

    public List<AccidentType> getTypes() {
        return new ArrayList<>(types.values());
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules.values());
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
