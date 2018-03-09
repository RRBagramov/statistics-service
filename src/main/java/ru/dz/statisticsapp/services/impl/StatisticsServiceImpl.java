package ru.dz.statisticsapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.statisticsapp.dao.VisitDao;
import ru.dz.statisticsapp.dto.DailyStatisticsDto;
import ru.dz.statisticsapp.dto.PeriodStatisticsDto;
import ru.dz.statisticsapp.models.Visit;
import ru.dz.statisticsapp.services.StatisticsService;
import ru.dz.statisticsapp.utils.StatisticsUtility;

/**
 * 09.03.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    StatisticsUtility statisticsUtility;

    @Autowired
    VisitDao visitDao;

    @Override
    public void create(Visit visit) {
        visitDao.create(visit);
    }

    @Override
    public DailyStatisticsDto getDailyStatistics() {
        return statisticsUtility.getDailyStatistics();
    }

    @Override
    public PeriodStatisticsDto getPeriodStatistics(String dateFrom, String dateTo) {
        return statisticsUtility.getPeriodStatistics(dateFrom, dateTo);
    }

}
