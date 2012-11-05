package com.ssem.loan;

import java.io.File;
import java.io.FileFilter;

public class Application {

    private long applicationNo;
    private long amount;
    private String email;
    private String contact;
    private boolean approved;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void getNextId() {
        File file = new File(LoanRepository.REPOSITORY_ROOT);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(LoanRepository.FILE_EXTENSION);
            }
        });

        long id = files == null ? 0 : files.length + 1;

        setApplicationNo(id);
    }

}
