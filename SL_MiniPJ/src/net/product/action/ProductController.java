package net.product.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// �� �������� �ּ�
		String RequestURI = request.getRequestURI();

		// ���̳��� �� ������Ʈ �� ������ �ּ�
		String contaxtPath = request.getContextPath();

		// �� �������� �ּҿ��� ���̳��� �� ������Ʈ �� ������ �ּҸ� �߶󳻰� ���� ���� command�� ����.
		String command = RequestURI.substring(contaxtPath.length());

		// ���� ��� �� ���� ����� ������ �뵵
		ActionForward forward  = null;
		Action action = null;


		if(command.equals("/product.pro")){ // ����.. ���⼭ �����ϸ� ��ȸ �Ұ�
			// ���� ������
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/product/product_List.jsp");

		}else if(command.equals("/PizzaAddAction.pro")) {
			// ���� �߰�
			action = new PizzaAddAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/SideAddAction.pro")) {
			// ���̵� �߰�
			action = new SideAddAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/DoughAddAction.pro")) {
			// ���� �߰�
			action = new DoughAddAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/ToppingAddAction.pro")) {
			// ���� �߰�
			action = new ToppingAddAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/OtherAddAction.pro")) {
			// ����/��Ÿ �߰�
			action = new OtherAddAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}			


		}else if(command.equals("/PizzaListAction.pro")) {
			// ���� ���
			action = new PizzaListAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/SideListAction.pro")) {
			// ���̵� ���
			action = new SideListAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/DoughListAction.pro")) {
			// ���� ���
			action = new DoughListAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/ToppingListAction.pro")) {
			// ���� ���
			action = new ToppingListAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/OtherListAction.pro")) {
			// ����/��Ÿ ���
			action = new OtherListAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/PizzaDeleteAction.pro")) {
			// ���� ����
			action = new PizzaDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/SideDeleteAction.pro")) {
			// ���̵� ����
			action = new SideDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/DoughDeleteAction.pro")) {
			// ���� ����
			action = new DoughDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/ToppingDeleteAction.pro")) {
			// ���� ����
			action = new ToppingDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/OtherDeleteAction.pro")) {
			// ����/��Ÿ ����
			action = new OtherDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

			// �߰�			
		}else if(command.equals("/Pizza_nutrient.pro")) {
			// ���� ���缺�� ���̺�
			action = new PizzaNutrient();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/Side_nutrient.pro")) {
			// ���̵� ���缺�� ���̺�
			action = new SideNutrient();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/Pizza_allergy.pro")) {
			// ���� �˷����� ���̺�
			action = new Pizza_allergy();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/Side_allergy.pro")) {
			// ���̵� �˷����� ���̺�
			action = new Side_allergy();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/Pizza_allergy.pro")) {
			// ���� �˷����� ���̺�
			action = new Other_allergy();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(command.equals("/OderOk.pro")) {
			// �ֹ� �ϱ�
			action = new OderOk();
			System.out.println("�ֹ��ϱ� controller");
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		

			if(forward.isRedirect()){
				// isRedirect = false : Forward
				response.sendRedirect(forward.getPath());
			}else{
				// isRedirect = true : sendRedirect
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response);
		}
	}
