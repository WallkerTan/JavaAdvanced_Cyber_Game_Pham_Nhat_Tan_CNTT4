package Cyber_Gaming.unity;

import java.sql.Timestamp;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.util.HashUtil;

public class users {
    public static users curentUser = null;
    private double moneyOwed = 0;
    private int userId;
    private String username;
    private String passwordHash;
    private String full_name;
    private String phone;
    private UserRole role;
    private double balance;
    private boolean is_active;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public users() {}

    // Constructor dùng khi đăng ký (có password)
    public users(String username, String password, String full_name, String phone, UserRole role) {
        this.username = username;
        this.passwordHash = password;
        this.full_name = full_name;
        this.phone = phone;
        this.role = role;
        this.balance = 0.0;
        this.is_active = true;
        this.moneyOwed = 0;
    }

    public void setMoneyOwed(double moneyOwed) {
        this.moneyOwed = moneyOwed;
    }

    public double getMoneyOwed() {
        return this.moneyOwed;
    }

    public void setCurentID(users user) {
        users.curentUser = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // Setter đặc biệt để hash password
    public void setPassword(String password) {
        this.passwordHash = HashUtil.hashPassword(password);
    }

    public String getfull_name() {
        return full_name;
    }

    public void setfull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean is_active() {
        return is_active;
    }

    public void setActive(boolean is_active) {
        this.is_active = is_active;
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

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username='" + username + '\'' + ", full_name='"
                + full_name + '\'' + ", phone='" + phone + '\'' + ", role=" + role + ", balance="
                + balance + ", is_active=" + is_active + ", createdAt=" + createdAt + '}';
    }
}
