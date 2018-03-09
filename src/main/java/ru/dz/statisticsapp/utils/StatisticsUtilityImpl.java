package ru.dz.statisticsapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dz.statisticsapp.dto.DailyStatisticsDto;
import ru.dz.statisticsapp.dto.PeriodStatisticsDto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 07.03.2018
 *
 * @author Robert Bagramov.
 */
@Component
public class StatisticsUtilityImpl implements StatisticsUtility {
    private static final String NUMBER_OF_REGULAR_USERS_PER_DAY_SQL =
            "SELECT count(*) " +
                    "FROM (SELECT count(user_id) " +
                    "      FROM (SELECT " +
                    "              user_id, url " +
                    "            FROM public.visits v " +
                    "            WHERE date_trunc('day', v.date) = date_trunc('day', current_timestamp) " +
                    "            GROUP BY v.user_id, v.url) AS unic_url " +
                    "      GROUP BY unic_url.user_id " +
                    "      HAVING count(unic_url.url) >= 10) AS us_count";

    private static final String TOTAL_NUMBER_OF_VISITS_PER_DAY_SQL =
            "SELECT count(*) " +
                    "FROM " +
                    "(SELECT * " +
                    "FROM public.visits v " +
                    "WHERE date_trunc('day', v.date) = date_trunc('day', current_timestamp)) AS daily_visits;";

    private static final String TOTAL_NUMBER_OF_USERS_PER_DAY_SQL =
            "SELECT count(DISTINCT user_id) " +
                    "FROM public.visits v " +
                    "WHERE date_trunc('day', v.date) = date_trunc('day', current_timestamp);";

    private static final String TOTAL_NUMBER_OF_VISITS_OF_PERIOD_SQL =
            "SELECT count(*) " +
                    "FROM public.visits v " +
                    "WHERE date_trunc('day', v.date) >= :dateFrom " +
                    "AND date_trunc('day', v.date) <= :dateTo;";

    private static final String NUMBER_OF_REGULAR_USERS_OF_PERIOD_SQL =
            "SELECT count(*) " +
                    "FROM (SELECT count(user_id) " +
                    "      FROM (SELECT " +
                    "              user_id, url " +
                    "            FROM public.visits v " +
                    "            WHERE date_trunc('day', v.date) >= :dateFrom " +
                    "            AND date_trunc('day', v.date) <= :dateTo " +
                    "            GROUP BY v.user_id, v.url) AS unic_url " +
                    "      GROUP BY unic_url.user_id " +
                    "      HAVING count(unic_url.url) >= 10) AS us_count";

    private static final String TOTAL_NUMBER_OF_USERS_OF_PERIOD_SQL =
            "SELECT count(DISTINCT user_id) " +
                    "FROM public.visits v " +
                    "WHERE date_trunc('day', v.date) >= :dateFrom " +
                    "AND date_trunc('day', v.date) <= :dateTo;";

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public DailyStatisticsDto getDailyStatistics() {
        int totalNumberOfVisits = jdbcTemplate
                .queryForObject(TOTAL_NUMBER_OF_VISITS_PER_DAY_SQL, int.class);

        int totalNumberOfUsers = jdbcTemplate
                .queryForObject(TOTAL_NUMBER_OF_USERS_PER_DAY_SQL, int.class);

        int numberOfRegularUsers = jdbcTemplate
                .queryForObject(NUMBER_OF_REGULAR_USERS_PER_DAY_SQL, int.class);

        int numberOfUniqueUsers = totalNumberOfUsers - numberOfRegularUsers;

        return DailyStatisticsDto.builder()
                .totalNumberOfVisits(totalNumberOfVisits)
                .numberOfUniqueUsers(numberOfUniqueUsers)
                .build();
    }

    @Override
    public PeriodStatisticsDto getPeriodStatistics(String dateFrom, String dateTo) {
        Timestamp tmstmpDateFrom = convertFromStringToDate(dateFrom);
        Timestamp tmstmpDateTo = convertFromStringToDate(dateTo);

        HashMap<String, Object> params = new HashMap<>();
        params.put("dateFrom", tmstmpDateFrom);
        params.put("dateTo", tmstmpDateTo);

        int totalNumberOfVisits = namedJdbcTemplate
                .queryForObject(TOTAL_NUMBER_OF_VISITS_OF_PERIOD_SQL, params, int.class);

        int totalNumberOfUsers = namedJdbcTemplate
                .queryForObject(TOTAL_NUMBER_OF_USERS_OF_PERIOD_SQL, params, int.class);

        int numberOfRegularUsers = namedJdbcTemplate
                .queryForObject(NUMBER_OF_REGULAR_USERS_OF_PERIOD_SQL, params, int.class);

        int numberOfUniqueUsers = totalNumberOfUsers - numberOfRegularUsers;

        return PeriodStatisticsDto.builder()
                .totalNumberOfVisits(totalNumberOfVisits)
                .numberOfUniqueUsers(numberOfUniqueUsers)
                .numberOfRegularUsers(numberOfRegularUsers)
                .build();
    }

    private Timestamp convertFromStringToDate(String date) {
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate;

        try {
            formattedDate = datetimeFormatter.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Not correct date format.");
        }

        return new Timestamp(formattedDate.getTime());
    }
}
