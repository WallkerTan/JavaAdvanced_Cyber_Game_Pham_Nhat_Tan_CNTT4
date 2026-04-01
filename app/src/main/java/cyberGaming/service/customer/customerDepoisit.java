package cyberGaming.service.customer;

import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.common.Function;
import cyberGaming.unity.Users;

public class customerDepoisit {
    public static UserIMPL userDAO = new UserIMPL();

    public static void depoisit(Scanner sc) {
        System.out.println("nhap so tien can nap");
        double x = Function.DoubleInput(sc);
        Users.curentUser.setBalance(x);
        userDAO.updateUser(Users.curentUser.getUserId(), Users.curentUser);
    }
}
