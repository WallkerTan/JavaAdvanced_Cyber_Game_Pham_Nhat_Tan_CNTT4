package cyberGaming.dao;

import java.util.Map;
import cyberGaming.unity.Users;
import cyberGaming.unity.enums.UserRole;

public interface UserRepository {
    // lấy toàn bộ người dùng khong phải admin
    public Map<Integer, Users> getAlluser();
public Map<Integer, Users> getAlluserByRole(UserRole role);
    // lấy admin
    public Users getAdmin();
    // tìm theo tên
    public Users findByUsername(String username);

    // CRUD người dùng
    public boolean insertUser(Users u);

    public boolean updateUser(int id, Users u);

    public boolean deleteUser(int id);
}
