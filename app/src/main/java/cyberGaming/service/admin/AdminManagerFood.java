package cyberGaming.service.admin;

import java.util.Scanner;
import cyberGaming.dao.implementation.FoodIMPL;
import cyberGaming.service.common.Function;
import cyberGaming.service.foods.FoodService;
import cyberGaming.unity.Foods;

public class AdminManagerFood {

    private static final FoodIMPL foodDAO = new FoodIMPL();

    // ====================== THÊM MÓN ĂN ======================
    public static void add(Scanner sc) {
        System.out.println("================ THEM MON AN MOI ================");

        System.out.print("Nhap ten mon: ");
        String foodName = Function.StringInput(sc);

        System.out.print("Nhap mo ta: ");
        String description = Function.StringInput(sc);

        System.out.print("Nhap gia (VND): ");
        double price = Function.DoubleInput(sc);

        System.out.print("Nhap so luong ton kho ban dau: ");
        int stock = Function.IntInput(sc);

        Foods f = new Foods();
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
        FoodService.showAllfood(); // Hiển thị danh sách món hiện có

        System.out.println("----------------------------");
        System.out.print("Nhap ID mon an muon sua: ");
        int id = Function.IntInput(sc);

        Foods temp = FoodService.getFoodByid(id);
        if (temp == null) {
            System.out.println("Mon an khong ton tai!");
            return;
        }

        System.out.println("Dang sua mon: " + temp.getFoodName());

        System.out.print("Nhap ten moi (hien tai: " + temp.getFoodName() + "): ");
        String name = Function.StringInput(sc);
        if (!name.isEmpty())
            temp.setFoodName(name);

        System.out.print("Nhap mo ta moi: ");
        String desc = Function.StringInput(sc);
        if (!desc.isEmpty())
            temp.setDescription(desc);

        System.out.print("Nhap gia moi (hien tai: " + temp.getPrice() + "): ");
        double price = Function.DoubleInput(sc);
        if (price > 0)
            temp.setPrice(price);

        System.out.print("Nhap so luong ton kho moi (hien tai: " + temp.getStockQuantity() + "): ");
        int stock = Function.IntInput(sc);
        temp.setStockQuantity(stock);

        System.out.print("Mon an con ban? (1: Co / 0: Het hang): ");
        boolean available = Function.IntInput(sc) == 1;
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
        FoodService.showAllfood();

        System.out.println("----------------------------");
        System.out.print("Nhap ID mon an muon xoa: ");
        int id = Function.IntInput(sc);

        Foods temp = FoodService.getFoodByid(id);
        if (temp == null) {
            System.out.println("Mon an khong ton tai!");
            return;
        }

        System.out.print(
                "Ban chac chan muon xoa mon '" + temp.getFoodName() + "' ? (1: Co / 0: Khong): ");
        int confirm = Function.IntInput(sc);

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
