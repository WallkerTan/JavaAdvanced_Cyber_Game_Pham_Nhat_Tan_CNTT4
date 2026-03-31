package Cyber_Gaming.service.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import Cyber_Gaming.dao.implementation.userIMPL;
import Cyber_Gaming.service.booking.bookingService;
import Cyber_Gaming.service.bookingFoodIteam.bookingFoodIteamService;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.service.foods.foodService;
import Cyber_Gaming.unity.booking_food_items;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.foods;
import Cyber_Gaming.unity.users;



public class CustomerChoseFood {
    public static userIMPL userDAO = new userIMPL();

    public static void choseFood(Scanner sc) {
        System.out.println("-----------------------------");
        foodService.showAllfood();
        System.out.println("-----------------------------");
        System.out.printf("-> ");
        int choice_id = function.IntInput(sc);
        foods f = foodService.getFoodByid(choice_id);
        if (f.getStockQuantity() <= 0) {
            System.out.println("het hang");
            return;
        }
        System.out.printf("nhap so luong: ");
        int qtt = function.IntInput(sc);
        if (qtt > f.getStockQuantity()) {
            System.out.println("so luong sp khong du");
            return;
        }

        f.setStockQuantity(f.getStockQuantity() - qtt);
        foodService.update(f);
        users t = users.curentUser;
        int bk_id;
        bookings b = bookingService.getByUsersID(t.getUserId());
        // kiểm tra xem có đơn hàng chưa (có đang dùng mt ko)

        if (b != null) {
            bookingFoodIteamService.add(
                    new booking_food_items(b.getBookingId(), f.getFoodId(), qtt, f.getPrice()));
        } else {
            bookings z = new bookings(users.curentUser.getUserId(), users.id_pc,
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

            bookingFoodIteamService.add(
                    new booking_food_items(z.getBookingId(), f.getFoodId(), qtt, f.getPrice()));
        }

        t.setMoneyOwed(t.getMoneyOwed() + qtt * f.getPrice());
        userDAO.updateUser(t.getUserId(), t);

    }
}
