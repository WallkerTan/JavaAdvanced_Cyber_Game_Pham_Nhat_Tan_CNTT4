package Cyber_Gaming.service.admin;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.foodIMPL;
import Cyber_Gaming.unity.foods;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.service.foods.foodService;

public class AdminManagerFood {

    private static final foodIMPL foodDAO = new foodIMPL();

    // ====================== THÊM MÓN ĂN ======================
    public static void add(Scanner sc) {
        System.out.println("================ THEM MON AN MOI ================");

        System.out.print("Nhap ten mon: ");
        String foodName = function.StringInput(sc);

        System.out.print("Nhap mo ta: ");
        String description = function.StringInput(sc);

        System.out.print("Nhap gia (VND): ");
        double price = function.DoubleInput(sc);

        System.out.print("Nhap so luong ton kho ban dau: ");
        int stock = function.IntInput(sc);

        foods f = new foods();
        f.setFoodName(foodName);
        f.setDescription(description);
        f.setPrice(price);
        f.setStockQuantity(stock);
        f.setAvailable(true);

        boolean success = foodDAO.insertFood(f);

        if (success) {
            System.out.println("Them mon an thanh cong!");
        } else {
            System.out.println("Them mon an that bai!");
        }
    }

    // ====================== SUA MON AN ======================
    public static void update(Scanner sc) {
        foodService.showAllfood(); // Hiển thị danh sách món hiện có

        System.out.println("----------------------------");
        System.out.print("Nhap ID mon an muon sua: ");
        int id = function.IntInput(sc);

        foods temp = foodService.getFoodByid(id);
        if (temp == null) {
            System.out.println("Mon an khong ton tai!");
            return;
        }

        System.out.println("Dang sua mon: " + temp.getFoodName());

        System.out.print("Nhap ten moi (hien tai: " + temp.getFoodName() + "): ");
        String name = function.StringInput(sc);
        if (!name.isEmpty())
            temp.setFoodName(name);

        System.out.print("Nhap mo ta moi: ");
        String desc = function.StringInput(sc);
        if (!desc.isEmpty())
            temp.setDescription(desc);

        System.out.print("Nhap gia moi (hien tai: " + temp.getPrice() + "): ");
        double price = function.DoubleInput(sc);
        if (price > 0)
            temp.setPrice(price);

        System.out.print("Nhap so luong ton kho moi (hien tai: " + temp.getStockQuantity() + "): ");
        int stock = function.IntInput(sc);
        temp.setStockQuantity(stock);

        System.out.print("Mon an con ban? (1: Co / 0: Het hang): ");
        boolean available = function.IntInput(sc) == 1;
        temp.setAvailable(available);

        boolean success = foodDAO.updateFood(temp.getFoodId(), temp);

        if (success) {
            System.out.println("Cap nhat mon an thanh cong!");
        } else {
            System.out.println("Cap nhat that bai!");
        }
    }

    // ====================== XÓA MÓN ĂN ======================
    public static void delete(Scanner sc) {
        foodService.showAllfood();

        System.out.println("----------------------------");
        System.out.print("Nhap ID mon an muon xoa: ");
        int id = function.IntInput(sc);

        foods temp = foodService.getFoodByid(id);
        if (temp == null) {
            System.out.println("Mon an khong ton tai!");
            return;
        }

        System.out.print(
                "Ban chac chan muon xoa mon '" + temp.getFoodName() + "' ? (1: Co / 0: Khong): ");
        int confirm = function.IntInput(sc);

        if (confirm != 1) {
            System.out.println("Da huy xoa.");
            return;
        }

        boolean success = foodDAO.deleteFood(id);

        if (success) {
            System.out.println("Xoa mon an thanh cong!");
        } else {
            System.out.println("Xoa that bai! Mon an co the dang duoc su dung.");
        }
    }
}
