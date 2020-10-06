package net.product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductDAO;

public class DoughDeleteAction implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		ProductDAO productDAO = new ProductDAO();

		try {

			boolean result = false;

			int code = Integer.parseInt(request.getParameter("dough_code"));

			result = productDAO.DeleteDough(code);

			if(result==false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('상품 삭제에 실패하였습니다. 다시 시도해 주시기 바랍니다.');");
				out.println("location.href='./DoughListAction.pro';");
				out.println("</script>");
				out.close();
				return null;
			}

			// 전송 방식 true : sendRedirect 방식
			forward.setRedirect(true);
			// 접근 경로 지정
			forward.setPath("./DoughListAction.pro");

			return forward;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
