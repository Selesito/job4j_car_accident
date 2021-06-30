package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ID = new AtomicInteger(4);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(0, "Скорость",
                "Первысил допустимую в 2 раза", "Novosibirsk"));
        accidents.put(2, new Accident(0, "Стоп линия",
                "Выезд на стоп линию", "Novosibirsk"));
    }

    public static AccidentMem instOf() {
        return Lazy.INST;
    }

    private static final class Lazy {
        private final static AccidentMem INST = new AccidentMem();
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }
}
