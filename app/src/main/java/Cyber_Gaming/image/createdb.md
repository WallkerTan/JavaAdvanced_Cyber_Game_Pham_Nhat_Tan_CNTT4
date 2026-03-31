users admin = new users("admin", HashUtil.hashPassword("123456789aA."), "PhamTan",
                "0966247783", UserRole.ADMIN);

        // Tạo 1 staff
        users staff = new users("staff1", HashUtil.hashPassword("Staff1234!"), "NguyenVanB",
                "0912345678", UserRole.STAFF);

        // Tạo 10 user khách hàng
        users user1 = new users("user1", HashUtil.hashPassword("User1234!"), "LeThiA", "0980000001",
                UserRole.CUSTOMER);
        users user2 = new users("user2", HashUtil.hashPassword("User1234!"), "LeThiB", "0980000002",
                UserRole.CUSTOMER);
        users user3 = new users("user3", HashUtil.hashPassword("User1234!"), "LeThiC", "0980000003",
                UserRole.CUSTOMER);
        users user4 = new users("user4", HashUtil.hashPassword("User1234!"), "LeThiD", "0980000004",
                UserRole.CUSTOMER);
        users user5 = new users("user5", HashUtil.hashPassword("User1234!"), "LeThiE", "0980000005",
                UserRole.CUSTOMER);
        users user6 = new users("user6", HashUtil.hashPassword("User1234!"), "LeThiF", "0980000006",
                UserRole.CUSTOMER);
        users user7 = new users("user7", HashUtil.hashPassword("User1234!"), "LeThiG", "0980000007",
                UserRole.CUSTOMER);
        users user8 = new users("user8", HashUtil.hashPassword("User1234!"), "LeThiH", "0980000008",
                UserRole.CUSTOMER);
        users user9 = new users("user9", HashUtil.hashPassword("User1234!"), "LeThiI", "0980000009",
                UserRole.CUSTOMER);
        users user10 = new users("user10", HashUtil.hashPassword("User1234!"), "LeThiJ",
                "0980000010", UserRole.CUSTOMER);
        userDAO.insertUser(admin);
        userDAO.insertUser(user1);
        userDAO.insertUser(user2);
        userDAO.insertUser(user3);
        userDAO.insertUser(user4);
        userDAO.insertUser(user5);
        userDAO.insertUser(user6);
        userDAO.insertUser(user7);
        userDAO.insertUser(user8);
        userDAO.insertUser(user9);
        userDAO.insertUser(user10);
        userDAO.insertUser(staff);