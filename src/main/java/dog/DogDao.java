package dog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dog.entity.Dog;

public class DogDao {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/dog");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Dog getDog(int dogId) {
		Connection conn = getConnection();
		String sql = "SELECT d.* u.uId FROM dog JOIN users ON u.uId=d.uId WHERE d.dogId=?";
		Dog dog = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dogId);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dog = new Dog(rs.getInt(1), LocalDate.parse(rs.getString(2)), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6));
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dog;
	}
	
	public List<Dog> getDogList(String field, String query, int num, int offset) {
		Connection conn = getConnection();
		String sql = "SELECT d.* u.uId FROM dog JOIN users ON u.uId=d.uId WHERE " + field + " = ? LIMIT ? OFFSET ?";
		List<Dog> list = new ArrayList<Dog>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Dog dog = new Dog(rs.getInt(1), LocalDate.parse(rs.getString(2)), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				list.add(dog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void insertDog(Dog dog) {
		Connection conn = getConnection();
		String sql = "insert into dog values (default, ?, ?, ?, ?, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dog.getbDate().toString());
			pstmt.setString(2, dog.getBreed());
			pstmt.setString(3, dog.getGender());
			pstmt.setString(4, dog.getDname());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateDog(Dog dog) {
		Connection conn = getConnection();
		String sql = "update dog set bDate=?, breed=?, gender=?, dname=?, where dogId=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dog.getbDate().toString());
			pstmt.setString(2, dog.getBreed());
			pstmt.setString(3, dog.getGender());
			pstmt.setString(4, dog.getDname());
			pstmt.setInt(5, dog.getDogId());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getDogCount(String field, String query) {
		return 0;
	}
}