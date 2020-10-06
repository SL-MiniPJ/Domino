package net.product.action;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OderOk implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		System.out.println("�ֹ� �Ϸ� ��");
		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean ��ü ����
		ActionForward forward = new ActionForward();
		ProductDAO productDAO = new ProductDAO();
		ProductBean productVO = new ProductBean();

		boolean result = false;
		
		try {

			int pizza_code = Integer.parseInt(request.getParameter("pizza_code"));
			
			productVO.setPizza_code(pizza_code);
			
			result = productDAO.setOder(productVO);
			
			
			
			// ���� ��� = false : Forward 
			forward.setRedirect(false);
			// ���� ��� ����
			forward.setPath("./menu/oderOk.jsp");

			System.out.println("�ֹ� ����");

			return forward;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}