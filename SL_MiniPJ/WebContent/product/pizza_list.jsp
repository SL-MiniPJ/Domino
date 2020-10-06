<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.product.db.*" %>
<%@ include file="./product_List.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pizza_list</title>
</head>
<body>
<table border=1 style=border-collapse:collapse;>
<tr>
	<th><b>code</b></th>
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
	<th>이미지 미리보기</th>
	<th>삭제</th>
</tr>

<%
	List list = (List) request.getAttribute("list");

	for(int i=0; i < list.size(); i++){
	
		ProductBean product = (ProductBean)list.get(i);
%>
			<tr>
				<td><%=product.getPizza_code() %></td>
				<td><%=product.getPizza_name() %></td>
				<td><%=product.getPizza_price() %></td>
				<td><%=product.getStandard() %></td>
				<td><%=product.getWeight() %></td>
				<td><%=product.getTotal_weight() %></td>
				<td><%=product.getCalorie() %></td>
				<td><%=product.getProtein() %></td>
				<td><%=product.getSaturated_fat() %></td>
				<td><%=product.getSalt() %></td>
				<td><%=product.getSugars() %></td>
				<td><a href="./pizza_image/<%=product.getPizza_image() %>"><%=product.getPizza_image() %></a></td>
				<td><a href="./PizzaDeleteAction.pro?pizza_code=<%=product.getPizza_code() %>">피자 삭제</a></td>
			</tr>
<%
	}
%>
</table>
</body>
</html>