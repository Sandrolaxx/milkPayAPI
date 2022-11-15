package com.aktie.aktiepay.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.enums.EnumFilterTitle;
import com.aktie.aktiepay.utils.EnumUtil;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;

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

        if (params.containsKey("liquidated")) {
            query.append(" AND liquidated = :liquidated");
        }

        if (filterBy != null
                && params.containsKey(filterBy.getValue())) {
            query = addFilterByParams(params, query, filterBy);
        }

        if (params.containsKey("offset")
                && params.containsKey("limit")) {
            query.append(" AND dueDate >= :offset AND dueDate <= :limit");
        }

        if (pageIndex != null) {
            return find(query.toString(), Sort.by("id", Direction.Descending), params)
                    .page(pageIndex, pageSize)
                    .list();
        }

        return list(query.toString(), params);
    }

    public List<Title> findAllByUserId(UUID userId) {
        return list("user.id", userId);
    }

    public Integer getQueryAllResultsLenght(Map<String, Object> params, EnumFilterTitle filterBy) {
        var query = new StringBuilder();

        query.append("user.id = :userId ");

        if (params.get("liquidated") != null) {
            query.append("and liquidated = :liquidated ");
        }

        if (filterBy != null
                && params.containsKey(filterBy.getValue())) {
            query = addFilterByParams(params, query, filterBy);
        }

        if (params.get("offset") != null
                && params.get("limit") != null) {
            query.append("and dueDate >= :offset and dueDate <= :limit");
        }

        return Long.valueOf(count(query.toString(), params)).intValue();
    }

    private StringBuilder addFilterByParams(Map<String, Object> params, StringBuilder query, EnumFilterTitle filterBy) {
        if (EnumUtil.isEquals(filterBy, EnumFilterTitle.DUE_DATE)
                || EnumUtil.isEquals(filterBy, EnumFilterTitle.INCLUSION_DATE)) {
            query.append(" AND ".concat(filterBy.getValue()));
            query.append(" >= :".concat(filterBy.getValue()));

            if (params.containsKey("auxDate")) {
                query.append(" AND ".concat(filterBy.getValue()));
                query.append(" <= :".concat("auxDate"));
            }

        } else {
            query.append(" AND ".concat(filterBy.getValue()).concat(" = :").concat(filterBy.getValue()));
        }

        return query;
    }

}
