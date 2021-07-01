package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ID = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();

    private AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        types.put(4, AccidentType.of(4, "Машина и олень"));
        types.put(5, AccidentType.of(5, "Человек и велосипед"));
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
}
