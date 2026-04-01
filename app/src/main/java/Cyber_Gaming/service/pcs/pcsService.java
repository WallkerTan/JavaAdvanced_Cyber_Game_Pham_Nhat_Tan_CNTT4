package Cyber_Gaming.service.pcs;

import java.util.*;
import Cyber_Gaming.dao.implementation.pcsIMPL;
import Cyber_Gaming.unity.pcs;
import Cyber_Gaming.unity.enums.PCStatus;

public class pcsService {

    private static final pcsIMPL pcsDAO = new pcsIMPL();
    private static Map<Integer, pcs> pcsMap;

    private static void loadData() {
        pcsMap = pcsDAO.getAllpcs();
    }

    // ================== READ ==================
    public static void showAllpcs() {
        loadData();

        if (pcsMap.isEmpty()) {
            System.out.println("Hien tai khong co may nao!");
            return;
        }

        for (pcs p : pcsMap.values()) {
            System.out.println(p.toString());
        }
    }

    public static void showAllpcsByStatus(PCStatus s) {
        pcsMap = pcsDAO.getAllpcsByStatus(s);
        if (pcsMap.isEmpty()) {
            System.out.println("Hien tai khong co may nao!");
            return;
        }
        for (pcs p : pcsMap.values()) {
            System.out.println(p.toString());
        }
    }

    public static pcs getPcsById(int id) {
        loadData();
        return pcsMap.get(id);
    }

    public static List<pcs> getAvailablePcs() {
        loadData();
        List<pcs> list = new ArrayList<>();

        for (pcs p : pcsMap.values()) {
            if (p.getStatus() == PCStatus.AVAILABLE) {
                list.add(p);
            }
        }
        return list;
    }

    public static List<pcs> getByArea(String area) {
        loadData();
        List<pcs> list = new ArrayList<>();

        for (pcs p : pcsMap.values()) {
            if (p.getArea().toString().equalsIgnoreCase(area)) {
                list.add(p);
            }
        }
        return list;
    }

    // ================== CREATE ==================
    public static boolean add(pcs p) {
        if (p == null)
            return false;
        return pcsDAO.insertPcs(p);
    }

    // ================== UPDATE ==================
    public static boolean update(pcs p) {
        if (p == null)
            return false;
        return pcsDAO.updatePcs(p.getPcId(), p);
    }

    public static boolean updateStatus(int pcId, PCStatus status) {
        pcs p = getPcsById(pcId);
        if (p == null)
            return false;

        p.setStatus(status);
        return pcsDAO.updatePcs(pcId, p);
    }

    // ================== DELETE ==================
    public static boolean delete(int id) {
        return pcsDAO.deletePcs(id);
    }

    // ================== CHECK ==================
    public static boolean isPcAvailable(int pcId) {
        pcs p = getPcsById(pcId);
        return p != null && p.getStatus() == PCStatus.AVAILABLE;
    }
}
