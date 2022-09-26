package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class BusinessCardManagerDao {
	
	private static String dburl = "jdbc:mysql://localhost:3306/cardmanagerdb?useSSL=false";
	private static String dbUser = "root"; //cardmanageruser
	private static String dbpasswd = "1111"; //1111
	
//    public List<BusinessCard> searchBusinessCard(String keyword){
//    	try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//    	
//    	String sql = "SELECT " //SELECT DATE_FORMAT(CREATE_DTE, '%a %b %d ') FROM TB_CARDMANAGER;
//    }

    public void addBusinessCard(BusinessCard businessCard){
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String sql = "INSERT INTO tb_cardmanager (name, phone_num, company_name, create_dte) VALUES ( ?, ?, ?, NOW() )";
    	
    	try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
    	
    }
}
