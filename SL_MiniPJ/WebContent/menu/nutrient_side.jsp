<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.product.db.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	text-align: center;
	width: 900px;
	height: 30px;
}
th {
	width: 100px;
}
</style>
</head>
<body>
<h1>2. 사이드디시</h1>
<table border=1 style=border-collapse:collapse;>
<tr>
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
</tr>

<%
	List list2 = (List) request.getAttribute("list");

	for(int i=0; i < list2.size(); i++){
	
		ProductBean product = (ProductBean)list2.get(i);
%>
			<tr>
				<td><%=product.getSide_name() %></td>
				<td><%=product.getSide_price() %></td>
				<td></td>
				<td></td>
				<td><%=product.getTotal_weight() %></td>
				<td><%=product.getCalorie() %></td>
				<td><%=product.getProtein() %></td>
				<td><%=product.getSaturated_fat() %></td>
				<td><%=product.getSalt() %></td>
				<td><%=product.getSugars() %></td>
			</tr>
<%
	}
%>
</table>
</body>
</html>