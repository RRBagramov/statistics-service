package ru.dz.statisticsapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dz.statisticsapp.dao.VisitDao;
import ru.dz.statisticsapp.models.Visit;

import java.util.HashMap;
import java.util.Map;

/**
 * 07.03.2018
 *
 * @author Robert Bagramov.
 */
@Repository
public class StatisicsDaoImpl implements VisitDao {
    private static final String CREATE_VISIT_SQL =
            "INSERT INTO visits(user_id, url) " +
                    "VALUES (:userId, :url);";
    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public void create(Visit visit) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", visit.getUserId());
        params.put("url", visit.getUrl());

        namedJdbcTemplate.update(CREATE_VISIT_SQL, params);
    }

}
