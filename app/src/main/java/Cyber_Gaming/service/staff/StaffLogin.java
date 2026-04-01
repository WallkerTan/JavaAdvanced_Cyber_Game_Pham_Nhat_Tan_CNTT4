package Cyber_Gaming.service.staff;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.util.HashUtil;

public class StaffLogin {

    private static final userIMPL userDAO = new userIMPL();
    private static final Scanner sc = new Scanner(System.in);

    public static users LoginStaff() {

        System.out.print("Username: ");
        String username = function.StringInput(sc).trim();

        System.out.print("Password: ");
        String password = function.StringInput(sc);

        // Debug
        System.out.println("[DEBUG] Dang tim Staff: " + username);

        users user = userDAO.findByUsername(username);

        if (user == null) {
            System.out.println("Khong tim thay user");
            return null;
        }

        System.out.println("Tim thay user - Role: " + user.getRole());

        boolean passDung = HashUtil.checkPassword(password, user.getPasswordHash());

        System.out.println("Check password ket qua: " + passDung);

        if (user.getRole() == UserRole.STAFF && passDung) {
            System.out.println("Dang nhap Staff thanh cong!");
            users.curentUser = user;
            return user;
        } else {
            System.out.println("Sai mat khau hoac khong phai tai khoan Staff!");
            return null;
        }
    }
}
