package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.users;

public interface userRepository {
    // lấy toàn bộ người dùng khong phải admin
    public Map<Integer, users> getAlluser();

    // lấy admin
    public users getAdmin();
    // tìm theo tên
    public users findByUsername(String username);

    // CRUD người dùng
    public boolean insertUser(users u);

    public boolean updateUser(int id, users u);

    public boolean deleteUser(int id);
}
