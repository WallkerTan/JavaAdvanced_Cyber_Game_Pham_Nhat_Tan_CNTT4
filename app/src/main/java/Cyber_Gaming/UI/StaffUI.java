package Cyber_Gaming.UI;

import java.util.Scanner;
import Cyber_Gaming.service.common.function;

public class StaffUI {
    public static int showStaffMenu(Scanner scanner) {
        function.clearScreen();
        System.out.println("--------------------- MENU NHAN VIEN ---------------------");
        System.out.println("1. Xem danh sach don dat may");
        System.out.println("2. Xac nhan / Cap nhat trang thai don hang");
        System.out.println("3. xem don do an");
        System.out.println("0. Dang xuat");
        System.out.println("---------------------------------------------------------");
        System.out.print("Chon chuc nang: ");

        int choice = function.IntInput(scanner);
        return choice;
    }
}
