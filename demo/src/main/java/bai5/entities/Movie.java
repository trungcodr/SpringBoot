package bai5.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Movie {
    private int id;
    private String name;
    private String genre;
    private String director;
    private int duration;
    private String description;
    private LocalDate publishedDate;
    private String category;
}
