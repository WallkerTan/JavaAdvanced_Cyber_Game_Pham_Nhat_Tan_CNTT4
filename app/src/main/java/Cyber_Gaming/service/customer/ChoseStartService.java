package Cyber_Gaming.service.customer;

import java.util.Scanner;
import Cyber_Gaming.UI.CustomerUI;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;

public class ChoseStartService {

    public static users choseOptionCustomer(Scanner sc) {
        while (true) {
            int choice = CustomerUI.showMainMenu(sc);

            switch (choice) {
                case 1:
                    users loggedUser = CustomerLogin.LoginCus();
                    if (loggedUser != null) {
                        return loggedUser;
                    }
                    break;

                case 2:
                    CustomerCreate.createCustomer(sc);
                    System.out.print("Ban co muon dang nhap ngay bay gio? (1: Co / 0: Khong): ");
                    int confirm = function.IntInput(sc);
                    if (confirm == 1) {
                        return CustomerLogin.LoginCus();
                    }
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);

                default:
                    System.out.println("Lua chon khong hop le!");
                    function.pause(sc);
            }
        }
    }
}
