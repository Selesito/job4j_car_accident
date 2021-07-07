package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
    @Query("from AccidentType where id = :id")
    AccidentType getTypeId(@Param("id") int id);
}
