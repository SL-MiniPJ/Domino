package net.product.action;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OderOk implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		System.out.println("주문 하러 옴");
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		ProductDAO productDAO = new ProductDAO();
		ProductBean productVO = new ProductBean();

		boolean result = false;
		
		try {

			int pizza_code = Integer.parseInt(request.getParameter("pizza_code"));
			
			productVO.setPizza_code(pizza_code);
			
			result = productDAO.setOder(productVO);
			
			
			
			// 전송 방식 = false : Forward 
			forward.setRedirect(false);
			// 접근 경로 지정
			forward.setPath("./menu/oderOk.jsp");

			System.out.println("주문 성공");

			return forward;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}