package Cyber_Gaming.service.bookingFoodIteam;

import Cyber_Gaming.dao.implementation.bookingFT_IMPL;
import Cyber_Gaming.unity.booking_food_items;

public class bookingFoodIteamService {
    public static bookingFT_IMPL BFTDAO = new bookingFT_IMPL();

    public static boolean add(booking_food_items bft) {
        if (bft == null)
            return false;
        return BFTDAO.insertBooKingFt(bft);
    }

    public static boolean upadte(int id, booking_food_items bft) {
        return BFTDAO.updateBookingFt(id, bft);
    }
}
