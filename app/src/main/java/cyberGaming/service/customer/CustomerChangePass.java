package cyberGaming.service.customer;

import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.common.Function;
import cyberGaming.unity.Users;
import cyberGaming.util.HashUtil;

public class CustomerChangePass {
    public static UserIMPL userDAO = new UserIMPL();

    public static void changpass(Scanner sc) {
        System.out.println("nhap pass cu");
        String str = Function.StringInput(sc);
        if (HashUtil.checkPassword(str, Users.curentUser.getPasswordHash())) {
            System.out.println("nhap pass moi");
            String temp = Function.StringInput(sc);
            while (Function.checkPassword(temp) == false) {
                System.out.println("pass phai du manh");
                temp = Function.StringInput(sc);
            }
            Users.curentUser.setPassword(temp);
            userDAO.updateUser(Users.curentUser.getUserId(), Users.curentUser);
            return;
        }
        System.out.println("khong dung mk");
    }
}
