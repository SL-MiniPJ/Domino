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

public class OtherAddAction implements Action{

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
		String saveFolder = "other_image";
		// 파일 사이즈 지정.
		int fileSize = 5*1024*1024;

		// 저장할 경로 = 파일이 저장되는 실제 경로의 saveFolder.
		realFolder = request.getRealPath(saveFolder);
		
		boolean result = false;

		try {
			
			// 파일 업로드 객체 : 초기화 null.
			MultipartRequest multi = null;

			// 파일 업로드 객체 생성 (  접근 경로 , 저장할 경로, 파일 사이즈, 인코딩, 파일명 바꾸는 규칙이 있는 클래스 객체 생성 ).
			multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			int other_price = Integer.parseInt(multi.getParameter("other_price"));
			int calorie = Integer.parseInt(multi.getParameter("calorie"));
			int protein = Integer.parseInt(multi.getParameter("protein"));
			int saturated_fat = Integer.parseInt(multi.getParameter("Saturated_fat"));
			int salt = Integer.parseInt(multi.getParameter("salt"));
			int sugars = Integer.parseInt(multi.getParameter("sugars"));

			productVO.setOther_name(multi.getParameter("other_name"));
			productVO.setOther_price(other_price);
			productVO.setCalorie(calorie);
			productVO.setProtein(protein);
			productVO.setSaturated_fat(saturated_fat);
			productVO.setSalt(salt);
			productVO.setSugars(sugars);
			productVO.setIngredient(multi.getParameter("ingredient"));
			productVO.setOther_image(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			
			result = productDAO.addOther(productVO);

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
			list = productDAO.getOtherList();

			//"list" 이름으로 list 객체 전달
			request.setAttribute("list", list);

			// 전송 방식 = true : sendRedirect 
			forward.setRedirect(true);
			// 접근 경로 지정
			forward.setPath("./OtherListAction.pro");

			System.out.println("addAction>listAcion");

			return forward;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}