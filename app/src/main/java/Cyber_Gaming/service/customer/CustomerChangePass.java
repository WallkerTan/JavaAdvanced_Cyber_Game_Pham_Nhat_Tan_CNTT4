package Cyber_Gaming.service.customer;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.util.HashUtil;

public class CustomerChangePass {
    public static userIMPL userDAO = new userIMPL();

    public static void changpass(Scanner sc) {
        System.out.println("nhap pass cu");
        String str = function.StringInput(sc);
        if (HashUtil.checkPassword(str, users.curentUser.getPasswordHash())) {
            System.out.println("nhap pass moi");
            String temp = function.StringInput(sc);
            while (function.checkPassword(temp) == false) {
                System.out.println("pass phai du manh");
                temp = function.StringInput(sc);
            }
            users.curentUser.setPassword(temp);
            userDAO.updateUser(users.curentUser.getUserId(), users.curentUser);
            return;
        }
        System.out.println("khong dung mk");
    }
}
