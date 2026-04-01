package cyberGaming.service.customer;

import java.util.Scanner;
import cyberGaming.service.common.Function;
import cyberGaming.ui.CustomerUI;
import cyberGaming.unity.Users;

public class ChoseStartService {

    public static Users choseOptionCustomer(Scanner sc) {
        while (true) {
            int choice = CustomerUI.showMainMenu(sc);

            switch (choice) {
                case 1:
                    Users loggedUser = CustomerLogin.LoginCus();
                    if (loggedUser != null) {
                        return loggedUser;
                    }
                    break;

                case 2:
                    CustomerCreate.createCustomer(sc);
                    System.out.print("Ban co muon dang nhap ngay bay gio? (1: Co / 0: Khong): ");
                    int confirm = Function.IntInput(sc);
                    if (confirm == 1) {
                        return CustomerLogin.LoginCus();
                    }
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);

                default:
                    System.out.println("Lua chon khong hop le!");
                    Function.pause(sc);
            }
        }
    }
}
