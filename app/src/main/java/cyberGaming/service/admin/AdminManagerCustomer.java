package cyberGaming.service.admin;

import java.util.Map;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.UserRole;

public class AdminManagerCustomer {
    public static UserIMPL userDAO = new UserIMPL();

    public static void showAlluser() {
        Map<Integer, Users> M = userDAO.getAlluserByRole(UserRole.CUSTOMER);

        if (M.isEmpty()) {
            System.out.println("khong co ng dung");
            return;
        }
        for (Users u : M.values()) {
            System.out.println(u.toString());
        }
    }
}
