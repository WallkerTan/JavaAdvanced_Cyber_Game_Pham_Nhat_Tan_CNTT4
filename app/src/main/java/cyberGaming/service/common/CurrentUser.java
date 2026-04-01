package cyberGaming.service.common;

import cyberGaming.unity.Users;

public class CurrentUser {

    private static Users currentUser = null;

    public static void setCurrentUser(Users user) {
        currentUser = user;
    }

    public static Users getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserId() {
        return currentUser != null ? currentUser.getUserId() : -1;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }
}
