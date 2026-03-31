package Cyber_Gaming.service.common;

import Cyber_Gaming.unity.users;

public class CurrentUser {

    private static users currentUser = null;

    public static void setCurrentUser(users user) {
        currentUser = user;
    }

    public static users getCurrentUser() {
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
