package Cyber_Gaming.UI;

import java.util.Scanner;
import Cyber_Gaming.service.common.function;

public class AdminUI {
    public static int showAdminMenu(Scanner scanner) {
        function.clearScreen();
        System.out.println("------ MENU ADMIN ------");
        System.out.println("1. Quan ly May tram");
        System.out.println("2. Quan ly Menu F&B");
        System.out.println("3. Quan ly Nguoi dung");
        System.out.println("4. Bao cao");
        System.out.println("0. Dang xuat");

        return function.IntInput(scanner);
    }
}
