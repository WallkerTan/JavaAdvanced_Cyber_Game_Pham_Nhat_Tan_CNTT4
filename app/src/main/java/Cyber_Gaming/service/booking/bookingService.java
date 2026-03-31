package Cyber_Gaming.service.booking;

import java.util.Map;
import Cyber_Gaming.dao.implementation.bookingIMPL;
import Cyber_Gaming.unity.bookings;

public class bookingService {

    private static final bookingIMPL bookingDAO = new bookingIMPL();
    private static Map<Integer, bookings> bookingMap;

    public static void loading() {
        bookingMap = bookingDAO.getAllbooking();
    }

    public static void showAllBooking() {
        loading();

        if (bookingMap.isEmpty()) {
            System.out.println("khong co don hang nao");
            return;
        }

        for (bookings bookings : bookingMap.values()) {
            bookings.toString();
        }
    }

    public static bookings getByUsersID(int user_id) {
        return bookingDAO.getByUserID(user_id);
    }

    public static bookings getByid(int id) {
        loading();
        return bookingMap.get(id);
    }

    public static boolean add(bookings b) {
        if (b == null) {
            return false;
        }
        return bookingDAO.insertBooking(b);
    }

    public static boolean update(bookings p) {
        if (p == null)
            return false;
        return bookingDAO.updateBooking(p.getBookingId(), p);
    }

    public static boolean deletd(int id) {
        return bookingDAO.deleteBooking(id);
    }

}
