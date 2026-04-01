package cyberGaming.service.customer;

import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.common.Function;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.UserRole;
import cyberGaming.util.HashUtil;

public class CustomerCreate {

    private static final UserIMPL userDAO = new UserIMPL();

    public static Users createCustomer(Scanner sc) {

        System.out.print("Username: ");
        String username = Function.StringInput(sc);

        System.out.print("Fullname: ");
        String fullName = Function.StringInput(sc);

        System.out.print("Phone: ");
        String phone = Function.StringInput(sc);

        System.out.print("Password: ");
        String password = Function.StringInput(sc);

        // Kiểm tra độ mạnh mật khẩu
        while (!Function.checkPassword(password)) {
            Function.clearScreen();
            System.out.print("MK khong du manh:\nnhap mk manh hon ");
            password = Function.StringInput(sc);
        }

        String hashedPassword = HashUtil.hashPassword(password);

        Users newUser = new Users(username, hashedPassword, fullName, phone, UserRole.CUSTOMER);

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
