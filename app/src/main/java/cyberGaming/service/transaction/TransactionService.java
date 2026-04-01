package cyberGaming.service.transaction;

import java.util.Map;
import cyberGaming.dao.implementation.TransactionIMPL;
import cyberGaming.unity.Transactions;

public class TransactionService {
    public static TransactionIMPL tranDAO = new TransactionIMPL();
    public static Map<Integer, Transactions> tranMap;

    public static void loading() {
        tranMap = tranDAO.getAlltransaction();
    }
    
    public static void showAllTransactionByid(int id) {
        Map<Integer, Transactions> M = tranDAO.getTransactionByid(id);
        for (Transactions t : M.values()) {
            System.out.println(t.toString());
        }
    }

    public static void showAllTransaction() {
        loading();

        if (tranMap.isEmpty()) {
            System.out.println("khong co giao dich nao");
            return;
        }

        for (Transactions transactions : tranMap.values()) {
            transactions.toString();
        }
    }


    public static boolean add(Transactions t) {
        if (t == null)
            return false;
        return tranDAO.addTransaction(t);
    }

    public static boolean delete(int id) {
        return tranDAO.deleteTransaction(id);
    }
}
