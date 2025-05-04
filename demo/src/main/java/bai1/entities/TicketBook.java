package bai1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor

public class TicketBook {
    private int id;
    private int bookId;
    private int personId;
    private String note;
    private Date borrowDate;
    private Date returnDate;
}
