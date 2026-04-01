package cyberGaming.service.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.booking.BookingService;
import cyberGaming.service.common.Function;
import cyberGaming.service.pcs.PcsService;
import cyberGaming.service.transaction.TransactionService;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.Pcs;
import cyberGaming.unity.Transactions;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.BookingStatus;
import cyberGaming.unity.enums.PCStatus;
import cyberGaming.unity.enums.TransactionType;

public class CustomerBanking {
    public static UserIMPL userDAO = new UserIMPL();

    public static void isBanking(Scanner sc) {
        // Lấy booking hiện tại của user (đang sử dụng)
        Bookings temp = BookingService.getByUsersID(Users.curentUser.getUserId());
        if (temp == null) {
            System.out.println("Ban chua su dung dich vu");
            return;
        }
        temp.setUpdatedAt();
        System.out.println(temp.getUpdatedAt());
        BookingService.update(temp);


        // Hiển thị thông tin booking
        System.out.println("======================================");
        System.out.println("THONG TIN DAT MAY:");
        System.out.println("Booking ID: " + temp.getBookingId());
        System.out.println("Thoi gian bat dau: " + temp.getStartTime());
        System.out.println("Thoi gian ket thuc: " + temp.getEndTime());
        System.out.println("Tong gio: " + temp.getTotalHours());
        System.out.println("Tien PC: " + temp.getTotalPcAmount() + " VND");
        System.out.println("Tien do an: " + temp.getTotalFoodAmount() + " VND");
        System.out.println("Tong tien: " + temp.getGrandTotal() + " VND");
        System.out.println("======================================");

        System.out.println("Ban co muon thanh toan?");
        System.out.println("1. Co");
        System.out.println("2. Khong");
        System.out.print("Chon: ");

        switch (Function.IntInput(sc)) {
            case 1:
                Users cu = Users.curentUser;
                if (cu.getBalance() < temp.getGrandTotal()) {
                    System.out.println("Khong du tien! Vui long nap them.");
                    System.out.println("So du hien tai: " + cu.getBalance() + " VND");
                    System.out.println("Can thanh toan: " + temp.getGrandTotal() + " VND");
                    System.out
                            .println("Thieu: " + (temp.getGrandTotal() - cu.getBalance()) + " VND");
                } else {
                    // Trừ tiền
                    cu.setBalance(cu.getBalance() - temp.getGrandTotal());
                    userDAO.updateUser(cu.getUserId(), cu);
                    // cộng tiền
                    userDAO.getAdmin().setBalance(temp.getGrandTotal());
                    userDAO.updateUser(userDAO.getAdmin().getUserId(), userDAO.getAdmin());
                    // Cập nhật trạng thái booking
                    temp.setStatus(BookingStatus.COMPLETED);
                    temp.setPaymentStatus("PAID");
                    temp.setUpdatedAt();
                    BookingService.update(temp);

                    // Cập nhật trạng thái máy thành AVAILABLE
                    if (Users.id_pc > 0) {
                        Pcs x = PcsService.getPcsById(Users.id_pc);
                        if (x != null) {
                            x.setStatus(PCStatus.AVAILABLE);
                            PcsService.update(x);

                        }
                        Users.id_pc = 0; // Reset id_pc
                    }
                    Transactions tran = new Transactions(Users.curentUser.getUserId(),
                            temp.getBookingId(), TransactionType.PAYMENT, temp.getGrandTotal(), "");
                    TransactionService.add(tran);
                    System.out.println("======================================");
                    System.out.println("THANH TOAN THANH CONG!");
                    System.out.println("So du con lai: " + cu.getBalance() + " VND");
                    System.out.println("Cam on quy khach! Hen gap lai!");
                    System.out.println("======================================");
                }
                break;
            case 2:
                System.out.println("Ban chua thanh toan. Vui long thanh toan truoc khi ket thuc!");
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                break;
        }
    }
}
