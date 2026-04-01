package cyberGaming.ui;

import java.util.Scanner;
import cyberGaming.service.common.Function;

public class AdminUI {
    public static int showAdminMenu(Scanner scanner) {
        Function.clearScreen();
        System.out.println("------ MENU ADMIN ------");
        System.out.println("1. Them May tram");
        System.out.println("2. Sua may tram");
        System.out.println("3. Xoa may tram");
        System.out.println("4: Xem danh sach may");
        System.out.println("5. Them mon");
        System.out.println("6. sua mon");
        System.out.println("7. xoa mon");
        System.out.println("8. Menu F&B");
        System.out.println("9. Danh sach nguoi dung");
        System.out.println("10. Lich su giao dich/thong ke");
        System.out.println("11. Duyet may dang sua");
        System.out.println("0. Dang xuat");

        return Function.IntInput(scanner);
    }
}
