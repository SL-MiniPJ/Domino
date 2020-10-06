<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.product.db.*" %>
<%@ include file="./product_List.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dough_list</title>
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
				<td><%=product.getDough_code() %></td>
				<td><%=product.getDough_name() %></td>
				<td><%=product.getDough_price() %></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="./pizza_image/<%=product.getDough_image() %>"><%=product.getDough_image() %></a></td>
				<td><a href="./DoughDeleteAction.pro?dough_code=<%=product.getDough_code() %>">도우 삭제</a></td>
			</tr>
<%
	}
%>
</table>
</body>
</html>