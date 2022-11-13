package com.aktie.aktiepay.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.enums.EnumFilterTitle;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class TitleRepository implements PanacheRepository<Title> {

    public List<Title> findByUserIdBetwenDates(Map<String, Object> params, Integer pageIndex,
            Integer pageSize, EnumFilterTitle filterBy) {
        var query = new StringBuilder();
        var defaultPageSize = 5;
        pageSize = pageSize == null ? defaultPageSize : pageSize;

        query.append("user.id = :userId");

        if (params.get("liquidated") != null) {
            query.append(" and liquidated = :liquidated");
        }

        if (filterBy != null
                && params.get(filterBy.getValue()) != null) {
            query.append(" and ".concat(filterBy.getValue()).concat(" = :").concat(filterBy.getValue()));
        }

        if (params.get("offset") != null
                && params.get("limit") != null) {
            query.append(" and dueDate >= :offset and dueDate <= :limit");
        }

        if (pageIndex != null) {
            return find(query.toString(), params)
                    .page(pageIndex, pageSize)
                    .list();
        }

        return list(query.toString(), params);
    }

    public List<Title> findAllByUserId(UUID userId) {
        return list("user.id", userId);
    }

}
