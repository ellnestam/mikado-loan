package com.ssem.loan;

public interface LoanRepository {

    Ticket store(LoanApplication application);

    Ticket approve(String ticketId);

}