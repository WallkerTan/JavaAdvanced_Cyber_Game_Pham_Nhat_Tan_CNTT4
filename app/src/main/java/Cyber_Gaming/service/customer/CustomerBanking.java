package Cyber_Gaming.service.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.booking.bookingService;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.service.pcs.pcsService;
import Cyber_Gaming.service.transaction.transactionService;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.pcs;
import Cyber_Gaming.unity.transactions;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.BookingStatus;
import Cyber_Gaming.unity.enums.PCStatus;
import Cyber_Gaming.unity.enums.TransactionType;

public class CustomerBanking {
    public static userIMPL userDAO = new userIMPL();

    public static void isBanking(Scanner sc) {
        // Lấy booking hiện tại của user (đang sử dụng)
        bookings temp = bookingService.getByUsersID(users.curentUser.getUserId());
        if (temp == null) {
            System.out.println("Ban chua su dung dich vu");
            return;
        }
        temp.setUpdatedAt();
        System.out.println(temp.getUpdatedAt());
        bookingService.update(temp);


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

        switch (function.IntInput(sc)) {
            case 1:
                users cu = users.curentUser;
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
                    bookingService.update(temp);

                    // Cập nhật trạng thái máy thành AVAILABLE
                    if (users.id_pc > 0) {
                        pcs x = pcsService.getPcsById(users.id_pc);
                        if (x != null) {
                            x.setStatus(PCStatus.AVAILABLE);
                            pcsService.update(x);

                        }
                        users.id_pc = 0; // Reset id_pc
                    }
                    transactions tran = new transactions(users.curentUser.getUserId(),
                            temp.getBookingId(), TransactionType.PAYMENT, temp.getGrandTotal(), "");
                    transactionService.add(tran);
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
