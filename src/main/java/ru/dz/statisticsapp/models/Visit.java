package ru.dz.statisticsapp.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 07.03.2018
 *
 * @author Robert Bagramov.
 */
@Data
@Builder
public class Visit {
    private long id;
    private int userId;
    private String url;
    private Timestamp date;
}
