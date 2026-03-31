package Cyber_Gaming.service.common;

import java.util.Scanner;

public class function {

    public static int IntInput(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.print("Nhap so hop le: ");
            }
        }
    }

    public static boolean checkPhone(String phone){
        String regex = "^0\\d{9}";
        return phone.matches(regex);
    }

    public static boolean checkPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*.!`~]).{8,}$";
        return password.matches(regex);
    }


    public static String StringInput(Scanner sc){
        try {
            return sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            // TODO: handle exception
        }
    }

    public static boolean CheckEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    
    public static void pause(Scanner sc) {
        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
