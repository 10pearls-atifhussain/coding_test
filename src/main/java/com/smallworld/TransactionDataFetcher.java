package com.smallworld;
// package smallworld;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.smallworld.entity.Transaction;
import com.smallworld.service.TransactionService;

public class TransactionDataFetcher {

    TransactionService ts;

    public TransactionDataFetcher() {
        // ts.printAllTransactions();
    }

    public TransactionService loadTransanctions() {
        this.ts = new TransactionService();

        try {
            ts.creatTransaction(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false,
                    "Looks like money laundering");
            ts.creatTransaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true,
                    "Never gonna give you up");
            ts.creatTransaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 3, false,
                    "Looks like money laundering");
            ts.creatTransaction(96132456, 67.8, "Aunt Polly", 34, "Aberama Gold", 58);
            ts.creatTransaction(5465465, 985, "Arthur Shelby", 60, "Ben Younger", 47, 15, false, "Something's fishy");
            ts.creatTransaction(1651665, 97.66, "Tom Shelby", 22, "Oswald Mosley", 37, 65, true,
                    "Never gonna let you down");
            ts.creatTransaction(6516461, 33.22, "Aunt Polly", 34, "MacTavern", 30);
            ts.creatTransaction(32612651, 666, "Grace Burgess", 31, "Michael Gray", 58, 54, false,
                    "Something ain't right");
            ts.creatTransaction(32612651, 666, "Grace Burgess", 31, "Michael Gray", 58, 78, true,
                    "Never gonna run around and desert you");
            ts.creatTransaction(32612651, 666, "Grace Burgess", 31, "Michael Gray", 58, 99, false,
                    "Don't let this transaction happen");
            ts.creatTransaction(36448252, 154.15, "Billy Kimber", 58, "Winston Churchill", 48);
            ts.creatTransaction(645645111, 215.17, "Billy Kimber", 58, "Major Campbell", 41);
            ts.creatTransaction(45431585, 89.77, "Billy Kimber", 58, "Luca Changretta", 46);
            return ts;
        } catch (Exception e) {
            System.out.println("Exception caught in loadTransanctions : " + e.getMessage());
            return null;
        }

    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {

        this.ts = this.loadTransanctions();
        double totalAmount = 0.0;

        try {
            for (Transaction i : this.ts.transactions) {
                totalAmount += i.getAmount();
            }
            return totalAmount;
        } catch (Exception e) {
            System.out.println("Exception caught in getTotalTransactionAmount : " + e.getMessage());
            return 0;
        }

    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified
     * client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {

        this.ts = this.loadTransanctions();
        double totalAmount = 0.0;

        try {
            // can also be use streams
            for (Transaction i : this.ts.transactions) {
                if (i.sender.getSender().getFullname().equals(senderFullName)) {
                    totalAmount += i.getAmount();
                }
            }
            return totalAmount;
        } catch (Exception e) {
            System.out.println("Exception caught in getTotalTransactionAmountSentBy : " + e.getMessage());
            return 0;
        }
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        this.ts = this.loadTransanctions();
        double maxAmount = 0.0;

        try {
            for (Transaction t : this.ts.transactions) {
                if (t.getAmount() > maxAmount) {
                    maxAmount = t.getAmount();
                }
            }
            return maxAmount;
        } catch (Exception e) {
            System.out.println("Exception caught in getMaxTransactionAmount : " + e.getMessage());
            return 0;
        }
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        this.ts = this.loadTransanctions();

        Set<String> uniqueClient = new HashSet<String>();
        try {
            for (Transaction i : this.ts.transactions) {
                uniqueClient.add(i.sender.getSender().getFullname());
                uniqueClient.add(i.beneficiary.getBeneficiary().getFullname());
            }
            return uniqueClient.size();
        } catch (Exception e) {
            System.out.println("Exception caught in countUniqueClients : " + e.getMessage());
            return 0;
        }
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction
     * with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {

        List<Transaction> ts = this.loadTransanctions().SearchTransactionByName(clientFullName);

        try {
            List<Transaction> list = new TransactionService().returnAllUnSolvedTransactionsByGiveList(ts);
            return list.size() > 0;
        } catch (Exception e) {
            System.out.println("Exception caught in hasOpenComplianceIssues : " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public List<Transaction> getTransactionsByBeneficiaryName(String beneficiaryName) {

        this.ts = this.loadTransanctions();
        try {
            return this.ts.SearchTransactionByBeneficaryName(beneficiaryName);
        } catch (Exception e) {
            System.out.println("Exception caught in getTransactionsByBeneficiaryName : " + e.getMessage());
            return null;
        }
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {

        this.ts = this.loadTransanctions();
        try {
            List<Transaction> list = this.ts.returnAllUnSolvedTransactions();
            return list.stream().map(t -> t.getIssue().getIssueId()).collect(Collectors.toSet());
        } catch (Exception e) {
            System.out.println("Exception caught in getUnsolvedIssueIds : " + e.getMessage());
            return null;
        }
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {

        this.ts = this.loadTransanctions();
        try {
            List<Transaction> list = this.ts.returnAllSolvedTransactions();
            return list.stream().map(t -> t.getIssue().getIssueMessage()).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Exception caught in getAllSolvedIssueMessages : " + e.getMessage());
            return null;
        }
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {

        this.ts = this.loadTransanctions();

        try {
            return this.ts.sortTransactionsByAmountReverseOrder().subList(0, 3);
        } catch (Exception e) {
            System.out.println("Exception caught in getTop3TransactionsByAmount : " + e.getMessage());
            return null;
        }
    }

    /**
     * Returns the sender with the most total sent amount
     */
    public Entry<String, Double> getTopSender() {

        this.ts = this.loadTransanctions();
        try {
            Map<String, Double> top = new HashMap<String, Double>();
            Set<String> sendersFullName = uniqueClients(this.ts.transactions);
            for (String s : sendersFullName) {
                top.put(s, getTotalTransactionAmountSentBy(s));
            }
            List<Entry<String, Double>> list = new ArrayList<>(top.entrySet());
            list.sort(Entry.comparingByValue(Comparator.reverseOrder()));
            return list.get(0);
        } catch (Exception e) {
            System.out.println("Exception caught in getTopSender : " + e.getMessage());
            return null;
        }
    }

    public Set<String> uniqueClients(List<Transaction> ts) {

        Set<String> uniqueClient = new HashSet<String>();
        try {
            for (Transaction t : this.ts.transactions) {
                uniqueClient.add(t.sender.getSender().getFullname());
                uniqueClient.add(t.beneficiary.getBeneficiary().getFullname());
            }
            return uniqueClient;
        } catch (Exception e) {
            System.out.println("Exception caught in uniqueClients : " + e.getMessage());
            return null;
        }
    }

}
