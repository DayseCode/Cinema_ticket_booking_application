package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ticket", schema = "bookingdb")
public class TicketEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ticket_id")
    private int ticketId;
    @Basic
    @Column(name = "customer_id")
    private Integer customerId;
    @Basic
    @Column(name = "movie_id")
    private Integer movieId;
    @Basic
    @Column(name = "seatRow")
    private Integer seatRow;
    @Basic
    @Column(name = "seatPlace")
    private Integer seatPlace;
    @Basic
    @Column(name = "dateOfBook")
    private Date dateOfBook;
    @Basic
    @Column(name = "ticketReturn")
    private Boolean ticketReturn;
    @Basic
    @Column(name = "dateOfReturn")
    private Date dateOfReturn;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public void setSeatPlace(Integer seatPlace) {
        this.seatPlace = seatPlace;
    }

    public Date getDateOfBook() {
        return dateOfBook;
    }

    public void setDateOfBook(Date dateOfBook) {
        this.dateOfBook = dateOfBook;
    }

    public Boolean getTicketReturn() {
        return ticketReturn;
    }

    public void setTicketReturn(Boolean ticketReturn) {
        this.ticketReturn = ticketReturn;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return ticketId == that.ticketId && Objects.equals(customerId, that.customerId) && Objects.equals(movieId, that.movieId) && Objects.equals(seatRow, that.seatRow) && Objects.equals(seatPlace, that.seatPlace) && Objects.equals(dateOfBook, that.dateOfBook) && Objects.equals(ticketReturn, that.ticketReturn) && Objects.equals(dateOfReturn, that.dateOfReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, customerId, movieId, seatRow, seatPlace, dateOfBook, ticketReturn, dateOfReturn);
    }
}
