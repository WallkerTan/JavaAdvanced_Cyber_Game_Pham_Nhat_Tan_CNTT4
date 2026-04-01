package cyberGaming.service.staff;

import java.util.Scanner;
import cyberGaming.dao.implementation.BookingIMPL;
import cyberGaming.service.booking.BookingService;
import cyberGaming.service.common.Function;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.enums.BookingStatus;

public class StaffManagerBooking {

    private static final BookingIMPL bookingDAO = new BookingIMPL();

    // ====================== XEM DANH SACH DON HANG ======================
    public static void showAllBookings(Scanner sc) {
        BookingService.showAllBooking(); // Giả sử bạn có hàm này trong bookingService

        System.out.println("\n1. Cap nhat trang thai don hang");
        System.out.println("0. Quay lai");
        System.out.print("Chon chuc nang: ");
        int choice = Function.IntInput(sc);

        if (choice == 1) {
            updateBookingStatus(sc);
        }
    }

    // ====================== CAP NHAT TRANG THAI DON HANG ======================
    public static void updateBookingStatus(Scanner sc) {
        BookingService.showAllBooking();

        System.out.println("----------------------------");
        System.out.print("Nhap ID don hang muon cap nhat: ");
        int bookingId = Function.IntInput(sc);

        Bookings b = BookingService.getByid(bookingId);
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
        int choice = Function.IntInput(sc);

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
