<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.product.db.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
table {
	text-align: center;
	height: 30px;
}
th {
	width: 120px;
	height: 30px;
}
</style>
<script type="text/javascript">
	function addPizza() {
		var f = document.add;
		
		f.action = "../PizzaAddAction.pro";
		f.submit();
		alert("Pizza 등록");
	}
	
	function addSide() {
		var f = document.add;
		
		f.action = "../SideAddAction.pro";
		f.submit();
		alert("Side 등록");
	}
	
	function addDough() {
		var f = document.add;
		
		f.action = "../DoughAddAction.pro";
		f.submit();
		alert("Dough 등록");
	}
	
	function addTopping() {
		var f = document.add;
		
		f.action = "../ToppingAddAction.pro";
		f.submit();
		alert("Topping 등록");
	}
	
	function addOther() {
		var f = document.add;
		
		f.action = "../OtherAddAction.pro";
		f.submit();
		alert("Other 등록");
	}
	
	
	function textBox(pro) {
		var box = "";
		inputBox.innerHTML = "";

		var buttonn = "";
		buttons.innerHTML = "";

		if (pro == "none") {
			box += "<td colspan='13'>관리할 메뉴를 선택하세요</td>";
			buttonn += "<input type=button value='none'>";
		}

		if (pro == "pizza") {
			box += "<a href='../PizzaListAction.pro'>피자 조회</a>";
			box += "<td><input type=text name='pizza_name' size='10'></td>";
			box += "<td><input type=text name='pizza_price' size='10'></td>";
			box += "<td><input type=text name='standard' size='10'></td>";
			box += "<td><input type=text name='weight' size='10'></td>";
			box += "<td><input type=text name='total_weight' size='10'></td>";
			box += "<td><input type=text name='calorie' size='10'></td>";
			box += "<td><input type=text name='protein' size='10'></td>";
			box += "<td><input type=text name='Saturated_fat' size='10'></td>";
			box += "<td><input type=text name='salt' size='10'></td>";
			box += "<td><input type=text name='sugars' size='10'></td>";
			box += "<td><input type=text name='ingredient' size='10'></td>";
			box += "<td><input type=file name='pizza_image' size='10'></td>";

			buttonn += "<input type='submit' value='피자 등록' onclick='addPizza()'>";
		}

		if (pro == "side") {

			box += "<a href='../SideListAction.pro'>사이드 조회</a>";
			box += "<td><input type=text name=side_name size='10'></td>";
			box += "<td><input type=text name=side_price size='10'></td>";
			box += "<td><input type=text name=standard size='10' disabled></td>";
			box += "<td><input type=text name=weight size='10' disabled></td>";
			box += "<td><input type=text name=total_weight size='10' ></td>";
			box += "<td><input type=text name=calorie size='10'></td>";
			box += "<td><input type=text name=protein size='10'></td>";
			box += "<td><input type=text name=Saturated_fat size='10'></td>";
			box += "<td><input type=text name=salt size='10'></td>";
			box += "<td><input type=text name=sugars size='10'></td>";
			box += "<td><input type=text name=ingredient size='10'></td>";
			box += "<td><input type=file name=side_image size='10'></td>";

			buttonn += "<input type='submit' value='사이드 등록' onclick='addSide()'>";
		}

		if (pro == "dough") {

			box += "<a href='../DoughListAction.pro'>도우 조회</a>";
			box += "<td><input type=text name=dough_name size='10'></td>";
			box += "<td><input type=text name=dough_price size='10'></td>";
			box += "<td><input type=text name=standard size='10' disabled></td>";
			box += "<td><input type=text name=weight size='10' disabled></td>";
			box += "<td><input type=text name=total_weight size='10' disabled></td>";
			box += "<td><input type=text name=calorie size='10' disabled></td>";
			box += "<td><input type=text name=protein size='10' disabled></td>";
			box += "<td><input type=text name=Saturated_fat size='10' disabled></td>";
			box += "<td><input type=text name=salt size='10' disabled></td>";
			box += "<td><input type=text name=sugars size='10' disabled></td>";
			box += "<td><input type=text name=ingredient size='10' disabled></td>";
			box += "<td><input type=file name=dough_image size='10'></td>";

			buttonn += "<input type='submit' value='도우 등록' onclick='addDough()'>";
		}

		if (pro == "topping") {

			box += "<a href='../ToppingListAction.pro'>토핑 조회</a>";
			box += "<td><input type=text name=topping_name size='10'></td>";
			box += "<td><input type=text name=topping_price size='10'></td>";
			box += "<td><input type=text name=standard size='10' disabled></td>";
			box += "<td><input type=text name=weight size='10' disabled></td>";
			box += "<td><input type=text name=total_weight size='10' disabled></td>";
			box += "<td><input type=text name=calorie size='10' disabled></td>";
			box += "<td><input type=text name=protein size='10' disabled></td>";
			box += "<td><input type=text name=Saturated_fat size='10' disabled></td>";
			box += "<td><input type=text name=salt size='10' disabled></td>";
			box += "<td><input type=text name=sugars size='10' disabled></td>";
			box += "<td><input type=text name=ingredient size='10' disabled></td>";
			box += "<td><input type=file name=topping_image size='10'></td>";

			buttonn += "<input type='submit' value='토핑 등록' onclick='addTopping()'>";
		}

		if (pro == "other") {

			box += "<a href='../OtherListAction.pro'>음료/기타 조회</a>";
			box += "<td><input type=text name=other_name size='10'></td>";
			box += "<td><input type=text name=other_price size='10'></td>";
			box += "<td><input type=text name=standard size='10' disabled></td>";
			box += "<td><input type=text name=weight size='10' disabled></td>";
			box += "<td><input type=text name=total_weight size='10' disabled></td>";
			box += "<td><input type=text name=calorie size='10'></td>";
			box += "<td><input type=text name=protein size='10'></td>";
			box += "<td><input type=text name=Saturated_fat size='10'></td>";
			box += "<td><input type=text name=salt size='10'></td>";
			box += "<td><input type=text name=sugars size='10'></td>";
			box += "<td><input type=text name=ingredient size='10'></td>";
			box += "<td><input type=file name=other_image size='10'></td>";

			buttonn += "<input type='submit' value='음료/기타 등록' onclick='addOther()'>";
		}

		inputBox.innerHTML = box;
		buttons.innerHTML = buttonn;
	}
</script>
<title>product list</title>
</head>
<body>
<form name="add" method="post" enctype="multipart/form-data">
	<table border=1 style="border-collapse: collapse; width: 2000px;">
		<tr>
			<th><b><select name="products"
					onChange="textBox(this.value);">
						<option value="non">==선택==</option>
						<option value="pizza">피자</option>
						<option value="side">사이드</option>
						<option value="dough">도우</option>
						<option value="topping">토핑</option>
						<option value="other">음료/기타</option>
				</select></b></th>
			<th>이름</th>
			<th>가격</th>
			<th>1회분 기준</th>
			<th>1회분 중량</th>
			<th>총 중량</th>
			<th>열량</th>
			<th>단백질</th>
			<th>포화 지방</th>
			<th>나트륨</th>
			<th>당류</th>
			<td><b>알레르기 유발 성분</b></td>
			<td><b>이미지 등록</b></td>
			<th rowspan="2" id="buttons"><input type=button value='none'></th>
		</tr>
		<tr height="30" id="inputBox">
			<td colspan="13">관리할 메뉴를 선택하세요</td>
		</tr>
	</table>
	<hr>
</form>
</body>
</html>