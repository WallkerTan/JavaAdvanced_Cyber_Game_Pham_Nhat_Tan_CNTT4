package Cyber_Gaming.service.admin;

import java.util.Scanner;
import Cyber_Gaming.dao.implementation.pcsIMPL;
import Cyber_Gaming.service.common.function;
import Cyber_Gaming.service.pcs.pcsService;
import Cyber_Gaming.unity.pcs;
import Cyber_Gaming.unity.enums.AreaType;
import Cyber_Gaming.unity.enums.PCStatus;

public class AdminManagerPcs {
    public static pcsIMPL pcsDAO = new pcsIMPL();

    public static void add(Scanner sc) {
        AreaType area;
        String configuration;
        double pricePerHour;
        System.out.println("Them thong tin may tram.");
        System.out.println("Khu vuc");
        System.out.println("1. STANDARD");
        System.out.println("2: STREAM_ROOM");
        System.out.println("3: VIP");
        System.out.println("!=: Thoat");
        int choice = function.IntInput(sc);
        if (choice < 1 || choice > 3) {
            System.out.println("Thoat !!");
            return;
        }
        area = (choice == 1 ? AreaType.STANDARD
                : (choice == 2 ? AreaType.STREAM_ROOM : AreaType.VIP));

        pricePerHour = (choice == 1 ? 5000 : (choice == 2 ? 10000 : 15000));
        System.out.println("nhap cau hinh");
        System.out.println("chip");
        System.out.println("1. i5-10400");
        System.out.println("2. i7-12700");
        System.out.println("!. i9-12900K");
        int c2 = function.IntInput(sc);
        configuration = (c2 == 1 ? "i5-10400 " : (c2 == 2 ? "i7-12700 " : "i9-12900k "));

        System.out.println("ram");
        System.out.println("1. 8GB");
        System.out.println("2. 16GB");
        System.out.println("!. 32GB");
        int c3 = function.IntInput(sc);
        configuration += (c3 == 1 ? "| 8GB " : (c3 == 2 ? "| 16GB " : "| 32GB "));


        System.out.println("Card");
        System.out.println("1. GTX 1650");
        System.out.println("2. RTX 3060");
        System.out.println("!. RTX 4070");
        int c4 = function.IntInput(sc);
        configuration += (c4 == 1 ? "| GTX 1650" : (c4 == 2 ? "| RTX 30360" : "| RTX 4070"));
        pcs p = new pcs(area, configuration, pricePerHour);
        pcsDAO.insertPcs(p);
        System.out.println(" da them may moi !!!");
    }

    public static void update(Scanner sc) {
        pcsService.showAllpcs();
        System.out.println("----------------------------");
        System.out.print("Nhap ID may muon sua: ");
        int id = function.IntInput(sc);

        pcs temp = pcsService.getPcsById(id);
        if (temp == null) {
            System.out.println("May khong ton tai!");
            return;
        }

        System.out.println("Dang sua may ID: " + id);

        System.out.println("Chon khu vuc:");
        System.out.println("1. STANDARD");
        System.out.println("2. VIP");
        System.out.println("3. STREAM_ROOM");
        int areaChoice = function.IntInput(sc);
        AreaType area = switch (areaChoice) {
            case 1 -> AreaType.STANDARD;
            case 2 -> AreaType.VIP;
            case 3 -> AreaType.STREAM_ROOM;
            default -> temp.getArea();
        };

        System.out.print("Nhap cau hinh moi (hien tai: " + temp.getConfiguration() + "): ");
        String configuration = function.StringInput(sc);

        System.out.print("Nhap gia moi moi gio (hien tai: " + temp.getPricePerHour() + "): ");
        double pricePerHour = function.DoubleInput(sc); // giả sử bạn có hàm này

        System.out.println("Chon trang thai:");
        System.out.println("1. AVAILABLE");
        System.out.println("2. MAINTENANCE");
        int statusChoice = function.IntInput(sc);
        PCStatus status = (statusChoice == 1) ? PCStatus.AVAILABLE : PCStatus.MAINTENANCE;

        // Cập nhật thông tin
        temp.setArea(area);
        temp.setConfiguration(configuration);
        temp.setPricePerHour(pricePerHour);
        temp.setStatus(status);

        boolean success = pcsDAO.updatePcs(id, temp);

        if (success) {
            System.out.println("Cap nhat may thanh cong!");
        } else {
            System.out.println("Cap nhat that bai!");
        }
    }

