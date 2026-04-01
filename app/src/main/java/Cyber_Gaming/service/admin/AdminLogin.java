package Cyber_Gaming.service.admin;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.util.HashUtil;

public class AdminLogin {

    private static final userIMPL userDAO = new userIMPL();
    private static final Scanner sc = new Scanner(System.in);

    public static users LoginAD() {

        System.out.print("Username: ");
        String username = function.StringInput(sc).trim();

        System.out.print("Password: ");
        String password = function.StringInput(sc).trim();

        // Debug
        users user = userDAO.findByUsername(username);

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
            users.curentUser = user;
            return user;
        } else {
            System.out.println("Sai mat khau hoac khong phai Admin!");
            return null;
        }
    }
}
