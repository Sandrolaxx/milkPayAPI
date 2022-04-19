package com.milk.pay.repository;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.milk.pay.entities.Title;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TitleRepository implements PanacheRepository<Title> {

    public List<Title> findByUserIdBetwenDates(Map<String, Object> params) {
        var query = new StringBuilder();

        query.append("user.id = ?1 and liquidated = ?2");

        if (params.get("offset") != null
                && params.get("offset") != null) {
            query.append("and dueDate > ?3 and dueDate <= ?4");
        }

        return list(query.toString(), params);
    }

}
