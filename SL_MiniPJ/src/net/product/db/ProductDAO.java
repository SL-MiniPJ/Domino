package net.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 커넥션 풀 (Connection Pool)
	public ProductDAO() {
		try{
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e){
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}


	// pizza 등록
	public boolean addPizza(ProductBean product) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "INSERT INTO pizza VALUES (pizza_seq.NEXTVAL,?,?,?)";
		String sql2 = "INSERT INTO nutrient_pizza VALUES "+
				"((select max(pizza_code) from pizza),?,?,?,?,?,?,?,?)";
		String sql3 = "INSERT INTO allergy_pizza VALUES ((select max(pizza_code) from pizza),?)";	

		try {
			// pizza
			pstmt = conn.prepareStatement(sql1);

			pstmt.setInt(1,product.getPizza_price());
			pstmt.setString(2,product.getPizza_name());
			pstmt.setString(3,product.getPizza_image());

			result1 = pstmt.executeUpdate();

			// pizza 영양성분
			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1,product.getStandard());
			pstmt.setInt(2,product.getWeight());
			pstmt.setInt(3,product.getTotal_weight());
			pstmt.setInt(4,product.getCalorie());
			pstmt.setInt(5,product.getProtein());
			pstmt.setInt(6,product.getSaturated_fat());
			pstmt.setInt(7,product.getSalt());
			pstmt.setInt(8,product.getSugars());

			result2 = pstmt.executeUpdate();

			// pizza 알레르기
			pstmt = conn.prepareStatement(sql3);

			pstmt.setString(1,product.getIngredient());

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("pizza 등록 성공");
			return true;

		}catch(Exception e){
			System.out.println("addPizza 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}

	// 피자 목록 보기 getPizzaList
	public List getPizzaList() {

		String sql = "SELECT  p.pizza_code, p.pizza_name, p.pizza_price, n.standard, n.weight, n.total_weight," + 
				"n.calorie, n.protein, n.saturated_fat, n.salt, n.sugars, p.pizza_image " + 
				"FROM pizza p, nutrient_pizza n "+
				"WHERE p.pizza_code=n.pizza_code(+) "+
				"ORDER BY p.pizza_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setPizza_code(rs.getInt("pizza_code"));
				product.setPizza_name(rs.getString("pizza_name"));
				product.setPizza_price(rs.getInt("pizza_price"));
				product.setStandard(rs.getString("standard"));
				product.setWeight(rs.getInt("weight"));
				product.setTotal_weight(rs.getInt("total_weight"));
				product.setCalorie(rs.getInt("calorie"));
				product.setProtein(rs.getInt("protein"));
				product.setSaturated_fat(rs.getInt("saturated_fat"));
				product.setSalt(rs.getInt("salt"));
				product.setSugars(rs.getInt("sugars"));
				product.setPizza_image(rs.getString("pizza_image"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getPizzaList 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}

	// 피자 삭제 DeletePizza
	public boolean DeletePizza(int code) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "Delete FROM nutrient_pizza WHERE pizza_code=? ";
		String sql2 = "Delete FROM allergy_pizza WHERE pizza_code=? ";
		String sql3 = "Delete FROM pizza WHERE pizza_code=? ";

		try{
			// nutrient_pizza 삭제
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, code);

			result1 = pstmt.executeUpdate();

			// allergy_pizza 삭제
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, code);

			result2 = pstmt.executeUpdate();

			// pizza 삭제
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, code);

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("pizza 삭제 성공");
			return true;

		}catch(Exception e){
			System.out.println("DeletePizza 에러 : "+e);
		}finally{
			try{
				if(pstmt!=null) {pstmt.close();}}catch(Exception e) {}
		}
		return false;
	}


	// side 등록
	public boolean addSide(ProductBean product) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "INSERT INTO side VALUES (side_seq.NEXTVAL,?,?,?)";
		String sql2 = "INSERT INTO nutrient_side VALUES "+
				"((select max(side_code) from side),?,?,?,?,?,?)";
		String sql3 = "INSERT INTO allergy_side VALUES ((select max(side_code) from side),?)";	

		try {
			// side
			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1,product.getSide_name());
			pstmt.setString(2,product.getSide_image());
			pstmt.setInt(3,product.getSide_price());

			result1 = pstmt.executeUpdate();

			// side 영양성분
			pstmt = conn.prepareStatement(sql2);

			pstmt.setInt(1,product.getCalorie());
			pstmt.setInt(2,product.getProtein());
			pstmt.setInt(3,product.getTotal_weight());
			pstmt.setInt(4,product.getSaturated_fat());
			pstmt.setInt(5,product.getSalt());
			pstmt.setInt(6,product.getSugars());

			result2 = pstmt.executeUpdate();

			// side 알레르기
			pstmt = conn.prepareStatement(sql3);

			pstmt.setString(1,product.getIngredient());

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("side 등록 성공");
			return true;

		}catch(Exception e){
			System.out.println("addSide 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}

	// Side 목록 보기 getSideList
	public List getSideList() {

		String sql = "SELECT p.side_code, p.side_name, p.side_price, n.total_weight," + 
				"n.calorie, n.protein, n.saturated_fat, n.salt, n.sugars, p.side_image " + 
				"FROM side p, nutrient_side n "+
				"WHERE p.side_code=n.side_code(+) "+
				"ORDER BY p.side_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setSide_code(rs.getInt("side_code"));
				product.setSide_name(rs.getString("side_name"));
				product.setSide_price(rs.getInt("side_price"));
				product.setTotal_weight(rs.getInt("total_weight"));
				product.setCalorie(rs.getInt("calorie"));
				product.setProtein(rs.getInt("protein"));
				product.setSaturated_fat(rs.getInt("saturated_fat"));
				product.setSalt(rs.getInt("salt"));
				product.setSugars(rs.getInt("sugars"));
				product.setSide_image(rs.getString("side_image"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getSideList 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}

	// 사이드 삭제 DeletePizza
	public boolean DeleteSide(int code) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "Delete FROM nutrient_side WHERE side_code=? ";
		String sql2 = "Delete FROM allergy_side WHERE side_code=? ";
		String sql3 = "Delete FROM side WHERE side_code=? ";

		try{
			// nutrient_pizza 삭제
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, code);

			result1 = pstmt.executeUpdate();

			// allergy_pizza 삭제
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, code);

			result2 = pstmt.executeUpdate();

			// pizza 삭제
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, code);

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("side 삭제 성공");
			return true;

		}catch(Exception e){
			System.out.println("DeleteSide 에러 : "+e);
		}finally{
			try{
				if(pstmt!=null) {pstmt.close();}}catch(Exception e) {}
		}
		return false;
	}


	// Dough 등록
	public boolean addDough(ProductBean product) {

		int result1 = 0;
		String sql1 = "INSERT INTO dough VALUES (dough_seq.NEXTVAL,?,?,?)";

		try {
			// dough
			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1,product.getDough_name());
			pstmt.setString(2,product.getDough_image());
			pstmt.setInt(3,product.getDough_price());

			result1 = pstmt.executeUpdate();

			if(result1==0) {
				return false;
			}
			System.out.println("dough 등록 성공");
			return true;

		}catch(Exception e){
			System.out.println("addSide 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}

	// Dough 목록 보기 getDoughList
	public List getDoughList() {

		String sql = "SELECT p.dough_code, p.dough_name, p.dough_price, p.dough_image "+
				"FROM dough p "+
				"ORDER BY p.dough_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setDough_code(rs.getInt("dough_code"));
				product.setDough_name(rs.getString("dough_name"));
				product.setDough_price(rs.getInt("dough_price"));
				product.setDough_image(rs.getString("dough_image"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getDoughList 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}

	// 도우 삭제 DeletePizza
	public boolean DeleteDough(int code) {

		int result1 = 0;
		String sql1 = "Delete FROM dough WHERE dough_code=? ";

		try{
			System.out.println("DelDao1");
			// nutrient_pizza 삭제
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, code);

			result1 = pstmt.executeUpdate();

			if(result1==0) {
				return false;
			}
			System.out.println("dough 삭제 성공");
			return true;

		}catch(Exception e){
			System.out.println("DeleteDough 에러 : "+e);
		}finally{
			try{
				if(pstmt!=null) {pstmt.close();}}catch(Exception e) {}
		}
		return false;
	}



	// Topping 등록
	public boolean addTopping(ProductBean product) {

		int result1 = 0;
		String sql1 = "INSERT INTO topping VALUES (topping_SEQ.NEXTVAL,?,?,?)";

		try {
			// Topping
			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1,product.getTopping_name());
			pstmt.setString(2,product.getTopping_image());
			pstmt.setInt(3,product.getTopping_price());
			System.out.println("1");
			result1 = pstmt.executeUpdate();
			System.out.println("2");
			if(result1==0) {
				return false;
			}
			System.out.println("Topping 등록 성공");
			return true;

		}catch(Exception e){
			System.out.println("addTopping 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}

	// Topping 목록 보기 getToppingList
	public List getToppingList() {

		String sql = "SELECT p.topping_code, p.topping_name, p.topping_price, p.topping_image "+
				"FROM topping p "+
				"ORDER BY p.topping_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setTopping_code(rs.getInt("topping_code"));
				product.setTopping_name(rs.getString("topping_name"));
				product.setTopping_price(rs.getInt("topping_price"));
				product.setTopping_image(rs.getString("topping_image"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getToppingList 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}

	// 토핑 삭제 DeletePizza
	public boolean DeleteTopping(int code) {

		int result1 = 0;
		String sql1 = "Delete FROM topping WHERE topping_code=? ";

		try{
			// nutrient_pizza 삭제
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, code);

			result1 = pstmt.executeUpdate();

			if(result1==0) {
				return false;
			}
			System.out.println("topping 삭제 성공");
			return true;

		}catch(Exception e){
			System.out.println("DeleteTopping 에러 : "+e);
		}finally{
			try{
				if(pstmt!=null) {pstmt.close();}}catch(Exception e) {}
		}
		return false;
	}


	// other 등록
	public boolean addOther(ProductBean product) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "INSERT INTO other VALUES (other_seq.NEXTVAL,?,?,?)";
		String sql2 = "INSERT INTO nutrient_other VALUES "+
				"((select max(Other_code) from Other),?,?,?,?,?)";
		String sql3 = "INSERT INTO allergy_Other VALUES ((select max(other_code) from other),?)";	

		try {
			// Other
			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1,product.getOther_name());
			pstmt.setString(2,product.getOther_image());
			pstmt.setInt(3,product.getOther_price());

			result1 = pstmt.executeUpdate();

			// Other 영양성분
			pstmt = conn.prepareStatement(sql2);

			pstmt.setInt(1,product.getCalorie());
			pstmt.setInt(2,product.getProtein());
			pstmt.setInt(3,product.getSaturated_fat());
			pstmt.setInt(4,product.getSalt());
			pstmt.setInt(5,product.getSugars());

			result2 = pstmt.executeUpdate();

			// Other 알레르기
			pstmt = conn.prepareStatement(sql3);

			pstmt.setString(1,product.getIngredient());

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("Other 등록 성공");
			return true;

		}catch(Exception e){
			System.out.println("addOther 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}

	// Other 목록 보기 getOtherList
	public List getOtherList() {

		String sql = "SELECT p.other_code, p.other_name, p.other_price, " + 
				"n.calorie, n.protein, n.saturated_fat, n.salt, n.sugars, p.other_image " + 
				"FROM other p, nutrient_Other n "+
				"WHERE p.other_code=n.other_code(+) "+
				"ORDER BY p.other_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setOther_code(rs.getInt("other_code"));
				product.setOther_name(rs.getString("other_name"));
				product.setOther_price(rs.getInt("other_price"));
				product.setCalorie(rs.getInt("calorie"));
				product.setProtein(rs.getInt("protein"));
				product.setSaturated_fat(rs.getInt("saturated_fat"));
				product.setSalt(rs.getInt("salt"));
				product.setSugars(rs.getInt("sugars"));
				product.setOther_image(rs.getString("other_image"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getOtherList 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}


	// 음료/기타 삭제 DeleteOther
	public boolean DeleteOther(int code) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "Delete FROM nutrient_other WHERE other_code=? ";
		String sql2 = "Delete FROM allergy_other WHERE other_code=? ";
		String sql3 = "Delete FROM other WHERE other_code=? ";

		try{
			System.out.println("DelDao1");
			// nutrient_pizza 삭제
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, code);

			result1 = pstmt.executeUpdate();

			System.out.println("DelDao2");
			// allergy_pizza 삭제
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, code);

			result2 = pstmt.executeUpdate();

			System.out.println("DelDao3");
			// pizza 삭제
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, code);

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("other 삭제 성공");
			return true;

		}catch(Exception e){
			System.out.println("DeleteOther 에러 : "+e);
		}finally{
			try{
				if(pstmt!=null) {pstmt.close();}}catch(Exception e) {}
		}
		return false;
	}


// 추가 1

	// 피자 알레르기 getPizzaAllergy
	public List getPizzaAllergy() {

		String sql = "SELECT  p.pizza_name, a.ingredient "+
				"FROM pizza p, allergy_pizza a "+
				"WHERE p.pizza_code=a.pizza_code(+) "+
				"ORDER BY p.pizza_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setPizza_name(rs.getString("pizza_name"));
				product.setIngredient(rs.getString("ingredient"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getPizzaAllergy 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}

	// 사이드 알레르기 getSideAllergy
	public List getSideAllergy() {

		String sql = "SELECT  p.side_name, a.ingredient "+
				"FROM side p, allergy_side a "+
				"WHERE p.side_code=a.side_code(+) "+
				"ORDER BY p.side_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setSide_name(rs.getString("side_name"));
				product.setIngredient(rs.getString("ingredient"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getPizzaAllergy 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}


	// 음료/기타 알레르기 getOtherAllergy
	public List getOtherAllergy() {

		String sql = "SELECT  p.other_name, a.ingredient "+
				"FROM other p, allergy_side a "+
				"WHERE p.side_other=a.other_code(+) "+
				"ORDER BY p.other_code";

		List list = new ArrayList();

		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ProductBean product = new ProductBean();

				product.setOther_name(rs.getString("other_name"));
				product.setIngredient(rs.getString("ingredient"));
				list.add(product);
			}

			return list;
		}catch(Exception e){
			System.out.println("getPizzaAllergy 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	
	
//추가 2
	
	// 주문 하기 setOder
	public boolean setOder(ProductBean product) {

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		String sql1 = "INSERT INTO pizza VALUES (pizza_seq.NEXTVAL,?,?,?)";
		String sql2 = "INSERT INTO nutrient_pizza VALUES "+
				"((select max(pizza_code) from pizza),?,?,?,?,?,?,?,?)";
		String sql3 = "INSERT INTO allergy_pizza VALUES ((select max(pizza_code) from pizza),?)";	

		try {
			// pizza
			pstmt = conn.prepareStatement(sql1);

			pstmt.setInt(1,product.getPizza_price());
			pstmt.setString(2,product.getPizza_name());
			pstmt.setString(3,product.getPizza_image());

			result1 = pstmt.executeUpdate();

			// pizza 영양성분
			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1,product.getStandard());
			pstmt.setInt(2,product.getWeight());
			pstmt.setInt(3,product.getTotal_weight());
			pstmt.setInt(4,product.getCalorie());
			pstmt.setInt(5,product.getProtein());
			pstmt.setInt(6,product.getSaturated_fat());
			pstmt.setInt(7,product.getSalt());
			pstmt.setInt(8,product.getSugars());

			result2 = pstmt.executeUpdate();

			// pizza 알레르기
			pstmt = conn.prepareStatement(sql3);

			pstmt.setString(1,product.getIngredient());

			result3 = pstmt.executeUpdate();

			if(result1==0 || result2==0 || result3==0) {
				return false;
			}
			System.out.println("pizza 등록 성공");
			return true;

		}catch(Exception e){
			System.out.println("addPizza 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}	
	
}
