package com.GD.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		try {
		     Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		try {
//		     Connection conn = DriverManager.getConnection ("jdbc:mysql://192.168.229.128:3306/gd","gd","gd123xxx~!@#");
		     Connection conn = DriverManager.getConnection ("jdbc:mysql://192.168.229.128:3306/gd?useUnicode=true&characterEncoding=UTF8","gd","gd123xxx~!@#");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}
