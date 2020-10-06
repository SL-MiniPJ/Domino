package net.product.action;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PizzaAddAction implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		System.out.println("addAction");
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		ProductDAO productDAO = new ProductDAO();
		ProductBean productVO = new ProductBean();

		// 저장할 경로.
		String realFolder = "";
		// 저장할 폴더 이름.
		String saveFolder = "pizza_image";
		// 파일 사이즈 지정.
		int fileSize = 5*1024*1024;

		// 저장할 경로 = 파일이 저장되는 실제 경로의 saveFolder.
		realFolder = request.getRealPath(saveFolder);

		
		// 상품 등록 : 초기화 false.
		boolean result = false;

		try {
			
			// 파일 업로드 객체 : 초기화 null.
			MultipartRequest multi = null;

			// 파일 업로드 객체 생성 (  접근 경로 , 저장할 경로, 파일 사이즈, 인코딩, 파일명 바꾸는 규칙이 있는 클래스 객체 생성 ).
			multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			int pizza_price = Integer.parseInt(multi.getParameter("pizza_price"));
			int weight = Integer.parseInt(multi.getParameter("weight"));
			int total_weight = Integer.parseInt(multi.getParameter("total_weight"));
			int calorie = Integer.parseInt(multi.getParameter("calorie"));
			int protein = Integer.parseInt(multi.getParameter("protein"));
			int saturated_fat = Integer.parseInt(multi.getParameter("Saturated_fat"));
			int salt = Integer.parseInt(multi.getParameter("salt"));
			int sugars = Integer.parseInt(multi.getParameter("sugars"));

			productVO.setPizza_name(multi.getParameter("pizza_name"));
			productVO.setPizza_price(pizza_price);
			productVO.setStandard(multi.getParameter("standard"));
			productVO.setWeight(weight);
			productVO.setTotal_weight(total_weight);
			productVO.setCalorie(calorie);
			productVO.setProtein(protein);
			productVO.setSaturated_fat(saturated_fat);
			productVO.setSalt(salt);
			productVO.setSugars(sugars);
			productVO.setIngredient(multi.getParameter("ingredient"));
			productVO.setPizza_image(multi.getFilesystemName((String)multi.getFileNames().nextElement()));

			result = productDAO.addPizza(productVO);

			// 등록 실패
			if(result==false){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('상품 등록에 실패하였습니다. 다시 시도해 주시기 바랍니다.');");
				out.println("location.href='./product.pro';");
				out.println("</script>");
				out.close();
				return null;
			}

			List list = new ArrayList();
			list = productDAO.getPizzaList();

			//"list" 이름으로 list 객체 전달
			request.setAttribute("list", list);

			// 전송 방식 = true : sendRedirect 
			forward.setRedirect(true);
			// 접근 경로 지정
			forward.setPath("./PizzaListAction.pro");
			
			System.out.println("addAction>listAcion");
			
			return forward;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
