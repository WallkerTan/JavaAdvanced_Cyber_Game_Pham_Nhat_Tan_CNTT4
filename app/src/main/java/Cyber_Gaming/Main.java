package Cyber_Gaming;

import java.util.Scanner;
import Cyber_Gaming.UI.ConsoleUI;
import Cyber_Gaming.UI.CustomerUI;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.admin.AdminLogin;
import Cyber_Gaming.service.customer.CustomerChoseFood;
import Cyber_Gaming.service.customer.CustomerChosePcs;
import Cyber_Gaming.service.customer.CustomerCreate;
import Cyber_Gaming.service.customer.CustomerLogin;
import Cyber_Gaming.service.pcs.pcsService;
import Cyber_Gaming.service.staff.StaffLogin;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.service.common.CurrentUser;
import Cyber_Gaming.service.common.function;

public class Main {
    public static userIMPL userDAO = new userIMPL();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Bước 1: Chọn vai trò
            UserRole role = chooseRole(sc);

            users currentUser = null;

            // Bước 2: Xử lý đăng nhập / đăng ký theo từng role
            switch (role) {
                case ADMIN:
                    currentUser = AdminLogin.LoginAD();
                    break;

                case STAFF:
                    currentUser = StaffLogin.LoginStaff();
                    break;

                case CUSTOMER:
                    currentUser = handleCustomerFlow(sc);
                    break;
            }

            // Nếu đăng nhập / đăng ký thất bại thì quay lại chọn role
            if (currentUser == null) {
                System.out.println("Dang nhap that bai hoac huy!");
                function.pause(sc);
                continue;
            }

            // Lưu user hiện tại
            CurrentUser.setCurrentUser(currentUser);

            System.out.println("Dang nhap thanh cong! Chao " + currentUser.getfull_name());

            while (true) {
                int choice = ConsoleUI.menuByrole(role);
                users temp = users.curentUser;
                switch (choice) {
                    case 0:
                        System.out.println("log out..");
                        function.pause(sc);
                        break;
                    case 1:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            pcsService.showAllpcs();
                        }
                        function.pause(sc);
                        break;
                    case 2:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChosePcs.chosePcs(sc);
                        }
                        function.pause(sc);
                        break;
                    case 3:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChoseFood.choseFood(sc);
                        }
                        function.pause(sc);
                        break;
                    default:
                        break;
                }

            }
        }
    }

    // ====================== CHON ROLE ======================
    private static UserRole chooseRole(Scanner sc) {
        while (true) {
            function.clearScreen();
            System.out.println("---------------- CHON VAI TRO ----------------");
            System.out.println("1. Admin");
            System.out.println("2. Staff");
            System.out.println("3. Customer");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("------------------------------------------------");
            System.out.print("Chon vai tro: ");

            int z = function.IntInput(sc);

            switch (z) {
                case 1:
                    return UserRole.ADMIN;
                case 2:
                    return UserRole.STAFF;
                case 3:
                    return UserRole.CUSTOMER;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
                    function.pause(sc);
            }
        }
    }

    // ====================== XU LY LUONG CUSTOMER ======================
    private static users handleCustomerFlow(Scanner sc) {
        while (true) {
            int choice = CustomerUI.showMainMenu(sc);

            switch (choice) {
                case 1:
                    return CustomerLogin.LoginCus(); // Dang nhap

                case 2:
                    CustomerCreate.createCustomer(sc); // Dang ky
                    System.out.print("Ban co muon dang nhap ngay? (1: Co / 0: Khong): ");
                    if (function.IntInput(sc) == 1) {
                        return CustomerLogin.LoginCus();
                    }
                    return null; // Khong muon dang nhap → quay lai chon role

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);

                default:
                    System.out.println("Lua chon khong hop le!");
                    function.pause(sc);
            }
        }
    }
}
