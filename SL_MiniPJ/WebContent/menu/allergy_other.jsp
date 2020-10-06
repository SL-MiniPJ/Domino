<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.product.db.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알레르기 유발 성분</title>
<style>
table {
	text-align: center;
	width: 900px;
	height: 300px;
}
th {
	width: 100px;
}
</style>
</head>
<body>
<h1>3. 음료/기타</h1>
<table border=1 style=border-collapse:collapse;>
<tr>
	<th>음료/기타</th>
	<th>알레르기 유발 성분</th>
</tr>
<%
	List list = (List) request.getAttribute("list");

	for(int i=0; i < list.size(); i++){
		
		ProductBean product = (ProductBean)list.get(i);
%>
			<tr>
				<td><%=product.getOther_name() %></td>
				<td><%=product.getIngredient() %></td>
			</tr>
<%
	}
%>

</table>
</body>
</html>