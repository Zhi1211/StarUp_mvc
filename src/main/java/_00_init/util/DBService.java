//package _00_init.util;
//
//public class DBService {
//	public static final String host = "127.0.0.1";
//	public static final String DB_MYSQL = "MYSQL";
//
//	public static final String DB_TYPE = DB_MYSQL;
//
//	private static final String DBURL_MySQL = "jdbc:mysql://" + host
//			+ "/star_up?useUnicode=yes&characterEncoding=utf8&useSSL=false";
//	public static final String USERID_MySQL = "root";
//	public static final String PSWD_MySQL = "qw82410a";
//
//	private static final String DROP_User_MySQL = "DROP Table IF EXISTS user ";
//
//
//	private static final String DROP_Orders_MySQL = "DROP Table IF EXISTS Orders ";
//
//
//	private static final String DROP_OrderItems_MySQL = "DROP TABLE IF EXISTS OrderItems";
//
//	
//	private static final String CREATE_User_MySQL = "create table User"
//			+ "(user_id INT not null AUTO_INCREMENT primary key, " 
//			+ "account VARCHAR(45) not null, "
//			+ "password VARCHAR(45) not null, "
//			+ "name VARCHAR(45) not null, "
//			+ "nickname VARCHAR(45) not null, "			
//			+ "gender VARCHAR(10) not null, "
//			+ "birthday DATETIME not null, "
//			+ "phone VARCHAR(45) not null, "
//			+ "address VARCHAR(45) not null, "
//			+ "photo LONGBLOB not null, "
//			+ "unpaid_amount decimal(10,1), "
//			+ "introduction LONGTEXT, "
//			+ "regTime DATETIME  not null"
//			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";
//
//	
//	public static String getDropUser() {
//		String drop = null;
//
//			drop = DROP_User_MySQL;
//
//		return drop;
//	}
//
//	public static String getCreateUser() {
//		String create = null;
//
//			create = CREATE_User_MySQL;
//
//		return create;
//	}
//	
//	
//	public static String getDropOrders() {
//		String drop = null;
//
//			drop = DROP_Orders_MySQL;
//
//		return drop;
//	}
//
//	public static String getDropOrderItems() {
//		String drop = null;
//
//			drop = DROP_OrderItems_MySQL;
//
//		return drop;
//	}
//
//	public static String getDbUrl() {
//		String url = null;
//
//			url = DBURL_MySQL;
//
//		return url;
//	}
//
//	public static String getUser() {
//		String user = null;
//
//			user = USERID_MySQL;
//
//		return user;
//	}
//
//	public static String getPassword() {
//		String pswd = null;
//		
//			pswd = PSWD_MySQL;
//		
//		return pswd;
//	}
//
//}
