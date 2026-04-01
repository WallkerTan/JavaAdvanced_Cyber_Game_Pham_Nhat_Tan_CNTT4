package cyberGaming.unity;

import java.sql.Timestamp;

public class Foods {

    private int foodId;
    private String foodName;
    private String description;
    private double price;
    private int stockQuantity;
    private boolean isAvailable;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Foods() {}

    public Foods(int foodId, String foodName, String description, double price, int stockQuantity,
            boolean isAvailable, Timestamp createdAt, Timestamp updatedAt) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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
        return "Food{" + "foodId=" + foodId + ", foodName='" + foodName + '\'' + ", price=" + price
                + ", stockQuantity=" + stockQuantity + ", isAvailable=" + isAvailable + '}';
    }
}
