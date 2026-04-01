package Cyber_Gaming.service.staff;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.bookingIMPL;
import Cyber_Gaming.service.booking.bookingService;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.enums.BookingStatus;
import Cyber_Gaming.service.common.function;

public class StaffManagerBooking {

    private static final bookingIMPL bookingDAO = new bookingIMPL();

    // ====================== XEM DANH SACH DON HANG ======================
    public static void showAllBookings(Scanner sc) {
        bookingService.showAllBooking(); // Giả sử bạn có hàm này trong bookingService

        System.out.println("\n1. Cap nhat trang thai don hang");
        System.out.println("0. Quay lai");
        System.out.print("Chon chuc nang: ");
        int choice = function.IntInput(sc);

        if (choice == 1) {
            updateBookingStatus(sc);
        }
    }

    // ====================== CAP NHAT TRANG THAI DON HANG ======================
    public static void updateBookingStatus(Scanner sc) {
        bookingService.showAllBooking();

        System.out.println("----------------------------");
        System.out.print("Nhap ID don hang muon cap nhat: ");
        int bookingId = function.IntInput(sc);

        bookings b = bookingService.getByid(bookingId);
        if (b == null) {
            System.out.println("Don hang khong ton tai!");
            return;
        }

        System.out
                .println("Don hang ID: " + bookingId + " - Trang thai hien tai: " + b.getStatus());

        System.out.println("Chon trang thai moi:");
        System.out.println("1. CONFIRMED");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");
        System.out.print("Chon: ");
        int choice = function.IntInput(sc);

        BookingStatus newStatus = switch (choice) {
            case 1 -> BookingStatus.CONFIRMED;
            case 2 -> BookingStatus.IN_PROGRESS;
            case 3 -> BookingStatus.COMPLETED;
            case 4 -> BookingStatus.CANCELLED;
            default -> null;
        };

        if (newStatus == null) {
            System.out.println("Lua chon khong hop le!");
            return;
        }

        b.setStatus(newStatus);

        boolean success = bookingDAO.updateBooking(bookingId, b);

        if (success) {
            System.out.println("Cap nhat trang thai don hang thanh cong!");
        } else {
            System.out.println("Cap nhat that bai!");
        }
    }
}
