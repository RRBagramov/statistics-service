package ru.dz.statisticsapp.services;

import ru.dz.statisticsapp.dto.DailyStatisticsDto;
import ru.dz.statisticsapp.dto.PeriodStatisticsDto;
import ru.dz.statisticsapp.models.Visit;

/**
 * 09.03.2018
 *
 * @author Robert Bagramov.
 */
public interface StatisticsService {
    void create(Visit visit);

    DailyStatisticsDto getDailyStatistics();

    PeriodStatisticsDto getPeriodStatistics(String dateFrom, String dateTo);
}
