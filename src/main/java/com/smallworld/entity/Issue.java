package com.smallworld.entity;

public class Issue {
    
    int issueId;
	boolean issueResolved = true;
	String issueMessage;

    @Override
    public String toString() {
        return "Issue [issueId=" + issueId + ", issueResolved=" + issueResolved + ", issueMessage=" + issueMessage
                + "]";
    }

    public Issue() {
        this.issueResolved = true;
    }

    public Issue(boolean issueResolved, String issueMessage) {
        this.issueResolved = issueResolved;
        this.issueMessage = issueMessage;
    }

    public Issue(int issueId, boolean issueResolved, String issueMessage) {
        this.issueId = issueId;
        this.issueResolved = issueResolved;
        this.issueMessage = issueMessage;
    }

    public Issue(int issueId, boolean issueResolved) {
        this.issueId = issueId;
        this.issueResolved = true;
    }

    public int getIssueId() {
        return issueId;
    }
    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
    public boolean getIssueResolved() {
        return issueResolved;
    }
    public void setIssueResolved(boolean issueResolved) {
        this.issueResolved = issueResolved;
    }
    public String getIssueMessage() {
        return issueMessage;
    }
    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }

    
}
