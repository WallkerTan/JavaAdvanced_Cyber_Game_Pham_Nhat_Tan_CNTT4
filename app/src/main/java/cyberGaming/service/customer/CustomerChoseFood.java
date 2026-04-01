package cyberGaming.service.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import cyberGaming.dao.implementation.UserIMPL;
import cyberGaming.service.booking.BookingService;
import cyberGaming.service.bookingFoodIteam.BookingFoodIteamService;
import cyberGaming.service.common.Function;
import cyberGaming.service.foods.FoodService;
import cyberGaming.unity.BookingFoodItems;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.Foods;
import cyberGaming.unity.Users;



public class CustomerChoseFood {
    public static UserIMPL userDAO = new UserIMPL();

    public static void choseFood(Scanner sc) {
        System.out.println("-----------------------------");
        FoodService.showAllfood();
        System.out.println("-----------------------------");
        System.out.printf("-> ");
        int choice_id = Function.IntInput(sc);
        Foods f = FoodService.getFoodByid(choice_id);
        if (f.getStockQuantity() <= 0) {
            System.out.println("het hang");
            return;
        }
        System.out.printf("nhap so luong: ");
        int qtt = Function.IntInput(sc);
        if (qtt > f.getStockQuantity()) {
            System.out.println("so luong sp khong du");
            return;
        }

        f.setStockQuantity(f.getStockQuantity() - qtt);
        FoodService.update(f);
        Users t = Users.curentUser;
        int bk_id;
        Bookings b = BookingService.getByUsersID(t.getUserId());
        // kiểm tra xem có đơn hàng chưa (có đang dùng mt ko)

        if (b != null) {
            BookingFoodIteamService.add(
                    new BookingFoodItems(b.getBookingId(), f.getFoodId(), qtt, f.getPrice()));
        } else {
            Bookings z = new Bookings(Users.curentUser.getUserId(), Users.id_pc,
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

            BookingFoodIteamService.add(
                    new BookingFoodItems(z.getBookingId(), f.getFoodId(), qtt, f.getPrice()));
        }

        t.setMoneyOwed(t.getMoneyOwed() + qtt * f.getPrice());
        userDAO.updateUser(t.getUserId(), t);

    }
}
