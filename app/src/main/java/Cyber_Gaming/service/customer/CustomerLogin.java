package Cyber_Gaming.service.customer;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.util.HashUtil;

public class CustomerLogin {

    private static final userIMPL userDAO = new userIMPL();
    private static final Scanner sc = new Scanner(System.in);

    public static users LoginCus() {
        System.out.print("Username: ");
        String username = function.StringInput(sc);

        System.out.print("Password: ");
        String password = function.StringInput(sc);

        try {
            users user = userDAO.findByUsername(username);

            if (user == null) {
                System.out.println("Khong tim thay user voi username: " + username);
                return null;
            }

            System.out.println("User tim thay: " + user.getUsername());
            System.out.println("Password hash trong DB : " + user.getPasswordHash());
            System.out.println("Password ban nhap      : " + password);
            System.out.println("Hash cua pass nhap    : " + HashUtil.hashPassword(password));
            System.out.println("Check password ket qua: "
                    + HashUtil.checkPassword(password, user.getPasswordHash()));

            if (HashUtil.checkPassword(password, user.getPasswordHash())) {
                users.curentUser = user;
                System.out.println("Dang nhap thanh cong! Chao " + user.getfull_name());
                return user;
            } else {
                System.out.println("Sai mat khau!");
                return null;
            }

        } catch (Exception e) {
            System.out.println("Loi he thong khi dang nhap!");
            e.printStackTrace();
            return null;
        }
    }
}
