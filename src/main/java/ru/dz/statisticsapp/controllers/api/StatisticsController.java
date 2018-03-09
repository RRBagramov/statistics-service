package ru.dz.statisticsapp.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dz.statisticsapp.dto.DailyStatisticsDto;
import ru.dz.statisticsapp.dto.PeriodStatisticsDto;
import ru.dz.statisticsapp.models.Visit;
import ru.dz.statisticsapp.services.StatisticsService;

/**
 * 06.03.2018
 *
 * @author Robert Bagramov.
 */
@RestController
@RequestMapping("/api")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/visit", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public DailyStatisticsDto userVisitPage(@RequestParam("userId") int userId,
                                            @RequestParam("pageURL") String pageURL) {
        Visit visit = Visit.builder()
                .userId(userId)
                .url(pageURL)
                .build();

        statisticsService.create(visit);
        return statisticsService.getDailyStatistics();
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PeriodStatisticsDto getPeriodStatistics(@RequestParam("from") String dateFrom,
                                                   @RequestParam("to") String dateTo) {
        return statisticsService.getPeriodStatistics(dateFrom, dateTo);
    }
}
