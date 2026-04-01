package cyberGaming.dao;

import java.util.Map;
import cyberGaming.unity.Transactions;

//lịch sử giao dịch
public interface TransactionRepository {
    //lấy toàn bộ lịch sử 
    public Map<Integer,Transactions> getAlltransaction();
    //lấy toàn bộ lịch sử giao dịch của 1 người dùng
    public Map<Integer, Transactions> getTransactionByid(int id_User);
    //Crud
    public boolean addTransaction(Transactions tran);
    public boolean deleteTransaction(int id);
}
