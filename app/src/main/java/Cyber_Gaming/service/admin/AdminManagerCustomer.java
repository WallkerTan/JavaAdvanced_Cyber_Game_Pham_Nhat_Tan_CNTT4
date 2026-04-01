package Cyber_Gaming.service.admin;

import java.util.Map;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;

public class AdminManagerCustomer {
    public static userIMPL userDAO = new userIMPL();

    public static void showAlluser() {
        Map<Integer, users> M = userDAO.getAlluserByRole(UserRole.CUSTOMER);

        if (M.isEmpty()) {
            System.out.println("khong co ng dung");
            return;
        }
        for (users u : M.values()) {
            System.out.println(u.toString());
        }
    }
}
