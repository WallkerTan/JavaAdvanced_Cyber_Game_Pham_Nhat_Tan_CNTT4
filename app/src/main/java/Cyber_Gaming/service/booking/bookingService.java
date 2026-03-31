package Cyber_Gaming.service.booking;

import java.util.Map;
import Cyber_Gaming.dao.implementation.bookingIMPL;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.pcs;

public class bookingService {

    private static final bookingIMPL bookingDAO = new bookingIMPL();
    private static Map<Integer, bookings> bookingMap;

    public static void loading() {
        bookingMap = bookingDAO.getAllbooking();
    }

    private static void showAllBooking() {
        loading();

        if (bookingMap.isEmpty()) {
            System.out.println("khong co don hang nao");
            return;
        }

        for (bookings bookings : bookingMap.values()) {
            bookings.toString();
        }
    }

    private static bookings getByid(int id) {
        loading();
        return bookingMap.get(id);
    }

    private static boolean add(bookings b) {
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
