package cyberGaming.service.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import cyberGaming.service.booking.BookingService;
import cyberGaming.service.common.Function;
import cyberGaming.service.pcs.PcsService;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.Pcs;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.PCStatus;

public class CustomerChosePcs {
    public static void chosePcs(Scanner sc) {
        System.out.println("----------------------------------");
        PcsService.showAllPcs();
        System.out.println("----------------------------------");
        System.out.printf("-> ");
        int id_chose = Function.IntInput(sc);
        Pcs temp = PcsService.getPcsById(id_chose);
        if (temp != null && (temp.getStatus() == PCStatus.IN_USE
                || temp.getStatus() == PCStatus.MAINTENANCE)) {
            System.out.println("khong the chon , may dang duoc su dung hoa bao tri");
            return;
        } else if (temp != null) {
            // có thể chọn
            temp.setStatus(PCStatus.IN_USE);
            Pcs.curentPcs = temp;
            Bookings b = new Bookings(Users.curentUser.getUserId(), temp.getPcId(),
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
            BookingService.add(b);
            System.out.println("ban da chon may id: " + temp.getPcId());
            Users.id_pc = temp.getPcId();
            PcsService.update(temp);
        }

    }
}
