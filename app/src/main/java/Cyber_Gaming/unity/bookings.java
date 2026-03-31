package Cyber_Gaming.unity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import Cyber_Gaming.unity.enums.BookingStatus;

public class bookings {

    private int bookingId;
    private int userId;
    private Integer pcId;
    private Timestamp startTime;
    private Timestamp endTime;
    private double totalHours;
    private double totalPcAmount;
    private double totalFoodAmount;
    private double grandTotal;
    private BookingStatus status;
    private String paymentStatus;
    private String notes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Map<Integer, booking_food_items> foodItems = new HashMap<>();

    public bookings() {}

    public bookings(int bookingId, int userId, Integer pcId, Timestamp startTime, Timestamp endTime,
            double totalHours, double totalPcAmount, double totalFoodAmount, double grandTotal,
            BookingStatus status, String paymentStatus, String notes, Timestamp createdAt,
            Timestamp updatedAt) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.pcId = pcId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalHours = totalHours;
        this.totalPcAmount = totalPcAmount;
        this.totalFoodAmount = totalFoodAmount;
        this.grandTotal = grandTotal;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getPcId() {
        return pcId;
    }

    public void setPcId(Integer pcId) {
        this.pcId = pcId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getTotalPcAmount() {
        return totalPcAmount;
    }

    public void setTotalPcAmount(double totalPcAmount) {
        this.totalPcAmount = totalPcAmount;
    }

    public double getTotalFoodAmount() {
        return totalFoodAmount;
    }

    public void setTotalFoodAmount(double totalFoodAmount) {
        this.totalFoodAmount = totalFoodAmount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<Integer, booking_food_items> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Map<Integer, booking_food_items> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingId=" + bookingId + ", userId=" + userId + ", pcId=" + pcId
                + ", startTime=" + startTime + ", endTime=" + endTime + ", grandTotal=" + grandTotal
                + ", status=" + status + ", paymentStatus='" + paymentStatus + '\'' + '}';
    }
}
