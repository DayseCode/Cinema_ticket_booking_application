package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "movies", schema = "bookingdb")
public class MoviesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Basic
    @Column(name = "movieName")
    private String movieName;
    @Basic
    @Column(name = "movieGenre")
    private String movieGenre;
    @Basic
    @Column(name = "agelimit")
    private Integer agelimit;
    @Basic
    @Column(name = "dateMovie")
    private Date dateMovie;
    @Basic
    @Column(name = "actorsCast")
    private String actorsCast;
    @Basic
    @Column(name = "ticketPrice")
    private Double ticketPrice;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public Integer getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(Integer agelimit) {
        this.agelimit = agelimit;
    }

    public Date getDateMovie() {
        return dateMovie;
    }

    public void setDateMovie(Date dateMovie) {
        this.dateMovie = dateMovie;
    }

    public String getActorsCast() {
        return actorsCast;
    }

    public void setActorsCast(String actorsCast) {
        this.actorsCast = actorsCast;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesEntity that = (MoviesEntity) o;
        return movieId == that.movieId && Objects.equals(movieName, that.movieName) && Objects.equals(movieGenre, that.movieGenre) && Objects.equals(agelimit, that.agelimit) && Objects.equals(dateMovie, that.dateMovie) && Objects.equals(actorsCast, that.actorsCast) && Objects.equals(ticketPrice, that.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieGenre, agelimit, dateMovie, actorsCast, ticketPrice);
    }
}
