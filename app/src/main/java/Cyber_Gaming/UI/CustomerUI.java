package Cyber_Gaming.UI;

import java.util.Scanner;
import Cyber_Gaming.service.common.function;

public class CustomerUI {

    public static int showMainMenu(Scanner scanner) {
        function.clearScreen();
        System.out.println("------------------- HE THONG CYBER CENTER -------------------");
        System.out.println("1. Dang nhap");
        System.out.println("2. Dang ky tai khoan khach hang");
        System.out.println("0. Thoat chuong trinh");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Chon chuc nang: ");
        return function.IntInput(scanner);
    }

    public static int showCustomerMenu(Scanner scanner) {
        function.clearScreen();
        System.out.println("--------------------- MENU KHACH HANG ---------------------");
        System.out.println("1. Xem may trong theo khu vuc");
        System.out.println("2. Dat may choi game");
        System.out.println("3. Goi mon an / thuc uong");
        System.out.println("4. Xem don hang cua toi");
        System.out.println("5. Xem so du tai khoan");
        System.out.println("6. Nap tien (gia lap)");
        System.out.println("7. Xem lich su giao dich");
        System.out.println("8. Doi mat khau");
        System.out.println("0. Dang xuat");
        System.out.println("----------------------------------------------------------");
        System.out.print("Chon chuc nang: ");

        return function.IntInput(scanner);
    }
}
