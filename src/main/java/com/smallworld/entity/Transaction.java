package com.smallworld.entity;

public class Transaction {
    public int mtn;
    Double amount;

    public Sender sender;
    public Beneficiary beneficiary;
    Issue issue;

    @Override
    public String toString() {
        return "Transaction [mtn=" + mtn + ", amount=" + amount + ", sender=" + sender + ", beneficiary=" + beneficiary
                + ", issue=" + issue + "]";
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Transaction(int mtn, Double amount, Sender sender, Beneficiary beneficiary) {
        this.mtn = mtn;
        this.amount = amount;
        this.sender = sender;
        this.beneficiary = beneficiary;
    }

    public Double getAmount() {
        return amount;
    }

}
