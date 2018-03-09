package ru.dz.statisticsapp.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 07.03.2018
 *
 * @author Robert Bagramov.
 */
@Data
@Builder
public class PeriodStatisticsDto {
    private int totalNumberOfVisits;
    private int numberOfUniqueUsers;
    private int numberOfRegularUsers;
}
