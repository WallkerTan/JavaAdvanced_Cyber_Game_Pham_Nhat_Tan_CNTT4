package Cyber_Gaming.service.transaction;

import java.util.Map;
import Cyber_Gaming.dao.implementation.transactionIMPL;
import Cyber_Gaming.unity.transactions;

public class transactionService {
    public static transactionIMPL tranDAO = new transactionIMPL();
    public static Map<Integer, transactions> tranMap;

    public static void loading() {
        tranMap = tranDAO.getAlltransaction();
    }

    public static void showAllTransaction() {
        loading();

        if (tranMap.isEmpty()) {
            System.out.println("khong co giao dich nao");
            return;
        }

        for (transactions transactions : tranMap.values()) {
            transactions.toString();
        }
    }

    public static boolean add(transactions t) {
        if (t == null)
            return false;
        return tranDAO.addTransaction(t);
    }

    public static boolean delete(int id) {
        return tranDAO.deleteTransaction(id);
    }
}
