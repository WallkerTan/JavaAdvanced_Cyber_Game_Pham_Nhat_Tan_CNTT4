package Cyber_Gaming.service.customer;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;

public class customerDepoisit {
    public static userIMPL userDAO = new userIMPL();

    public static void depoisit(Scanner sc) {
        System.out.println("nhap so tien can nap");
        double x = function.DoubleInput(sc);
        users.curentUser.setBalance(x);
        userDAO.updateUser(users.curentUser.getUserId(), users.curentUser);
    }
}
