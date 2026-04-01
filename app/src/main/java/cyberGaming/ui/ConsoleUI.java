package cyberGaming.ui;

import java.util.Scanner;
import cyberGaming.unity.enums.UserRole;

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
