package cyberGaming.unity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import cyberGaming.unity.enums.BookingStatus;

public class Bookings {

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

    private Map<Integer, BookingFoodItems> foodItems = new HashMap<>();

    public Bookings() {}

    public Bookings(int userId, Integer pcId, Timestamp startTime, Timestamp endTime) {
        this.userId = userId;
        this.pcId = pcId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = "";

        // Các giá trị mặc định
        this.totalHours = 0; // Sẽ được database tự tính
        this.totalPcAmount = 0.0; // Sẽ được database tính hoặc set sau
        this.totalFoodAmount = 0.0; // Mặc định 0, sẽ cập nhật khi thêm món
        this.grandTotal = 0.0; // Sẽ được database tính
        this.status = BookingStatus.PENDING; // Mặc định PENDING
        this.paymentStatus = "UNPAID"; // Mặc định UNPAID
        this.createdAt = null; // Database tự set
        this.updatedAt = null; // Database tự set
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

    public void setUpdatedAt() {
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Map<Integer, BookingFoodItems> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Map<Integer, BookingFoodItems> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingId=" + bookingId + ", userId=" + userId + ", pcId=" + pcId
                + ", startTime=" + startTime + ", endTime=" + endTime + ", grandTotal=" + grandTotal
                + ", status=" + status + ", paymentStatus='" + paymentStatus + '\'' + '}';
    }
}
