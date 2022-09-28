package org.edwith.webbe.cardmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

public class BusinessCardManagerDao {
	
	private static String dburl = "jdbc:mysql://localhost:3306/cardmanagerdb?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
	private static String dbUser = "cardmanageruser"; 
	private static String dbpasswd = "1111"; 
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> BusinessCardList = new ArrayList<>();
    		
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String sql = "SELECT NAME, PHONE_NUM, COMPANY_NAME FROM TB_CARDMANAGER WHERE 1=1 AND NAME LIKE ?";
    	
    	try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
    		
    		ps.setString(1, "%"+keyword+"%");
    		
    		try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String name = rs.getString("NAME");
					String phoneNum = rs.getString("PHONE_NUM");
					String companyName = rs.getString("COMPANY_NAME");

					BusinessCard businessCard = new BusinessCard(name, phoneNum, companyName);
					BusinessCardList.add(businessCard); // list에 반복할때마다 Role인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
    	
    	return BusinessCardList;
    }

    public void addBusinessCard(BusinessCard businessCard){
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String sql = "INSERT INTO tb_cardmanager (name, phone_num, company_name) VALUES (?, ?, ?)";
    	
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
