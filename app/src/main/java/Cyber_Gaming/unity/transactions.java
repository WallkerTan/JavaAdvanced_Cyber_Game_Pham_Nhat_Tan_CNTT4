package Cyber_Gaming.unity;

import java.sql.Timestamp;
import Cyber_Gaming.unity.enums.TransactionType;

public class transactions {

    private int transactionId;
    private int userId;
    private Integer bookingId;
    private TransactionType type;
    private double amount;
    private String description;
    private Timestamp createdAt;

    public transactions() {}

    public transactions(int userId, Integer bookingId, TransactionType type, double amount,
            String description) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", userId=" + userId
                + ", bookingId=" + (bookingId != null ? bookingId : "null") + ", type=" + type
                + ", amount=" + amount + ", description='" + description + '\'' + ", createdAt="
                + createdAt + '}';
    }
}
