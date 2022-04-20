package com.milk.pay.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.milk.pay.entities.Title;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TitleRepository implements PanacheRepository<Title> {

    public List<Title> findByUserIdBetwenDates(Map<String, Object> params) {
        var query = new StringBuilder();

        query.append("user.id = :userId and liquidated = :liquidated ");

        if (params.get("offset") != null
                && params.get("limit") != null) {
            query.append("and dueDate >= :offset and dueDate <= :limit");
        }

        return list(query.toString(), params);
    }

    public List<Title> findAllByUserId(UUID userId) {
        return list("user.id", userId);
    }

}
