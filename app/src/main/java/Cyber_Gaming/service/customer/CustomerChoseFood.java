package Cyber_Gaming.service.customer;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.service.foods.foodService;
import Cyber_Gaming.unity.foods;
import Cyber_Gaming.unity.users;



public class CustomerChoseFood {
    public static userIMPL userDAO = new userIMPL();

    public static void choseFood(Scanner sc) {
        System.out.println("-----------------------------");
        foodService.showAllfood();
        System.out.println("-----------------------------");
        System.out.printf("-> ");
        int choice_id = function.IntInput(sc);
        foods f = foodService.getFoodByid(choice_id);
        if (f.getStockQuantity() <= 0) {
            System.out.println("het hang");
            return;
        }
        System.out.printf("nhap so luong: ");
        int qtt = function.IntInput(sc);
        if (qtt > f.getStockQuantity()) {
            System.out.println("so luong sp khong du");
            return;
        }
        f.setStockQuantity(f.getStockQuantity() - qtt);
        foodService.update(f);
        users t = users.curentUser;
        t.setMoneyOwed(t.getMoneyOwed() + qtt * f.getPrice());
        userDAO.updateUser(t.getUserId(), t);
        System.out.println("bn da goi 2 " + f.getFoodName() + " tong tien: " + qtt * f.getPrice()
                + "  can thanh toan: " + t.getMoneyOwed());
    }
}
