package com.school.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LibraryTransactionRequest {

    private int book_id;

    private int student_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date borrowed_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date returned_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date due_date;

    private int fine;

    public int getBook_id() {
        return book_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public Date getBorrowed_date() {
        return borrowed_date;
    }

    public Date getReturned_date() {
        return returned_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public int getFine() {
        return fine;
    }

    @Override
    public String toString() {
        return "LibraryTransactionRequest{" +
                "book_id=" + book_id +
                ", student_id=" + student_id +
                ", borrowed_date=" + borrowed_date +
                ", returned_date=" + returned_date +
                ", due_date=" + due_date +
                ", fine=" + fine +
                '}';
    }
}
