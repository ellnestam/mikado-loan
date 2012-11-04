package com.ssem.loan;

public class Application {

    private long applicationNo;
    private long amount;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    private String email;
    private boolean approved;
    private String contact;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Application(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void approve() {
        setApproved(true);
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setContact(String who) {
        this.contact = who;
    }

    public String getContact() {
        return contact;
    }

}
