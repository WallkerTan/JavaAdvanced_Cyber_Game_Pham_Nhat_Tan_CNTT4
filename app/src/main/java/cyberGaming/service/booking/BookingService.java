package cyberGaming.service.booking;

import java.util.Map;
import cyberGaming.dao.implementation.BookingIMPL;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.enums.BookingStatus;

public class BookingService {

    private static final BookingIMPL bookingDAO = new BookingIMPL();
    private static Map<Integer, Bookings> bookingMap;

    public static void loading() {
        bookingMap = bookingDAO.getAllbooking();
    }

    public static void showAllBooking() {
        loading();

        if (bookingMap.isEmpty()) {
            System.out.println("khong co don hang nao");
            return;
        }

        for (Bookings bookings : bookingMap.values()) {
            System.out.println(bookings.toString());
        }
    }

    public static void showAllBookingByStatus(BookingStatus bs) {
        bookingMap = bookingDAO.getBystatus(bs);

        if (bookingMap.isEmpty()) {
            System.out.println("khong co don hang nao");
            return;
        }

        for (Bookings bookings : bookingMap.values()) {
            bookings.toString();
        }
    }

    public static Bookings getByUsersID(int user_id) {
        return bookingDAO.getByUserID(user_id);
    }

    public static Bookings getByid(int id) {
        loading();
        return bookingMap.get(id);
    }

    public static boolean add(Bookings b) {
        if (b == null) {
            return false;
        }
        return bookingDAO.insertBooking(b);
    }

    public static boolean update(Bookings p) {
        if (p == null)
            return false;
        return bookingDAO.updateBooking(p.getBookingId(), p);
    }

    public static boolean deletd(int id) {
        return bookingDAO.deleteBooking(id);
    }

}
