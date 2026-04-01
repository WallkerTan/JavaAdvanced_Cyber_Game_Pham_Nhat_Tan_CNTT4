package cyberGaming.service.staff;

import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.common.Function;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.UserRole;
import cyberGaming.util.HashUtil;

public class StaffLogin {

    private static final UserIMPL userDAO = new UserIMPL();
    private static final Scanner sc = new Scanner(System.in);

    public static Users LoginStaff() {

        System.out.print("Username: ");
        String username = Function.StringInput(sc).trim();

        System.out.print("Password: ");
        String password = Function.StringInput(sc);

        // Debug
        System.out.println("[DEBUG] Dang tim Staff: " + username);

        Users user = userDAO.findByUsername(username);

        if (user == null) {
            System.out.println("Khong tim thay user");
            return null;
        }

        System.out.println("Tim thay user - Role: " + user.getRole());

        boolean passDung = HashUtil.checkPassword(password, user.getPasswordHash());

        System.out.println("Check password ket qua: " + passDung);

        if (user.getRole() == UserRole.STAFF && passDung) {
            System.out.println("Dang nhap Staff thanh cong!");
            Users.curentUser = user;
            return user;
        } else {
            System.out.println("Sai mat khau hoac khong phai tai khoan Staff!");
            return null;
        }
    }
}
