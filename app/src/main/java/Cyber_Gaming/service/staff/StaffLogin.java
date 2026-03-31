package Cyber_Gaming.service.staff;

import java.util.Map;
import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.util.HashUtil;

public class StaffLogin {
    
    private static userIMPL userDAO = new userIMPL();
    private static Scanner sc = new Scanner(System.in);

    public static users LoginStaff(){
        
        String username = "";
        String pass = "";

        System.out.printf("username: ");
        username = function.StringInput(sc);
        
        System.out.printf("\nPassWord: ");
        pass = function.StringInput(sc);

        Map<Integer, users> M = userDAO.getAlluser();
        for (users u : M.values()) {
            if (u.getUsername().equals(username.trim()) && u.getRole() == UserRole.STAFF
                    && HashUtil.checkPassword(pass, u.getPasswordHash())) {
                return u;
            }
        }
        return null;
    }
}
