package Cyber_Gaming.UI;

import java.util.Scanner;
import Cyber_Gaming.unity.enums.UserRole;

public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static int menuByrole(UserRole ur){
        return switch (ur) {
            case ADMIN -> AdminUI.showAdminMenu(scanner);
            case STAFF -> StaffUI.showStaffMenu(scanner);
            case CUSTOMER -> CustomerUI.showCustomerMenu(scanner);
        };
    }


}
