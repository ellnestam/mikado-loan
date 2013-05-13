package com.ssem.loan;

public class MemoryLoanRepository implements LoanRepository {

    @Override
    public Ticket store(LoanApplication application) {

        return new Ticket(1);
    }

    @Override
    public Ticket approve(String ticketId) {
        return new Ticket(1);
    }

}
