package net.product.action;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SideNutrient implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		System.out.println("listAction");
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		ProductDAO productDAO = new ProductDAO();
		ProductBean productVO = new ProductBean();

		List list = new ArrayList();
		list = productDAO.getSideList();
		
		 //"list" 이름으로 list 객체 전달
		request.setAttribute("list", list);


		// 전송 방식 = false : Forward 
		forward.setRedirect(false);
		// 접근 경로 지정
		forward.setPath("./menu/nutrient_side.jsp");

		return forward;
	}
}
