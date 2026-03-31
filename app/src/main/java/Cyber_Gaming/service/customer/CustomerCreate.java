package Cyber_Gaming.service.customer;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.util.HashUtil;

public class CustomerCreate {

    private static final userIMPL userDAO = new userIMPL();

    public static users createCustomer(Scanner sc) {

        System.out.print("Username: ");
        String username = function.StringInput(sc);

        System.out.print("Fullname: ");
        String fullName = function.StringInput(sc);

        System.out.print("Phone: ");
        String phone = function.StringInput(sc);

        System.out.print("Password: ");
        String password = function.StringInput(sc);

        // Kiểm tra độ mạnh mật khẩu
        while (!function.checkPassword(password)) {
            function.clearScreen();
            System.out.print("MK khong du manh:\nnhap mk manh hon ");
            password = function.StringInput(sc);
        }

        String hashedPassword = HashUtil.hashPassword(password);

        users newUser = new users(username, hashedPassword, fullName, phone, UserRole.CUSTOMER);

        boolean success = userDAO.insertUser(newUser);

        if (success) {
            System.out.println("\ndang ki thanh cong!");
            return newUser;
        } else {
            System.out.println("\ndang ki that bai, nguoi dung da ton tai");
            return null;
        }
    }
}
