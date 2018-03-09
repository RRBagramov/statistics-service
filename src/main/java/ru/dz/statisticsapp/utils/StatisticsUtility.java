package ru.dz.statisticsapp.utils;

import ru.dz.statisticsapp.dto.DailyStatisticsDto;
import ru.dz.statisticsapp.dto.PeriodStatisticsDto;

/**
 * 07.03.2018
 *
 * @author Robert Bagramov.
 */
public interface StatisticsUtility {
    DailyStatisticsDto getDailyStatistics();

    PeriodStatisticsDto getPeriodStatistics(String dateFrom, String dateTo);
}
