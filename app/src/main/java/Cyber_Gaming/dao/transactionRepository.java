package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.transactions;

//lịch sử giao dịch
public interface transactionRepository {
    //lấy toàn bộ lịch sử 
    public Map<Integer,transactions> getAlltransaction();
    //lấy toàn bộ lịch sử giao dịch của 1 người dùng
    public Map<Integer, transactions> getTransactionByid(int id_User);
    //Crud
    public boolean addTransaction(transactions tran);
    public boolean deleteTransaction(int id);
}
