package cyberGaming.service.foods;

import java.util.Map;
import cyberGaming.dao.implementation.FoodIMPL;
import cyberGaming.unity.Foods;

public class FoodService {
    private static final FoodIMPL foodDAO = new FoodIMPL();
    private static Map<Integer, Foods> foodMap;

    private static void loadData() {
        foodMap = foodDAO.getAllfood();
    }

    public static void showAllfood() {
        loadData();

        if (foodMap.isEmpty()) {
            System.out.println("Hien tai khong co do an nao!");
            return;
        }

        System.out.println("-------------- DANH SACH DO AN --------------");
        for (Foods f : foodMap.values()) {
            System.out.println("ID: " + f.getFoodId() + " | Ten: " + f.getFoodName() + " | Gia: "
                    + f.getPrice() + " | Ton kho: " + f.getStockQuantity() + " | Trang thai: "
                    + (f.isAvailable() ? "Co san" : "Het hang"));
        }
        System.out.println("---------------------------------------------");
    }

    public static Foods getFoodByid(int id) {
        loadData();
        return foodMap.get(id);
    }

    public static boolean update(Foods f) {
        if (f == null) {
            return false;
        }
        return foodDAO.updateFood(f.getFoodId(), f);
    }

    public static boolean delete(int id) {
        loadData();
        return foodDAO.deleteFood(id);
    }

    public static boolean isQuantity(int id) {
        Foods temp = getFoodByid(id);
        return temp != null && temp.getStockQuantity() > 0;
    }
}
