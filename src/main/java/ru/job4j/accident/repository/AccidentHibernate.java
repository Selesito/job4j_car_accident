package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident crate(Accident accident) {
        return this.tx(session -> {
            session.save(accident);
            return accident;
        });
    }

    public Accident update(Accident accident) {
        return this.tx(session -> {
            session.update(accident);
            return accident;
        });
    }

    public List<Accident> getAllAccident() {
        return this.tx(session ->
            session.createQuery("select distinct a from Accident a "
                    + "left join fetch a.type t "
                    + "left join fetch a.rules r ", Accident.class)
                    .list()
        );
    }

    public List<AccidentType> getAllTypes() {
        return this.tx(session ->
            session.createQuery("from AccidentType", AccidentType.class)
                    .list()
        );
    }

    public List<Rule> getAllRules() {
        return this.tx(session ->
                session.createQuery("from Rule", Rule.class)
                .list()
        );
    }

    public Accident findAccidentById(int id) {
        return (Accident) this.tx(session ->
                session.createQuery("from Accident a "
                        + "left join fetch a.type t "
                        + "left join fetch a.rules r "
                        + "where a.id = :paramId")
                        .setParameter("paramId", id).uniqueResult()
        );
    }

    public AccidentType findTypeById(int id) {
        return (AccidentType) this.tx(session ->
                session.createQuery("from AccidentType "
                        + "where id = :paramId")
                        .setParameter("paramId", id).uniqueResult()
        );
    }

    public Set<Rule> findRulesById(List<Integer> id) {
        return new HashSet<>(this.tx(session ->
                session.createQuery("from Rule "
                        + "where id = :paramId")
                        .setParameter("paramId", id)
                        .getResultList())
        );
    }
}