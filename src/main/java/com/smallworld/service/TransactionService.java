package com.smallworld.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import com.smallworld.entity.Beneficiary;
import com.smallworld.entity.Issue;
import com.smallworld.entity.Sender;
import com.smallworld.entity.Transaction;

public class TransactionService {
    public List<Transaction> transactions;


    public TransactionService() {
        this.transactions = new ArrayList<Transaction>();
    }


    public void creatTransaction(int mtn, double amount, String senderfullname, int senderAge, String beneficiaryFullname,
    int beneficiaryAge, int issueId, boolean issueResolved, String issueMessage) {
        
        // add validation later
        Sender sender = new Sender(senderfullname, senderAge);
        Beneficiary beneficary = new Beneficiary(beneficiaryFullname, beneficiaryAge);
        Transaction t = new Transaction(mtn, amount, sender, beneficary);
        Issue is = new Issue(issueId, issueResolved, issueMessage);
        t.setIssue(is);
        transactions.add(t);
    }

    public void creatTransaction(int mtn, double amount, String senderfullname, int senderAge, String beneficiaryFullname,
    int beneficiaryAge) {
        
        // add validation later
        Sender sender = new Sender(senderfullname, senderAge);
        Beneficiary beneficary = new Beneficiary(beneficiaryFullname, beneficiaryAge);
        Transaction t = new Transaction(mtn, amount, sender, beneficary);
        Issue is = new Issue();
        t.setIssue(is);
        transactions.add(t);
    }


    public List<Transaction> getAllTransactions() {
		return transactions;
    }

    public void printAllTransactions() {
		for (Transaction i : transactions) {
            System.out.println(i.toString());
        }
    }

    @Override
    public String toString() {
        return "TransactionService [transactions=" + transactions + "]";
    }
    
    public boolean removeTransactionByMTN(int mtn) {
		return this.transactions.removeIf(t -> t.mtn == mtn); 
	}

    public List<Transaction> SearchTransactionBySenderName(String fullName) {
        return this.transactions.stream().filter(t -> t.sender.getSender().getFullname().equals(fullName)).collect(Collectors.toList());
	}

    public List<Transaction> SearchTransactionByBeneficaryName(String fullName) {
        return this.transactions.stream().filter(t -> t.beneficiary.getBeneficiary().getFullname().equals(fullName)).collect(Collectors.toList());
	}

    public List<Transaction> returnAllUnSolvedTransactions() {
        return this.transactions.stream().filter(t -> t.getIssue().getIssueId() != 0 && t.getIssue().getIssueResolved() == false )
        .collect(Collectors.toList());
	}

    public List<Transaction> returnAllUnSolvedTransactionsByGiveList(List<Transaction> list) {
        return list.stream().filter(t -> t.getIssue().getIssueId() != 0 && t.getIssue().getIssueResolved() == false )
        .collect(Collectors.toList());
	}

    public List<Transaction> sortTransactionsByAmountReverseOrder() {
        return this.transactions.stream().sorted( (o1, o2)-> - o1.getAmount().compareTo( o2.getAmount() ))
        .collect(Collectors.toList());
	}
    
    public List<Transaction> returnAllSolvedTransactions() {
        return this.transactions.stream().filter(t -> t.getIssue().getIssueId() != 0 && t.getIssue().getIssueResolved() == true )
        .collect(Collectors.toList());
	}

    public List<Transaction> SearchTransactionByName(String fullName) {
        List<Transaction> sender = this.transactions.stream().filter(t -> t.sender.getSender().getFullname().equals(fullName)).collect(Collectors.toList());
        List<Transaction> beneficiary = this.transactions.stream().filter(t -> t.beneficiary.getBeneficiary().getFullname().equals(fullName)).collect(Collectors.toList());

        List<Transaction> merge = new ArrayList<Transaction>();
        merge.addAll(sender);
        merge.addAll(beneficiary);
        
        return merge;
	}

    // Need to integrate JSON Parser library like jackson, simplejson etc
    // public List<Transaction> ReadTransaction() {
    //     JSONParser parser = new JSONParser();
    //     try {
    //         Object obj = parser.parse(new FileReader("transactions.json"));
    //         JSONObject jsonObject = (JSONObject)obj;
    //         String name = (String)jsonObject.get("Name");
    //         String course = (String)jsonObject.get("Course");
    //         JSONArray subjects = (JSONArray)jsonObject.get("Subjects");
    //         System.out.println("Name: " + name);
    //         System.out.println("Course: " + course);
    //         System.out.println("Subjects:");
    //         Iterator iterator = subjects.iterator();
    //         while (iterator.hasNext()) {
    //            System.out.println(iterator.next());
    //         }
    //      } catch(Exception e) {
    //         e.printStackTrace();
    //      }
    // }



}
