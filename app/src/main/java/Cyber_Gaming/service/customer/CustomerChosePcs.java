package Cyber_Gaming.service.customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import Cyber_Gaming.service.booking.bookingService;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.service.pcs.pcsService;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.pcs;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.PCStatus;

public class CustomerChosePcs {
    public static void chosePcs(Scanner sc) {
        System.out.println("----------------------------------");
        pcsService.showAllpcs();
        System.out.println("----------------------------------");
        System.out.printf("-> ");
        int id_chose = function.IntInput(sc);
        pcs temp = pcsService.getPcsById(id_chose);
        if (temp != null && (temp.getStatus() == PCStatus.IN_USE
                || temp.getStatus() == PCStatus.MAINTENANCE)) {
            System.out.println("khong the chon , may dang duoc su dung hoa bao tri");
            return;
        } else if (temp != null) {
            // có thể chọn
            temp.setStatus(PCStatus.IN_USE);
            pcs.curentPcs = temp;
            bookings b = new bookings(users.curentUser.getUserId(), temp.getPcId(),
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
            bookingService.add(b);
            System.out.println("ban da chon may id: " + temp.getPcId());
            users.id_pc = temp.getPcId();
            pcsService.update(temp);
        }

    }
}
