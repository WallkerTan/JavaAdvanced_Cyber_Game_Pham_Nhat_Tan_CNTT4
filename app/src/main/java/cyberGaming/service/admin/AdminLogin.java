package cyberGaming.service.admin;

import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.common.Function;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.UserRole;
import cyberGaming.util.HashUtil;

public class AdminLogin {

    private static final UserIMPL userDAO = new UserIMPL();
    private static final Scanner sc = new Scanner(System.in);

    public static Users LoginAD() {

        System.out.print("Username: ");
        String username = Function.StringInput(sc).trim();

        System.out.print("Password: ");
        String password = Function.StringInput(sc).trim();

        // Debug
        Users user = userDAO.findByUsername(username);

        if (user == null) {
            System.out.println(" Khong tim thay user nao voi username nay");
            System.out.println("Sai username hoac tai khoan khong ton tai!");
            return null;
        }

        System.out.println("Tim thay user - Role: " + user.getRole());
        System.out.println("Password hash trong DB: " + user.getPasswordHash());

        // Kiểm tra mật khẩu
        boolean isCorrectPass = HashUtil.checkPassword(password, user.getPasswordHash());

        System.out.println("Mat khau nhap vao hash thanh: " + HashUtil.hashPassword(password));
        System.out.println("Check password ket qua: " + isCorrectPass);

        if (user.getRole() == UserRole.ADMIN && isCorrectPass) {
            System.out.println("Dang nhap Admin thanh cong!");
            Users.curentUser = user;
            return user;
        } else {
            System.out.println("Sai mat khau hoac khong phai Admin!");
            return null;
        }
    }
}
