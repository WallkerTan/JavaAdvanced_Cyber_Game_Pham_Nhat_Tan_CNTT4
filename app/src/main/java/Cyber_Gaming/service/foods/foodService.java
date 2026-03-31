package Cyber_Gaming.service.foods;

import java.util.Map;
import Cyber_Gaming.dao.implementation.foodIMPL;
import Cyber_Gaming.unity.foods;

public class foodService {
    private static final foodIMPL foodDAO = new foodIMPL();
    private static Map<Integer, foods> foodMap;

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
        for (foods f : foodMap.values()) {
            System.out.println("ID: " + f.getFoodId() + " | Ten: " + f.getFoodName() + " | Gia: "
                    + f.getPrice() + " | Ton kho: " + f.getStockQuantity() + " | Trang thai: "
                    + (f.isAvailable() ? "Co san" : "Het hang"));
        }
        System.out.println("---------------------------------------------");
    }

    public static foods getFoodByid(int id) {
        loadData();
        return foodMap.get(id);
    }

    public static boolean update(foods f) {
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
        foods temp = getFoodByid(id);
        return temp != null && temp.getStockQuantity() > 0;
    }
}
