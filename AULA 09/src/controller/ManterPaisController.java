package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import service.ClienteService;
@WebServlet("/ManterCliente.do")
publicclass ManterClienteController extends HttpServlet {
	privatestaticfinallongserialVersionUID = 1L;

	protectedvoid doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protectedvoid doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pAcao = request.getParameter("acao");
		String pId = request.getParameter("id");
		String pPais = request.getParameter("Pais");
		String pArea = request.getParameter("Area");
		String pPopulacao = request.getParameter("Populacao");
		intid = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {
		}
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(pPais);
		cliente.setFone(pArea);
		cliente.setEmail(pPopulacao);
		ClienteService cs = new ClienteService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		if (pAcao.equals("Criar")) {
			cs.criar(cliente);
			ArrayList<Cliente>lista = new ArrayList<>();
			lista.add(cliente);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPais.jsp");
		} elseif (pAcao.equals("Excluir")) {
			cs.excluir(cliente.getId());
			ArrayList<Cliente>lista =
					(ArrayList<Cliente>) session .getAttribute( "lista");
			lista.remove(busca(cliente, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPais.jsp");
		} elseif (pAcao.equals("Alterar")) {
			cs.atualizar(cliente);
			ArrayList<Cliente>lista =
					(ArrayList<Cliente>) session .getAttribute( "lista");
			intpos = busca(cliente, lista);
			lista.remove(pos);
			lista.add(pos, cliente);
			session.setAttribute("lista", lista);
			request.setAttribute("cliente", cliente);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
		} elseif (pAcao.equals("Visualizar")) {
			cliente = cs.carregar(cliente.getId());
			request.setAttribute("cliente", cliente);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
		} elseif (pAcao.equals("Editar")) {
			cliente = cs.carregar(cliente.getId());
			request.setAttribute("cliente", cliente);
			view = request.getRequestDispatcher("AlterarPais.jsp");
		}
		view.forward(request, response);
	}

	publicint busca(Cliente cliente, ArrayList<Cliente>lista) {
		Cliente to;
		for(inti = 0; i<lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == cliente.getId()){
				returni;
			}
		}
		return -1;
	}
}