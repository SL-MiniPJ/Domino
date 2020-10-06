package net.product.db;

public class ProductBean {

	// 피자
	private int pizza_code;
	private int pizza_price;
	private String pizza_name;
	private String pizza_image;

	// 사이드
	private int side_code;
	private int side_price;
	private String side_name;
	private String side_image;

	// 도우
	private int dough_code;
	private int dough_price;
	private String dough_name;
	private String dough_image;

	// 토핑
	private int topping_code;
	private String topping_name;
	private int topping_price;
	private String topping_image;

	// 기타/음료
	private int other_code;
	private int other_price;
	private String other_name;
	private String other_image;

	// 영양성분
	private String standard;
	private int weight;
	private int total_weight;
	private int calorie;
	private int protein;
	private int saturated_fat;
	private int salt;
	private int sugars;

	// 알레르기 유발 성분
	private String ingredient;

//////////////////////////////////////////////////////
	
	public int getPizza_code() {
		return pizza_code;
	}

	public void setPizza_code(int pizza_code) {
		this.pizza_code = pizza_code;
	}

	public int getPizza_price() {
		return pizza_price;
	}

	public void setPizza_price(int pizza_price) {
		this.pizza_price = pizza_price;
	}

	public String getPizza_name() {
		return pizza_name;
	}

	public void setPizza_name(String pizza_name) {
		this.pizza_name = pizza_name;
	}

	public String getPizza_image() {
		return pizza_image;
	}

	public void setPizza_image(String pizza_image) {
		this.pizza_image = pizza_image;
	}

	public int getSide_code() {
		return side_code;
	}

	public void setSide_code(int side_code) {
		this.side_code = side_code;
	}

	public int getSide_price() {
		return side_price;
	}

	public void setSide_price(int side_price) {
		this.side_price = side_price;
	}

	public String getSide_name() {
		return side_name;
	}

	public void setSide_name(String side_name) {
		this.side_name = side_name;
	}

	public String getSide_image() {
		return side_image;
	}

	public void setSide_image(String side_image) {
		this.side_image = side_image;
	}

	public int getDough_code() {
		return dough_code;
	}

	public void setDough_code(int dough_code) {
		this.dough_code = dough_code;
	}

	public int getDough_price() {
		return dough_price;
	}

	public void setDough_price(int dough_price) {
		this.dough_price = dough_price;
	}

	public String getDough_name() {
		return dough_name;
	}

	public void setDough_name(String dough_name) {
		this.dough_name = dough_name;
	}

	public String getDough_image() {
		return dough_image;
	}

	public void setDough_image(String dough_image) {
		this.dough_image = dough_image;
	}

	public int getTopping_code() {
		return topping_code;
	}

	public void setTopping_code(int topping_code) {
		this.topping_code = topping_code;
	}

	public String getTopping_name() {
		return topping_name;
	}

	public void setTopping_name(String topping_name) {
		this.topping_name = topping_name;
	}

	public int getTopping_price() {
		return topping_price;
	}

	public void setTopping_price(int topping_price) {
		this.topping_price = topping_price;
	}

	public String getTopping_image() {
		return topping_image;
	}

	public void setTopping_image(String topping_image) {
		this.topping_image = topping_image;
	}

	public int getOther_code() {
		return other_code;
	}

	public void setOther_code(int other_code) {
		this.other_code = other_code;
	}

	public int getOther_price() {
		return other_price;
	}

	public void setOther_price(int other_price) {
		this.other_price = other_price;
	}

	public String getOther_name() {
		return other_name;
	}

	public void setOther_name(String other_name) {
		this.other_name = other_name;
	}

	public String getOther_image() {
		return other_image;
	}

	public void setOther_image(String other_image) {
		this.other_image = other_image;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(int total_weight) {
		this.total_weight = total_weight;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getSaturated_fat() {
		return saturated_fat;
	}

	public void setSaturated_fat(int saturated_fat) {
		this.saturated_fat = saturated_fat;
	}

	public int getSalt() {
		return salt;
	}

	public void setSalt(int salt) {
		this.salt = salt;
	}

	public int getSugars() {
		return sugars;
	}

	public void setSugars(int sugars) {
		this.sugars = sugars;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
}