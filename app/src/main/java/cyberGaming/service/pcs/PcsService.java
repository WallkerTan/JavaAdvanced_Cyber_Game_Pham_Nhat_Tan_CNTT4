package cyberGaming.service.pcs;

import java.util.*;
import cyberGaming.dao.implementation.PcsIMPL;
import cyberGaming.unity.Pcs;
import cyberGaming.unity.enums.PCStatus;

public class PcsService {

    private static final PcsIMPL PcsDAO = new PcsIMPL();
    private static Map<Integer, Pcs> PcsMap;

    private static void loadData() {
        PcsMap = PcsDAO.getAllPcs();
    }

    // ================== READ ==================
    public static void showAllPcs() {
        loadData();

        if (PcsMap.isEmpty()) {
            System.out.println("Hien tai khong co may nao!");
            return;
        }

        for (Pcs p : PcsMap.values()) {
            System.out.println(p.toString());
        }
    }

    public static void showAllPcsByStatus(PCStatus s) {
        PcsMap = PcsDAO.getAllPcsByStatus(s);
        if (PcsMap.isEmpty()) {
            System.out.println("Hien tai khong co may nao!");
            return;
        }
        for (Pcs p : PcsMap.values()) {
            System.out.println(p.toString());
        }
    }

    public static Pcs getPcsById(int id) {
        loadData();
        return PcsMap.get(id);
    }

    public static List<Pcs> getAvailablePcs() {
        loadData();
        List<Pcs> list = new ArrayList<>();

        for (Pcs p : PcsMap.values()) {
            if (p.getStatus() == PCStatus.AVAILABLE) {
                list.add(p);
            }
        }
        return list;
    }

    public static List<Pcs> getByArea(String area) {
        loadData();
        List<Pcs> list = new ArrayList<>();

        for (Pcs p : PcsMap.values()) {
            if (p.getArea().toString().equalsIgnoreCase(area)) {
                list.add(p);
            }
        }
        return list;
    }

    // ================== CREATE ==================
    public static boolean add(Pcs p) {
        if (p == null)
            return false;
        return PcsDAO.insertPcs(p);
    }

    // ================== UPDATE ==================
    public static boolean update(Pcs p) {
        if (p == null)
            return false;
        return PcsDAO.updatePcs(p.getPcId(), p);
    }

    public static boolean updateStatus(int pcId, PCStatus status) {
        Pcs p = getPcsById(pcId);
        if (p == null)
            return false;

        p.setStatus(status);
        return PcsDAO.updatePcs(pcId, p);
    }

    // ================== DELETE ==================
    public static boolean delete(int id) {
        return PcsDAO.deletePcs(id);
    }

    // ================== CHECK ==================
    public static boolean isPcAvailable(int pcId) {
        Pcs p = getPcsById(pcId);
        return p != null && p.getStatus() == PCStatus.AVAILABLE;
    }
}
