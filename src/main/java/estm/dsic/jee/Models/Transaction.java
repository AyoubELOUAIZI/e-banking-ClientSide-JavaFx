package estm.dsic.jee.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private int transactionId;
    private int accountId;
    private String transactionType;
    private Double amount;
    private LocalDateTime transactionDate;

    public Transaction() {}

    public Transaction(int transactionId, int accountId, String transactionType, Double amount, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters and setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime localDateTime) {
        this.transactionDate = localDateTime;
    }
}

