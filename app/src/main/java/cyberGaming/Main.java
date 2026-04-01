package cyberGaming;

import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.admin.AdminLogin;
import cyberGaming.service.admin.AdminManagerCustomer;
import cyberGaming.service.admin.AdminManagerFood;
import cyberGaming.service.admin.AdminManagerPcs;
import cyberGaming.service.common.CurrentUser;
import cyberGaming.service.common.Function;
import cyberGaming.service.customer.CustomerBanking;
import cyberGaming.service.customer.CustomerChangePass;
import cyberGaming.service.customer.CustomerChoseFood;
import cyberGaming.service.customer.CustomerChosePcs;
import cyberGaming.service.customer.CustomerCreate;
import cyberGaming.service.customer.CustomerLogin;
import cyberGaming.service.customer.customerDepoisit;
import cyberGaming.service.foods.FoodService;
import cyberGaming.service.pcs.PcsService;
import cyberGaming.service.staff.StaffLogin;
import cyberGaming.service.staff.StaffManagerBooking;
import cyberGaming.service.transaction.TransactionService;
import cyberGaming.ui.ConsoleUI;
import cyberGaming.ui.CustomerUI;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.PCStatus;
import cyberGaming.unity.enums.UserRole;

public class Main {
    public static UserIMPL userDAO = new UserIMPL();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            // Bước 1: Chọn vai trò
            UserRole role = chooseRole(sc);

            Users currentUser = null;

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
                Function.pause(sc);
                continue;
            }

            // Lưu user hiện tại
            CurrentUser.setCurrentUser(currentUser);

            System.out.println("Dang nhap thanh cong! Chao " + currentUser.getfull_name());

            while (true) {
                int choice = ConsoleUI.menuByrole(role);
                Users temp = Users.curentUser;
                if (choice == 0) {
                    System.out.println("log out");
                    Function.pause(sc);
                    break;
                }
                switch (choice) {
                    case 1:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            PcsService.showAllPcs();
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerPcs.add(sc);
                        } else if (temp.getRole() == UserRole.STAFF) {
                            System.out.println("ds may dang duoc sd");
                            PcsService.showAllPcsByStatus(PCStatus.IN_USE);
                        }
                        Function.pause(sc);
                        break;
                    case 2:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChosePcs.chosePcs(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerPcs.update(sc);
                        } else if (temp.getRole() == UserRole.STAFF) {
                            StaffManagerBooking.updateBookingStatus(sc);
                        }
                        Function.pause(sc);
                        break;
                    case 3:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChoseFood.choseFood(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerPcs.delete(sc);
                        } else if (temp.getRole() == UserRole.STAFF) {
                            FoodService.showAllfood();
                        }
                        Function.pause(sc);
                        break;
                    case 4:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerBanking.isBanking(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            PcsService.showAllPcs();
                        }
                        Function.pause(sc);
                        break;
                    case 5:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            System.out.println(Users.curentUser.getBalance());

                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerFood.add(sc);
                        }
                        Function.pause(sc);
                        break;
                    case 6:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            customerDepoisit.depoisit(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerFood.update(sc);
                        }
                        Function.pause(sc);
                        break;
                    case 7:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            TransactionService.showAllTransactionByid(Users.curentUser.getUserId());
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerFood.delete(sc);
                        }
                        Function.pause(sc);
                        break;
                    case 8:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChangePass.changpass(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            FoodService.showAllfood();
                        }
                        Function.pause(sc);
                        break;
                    case 9:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChangePass.changpass(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerCustomer.showAlluser();
                        }
                        Function.pause(sc);
                        break;
                    case 10:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChangePass.changpass(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            TransactionService.showAllTransaction();
                        }
                        Function.pause(sc);
                        break;
                    case 11:
                        if (temp.getRole() == UserRole.CUSTOMER) {
                            CustomerChangePass.changpass(sc);
                        } else if (temp.getRole() == UserRole.ADMIN) {
                            AdminManagerPcs.maintenance(sc);
                        }
                        Function.pause(sc);
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
            Function.clearScreen();
            System.out.println("---------------- CHON VAI TRO ----------------");
            System.out.println("1. Admin");
            System.out.println("2. Staff");
            System.out.println("3. Customer");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("------------------------------------------------");
            System.out.print("Chon vai tro: ");

            int z = Function.IntInput(sc);

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
                    Function.pause(sc);
            }
        }
    }

    // ====================== XU LY LUONG CUSTOMER ======================
    private static Users handleCustomerFlow(Scanner sc) {
        while (true) {
            int choice = CustomerUI.showMainMenu(sc);

            switch (choice) {
                case 1:
                    return CustomerLogin.LoginCus(); // Dang nhap

                case 2:
                    CustomerCreate.createCustomer(sc); // Dang ky
                    System.out.print("Ban co muon dang nhap ngay? (1: Co / 0: Khong): ");
                    if (Function.IntInput(sc) == 1) {
                        return CustomerLogin.LoginCus();
                    }
                    return null; // Khong muon dang nhap → quay lai chon role

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);

                default:
                    System.out.println("Lua chon khong hop le!");
                    Function.pause(sc);
            }
        }
    }
}
