package cyberGaming.unity;

public class BookingFoodItems {

    private int itemId;
    private int bookingId;
    private int foodId;
    private int quantity;
    private double priceAtTime;
    private double subtotal;

    public BookingFoodItems() {}

    public BookingFoodItems(int bookingId, int foodId, int quantity, double priceAtTime) {
        this.bookingId = bookingId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(double priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "BookingFoodItem{" + "itemId=" + itemId + ", bookingId=" + bookingId + ", foodId="
                + foodId + ", quantity=" + quantity + ", priceAtTime=" + priceAtTime + ", subtotal="
                + subtotal + '}';
    }
}