    // ====================== XÓA MÁY (Có điều kiện) ======================
    public static void delete(Scanner sc) {
        pcsService.showAllpcs();
        System.out.println("----------------------------");
        System.out.print("Nhap ID may muon xoa: ");
        int id = function.IntInput(sc);

        pcs temp = pcsService.getPcsById(id);
        if (temp == null) {
            System.out.println("May khong ton tai!");
            return;
        }

        // Điều kiện quan trọng: Chỉ cho xóa khi máy không có người đang dùng
        if (temp.getStatus() != PCStatus.AVAILABLE) {
            System.out.println("Khong the xoa may nay! May dang o trang thai " + temp.getStatus());
            System.out.println("Chi duoc xoa may o trang thai AVAILABLE.");
            return;
        }

        System.out.print("Ban chac chan muon xoa may ID " + id + " ? (1: Co / 0: Khong): ");
        int confirm = function.IntInput(sc);

        if (confirm != 1) {
            System.out.println("Da huy xoa may.");
            return;
        }

        boolean success = pcsDAO.deletePcs(id);

        if (success) {
            System.out.println("Xoa may thanh cong!");
        } else {
            System.out.println("Xoa that bai! May co the dang duoc su dung.");
        }
    }

    public static void maintenance(Scanner sc) {
        pcsService.showAllpcs();
        System.out.println("----------------------------");
        System.out.print("Nhap ID may muon bao tri: ");
        int id = function.IntInput(sc);

        pcs temp = pcsService.getPcsById(id);
        if (temp == null) {
            System.out.println("May khong ton tai!");
            return;
        }

        System.out.println("May ID " + id + " - Trang thai hien tai: " + temp.getStatus());

        // Không cho bảo trì nếu máy đang có người dùng
        if (temp.getStatus() == PCStatus.IN_USE) {
            System.out.println("Khong the bao tri! May dang co nguoi su dung.");
            return;
        }

        if (temp.getStatus() == PCStatus.MAINTENANCE) {
            // Máy đang bảo trì → Hỏi tiếp tục hay hoàn thành
            System.out.println("May nay dang trong trang thai bao tri.");
            System.out.println("1. Bao tri xong (chuyen ve AVAILABLE)");
            System.out.println("2. Tiep tuc bao tri");
            System.out.println("0. Huy");
            System.out.print("Chon: ");
            int choice = function.IntInput(sc);

            if (choice == 1) {
                temp.setStatus(PCStatus.AVAILABLE);
                if (pcsService.update(temp)) {
                    System.out.println("May da bao tri xong va san sang su dung!");
                } else {
                    System.out.println("Cap nhat that bai!");
                }
            } else if (choice == 2) {
                System.out.println("May van o trang thai bao tri.");
            } else {
                System.out.println("Da huy thao tac.");
            }

        } else {
            // Máy đang AVAILABLE → Đưa vào bảo trì
            System.out.print(
                    "Ban chac chan muon dua may ID " + id + " vao bao tri? (1: Co / 0: Khong): ");
            int confirm = function.IntInput(sc);

            if (confirm == 1) {
                temp.setStatus(PCStatus.MAINTENANCE);
                if (pcsService.update(temp)) {
                    System.out.println("May da chuyen sang trang thai bao tri thanh cong!");
                } else {
                    System.out.println("Cap nhat that bai!");
                }
            } else {
                System.out.println("Da huy thao tac.");
            }
        }
    }
}
