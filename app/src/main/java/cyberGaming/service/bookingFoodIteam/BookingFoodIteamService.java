package cyberGaming.service.bookingFoodIteam;

import cyberGaming.dao.implementation.BookingFT_IMPL;
import cyberGaming.unity.BookingFoodItems;

public class BookingFoodIteamService {
    public static BookingFT_IMPL BFTDAO = new BookingFT_IMPL();

    public static boolean add(BookingFoodItems bft) {
        if (bft == null)
            return false;
        return BFTDAO.insertBooKingFt(bft);
    }

    public static boolean upadte(int id, BookingFoodItems bft) {
        return BFTDAO.updateBookingFt(id, bft);
    }
}
