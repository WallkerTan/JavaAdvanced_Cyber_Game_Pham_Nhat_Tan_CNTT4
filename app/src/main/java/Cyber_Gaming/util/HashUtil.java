package Cyber_Gaming.util;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
    // mã hóa mật khẩu
    public static String hashPassword(String PassWord) {
        return BCrypt.hashpw(PassWord, BCrypt.gensalt());
    }
    // check pasword
    public static boolean checkPassword(String input, String hash) {
        return org.mindrot.jbcrypt.BCrypt.checkpw(input, hash);
    }
}
