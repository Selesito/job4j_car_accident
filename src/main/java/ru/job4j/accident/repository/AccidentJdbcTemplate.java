package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAllAccident() {
        return jdbc.query("select * from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("type_id")));
                    accident.setRules(rulesAccident(rs.getString("rules")));
                    return accident;
                });
    }

    public List<Rule> getAllRules() {
        return  jdbc.query(
                "select * from rules",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    public List<AccidentType> getAllTypes() {
        return  jdbc.query(
                "select * from types",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public Accident findAccidentById(int id) {
        return  jdbc.queryForObject(
                "select id, name from accident where id = ?",
                new BeanPropertyRowMapper<>(Accident.class),
                id
        );
    }

    public Rule findRuleById(int id) {
        return  jdbc.queryForObject(
                "select id, name from rules where id = ?",
                new BeanPropertyRowMapper<>(Rule.class),
                id
        );
    }

    public AccidentType findTypeById(int id) {
        return  jdbc.queryForObject(
                "select id, name from types where id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class),
                id
        );
    }

    private Set<Rule> rulesAccident(String ids) {
        Set<Rule> rules = new HashSet<>();
        String[] id = ids.split(" ");
        for (String rsl : id) {
            rules.add(findRuleById(Integer.parseInt(rsl)));
        }
        return rules;
    }

    public void save(Accident accident, String ids) {
        if (accident.getId() == 0) {
            create(accident, ids);
        } else {
            update(accident, ids);
        }
    }

    private Accident create(Accident accident, String ids) {
        jdbc.update("INSERT INTO accident (name, text, address, type_id, rules)"
                        + " values (?, ?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                ids);
        return accident;
    }

    private Accident update(Accident accident, String ids) {
        jdbc.update("UPDATE accident (name, text, address, type_id, rules where id = ?)"
                        + " values (?, ?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                ids,
                accident.getId());
        return accident;
    }
}
