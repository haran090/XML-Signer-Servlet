

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password = request.getParameter("pass");
		if(password != null) {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			String encoded = new String(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
			if(encoded != null && encoded.equals("56863a9219d49127d3329baf2fa5b533b954faac")){
				request.getRequestDispatcher("/file_upload.jsp").forward(request, response);
			}
			else {
				response.getWriter().append("<script>window.close()</script>").append(request.getContextPath());	
			}
		}
		else {
			response.getWriter().append("<script>window.close()</script>").append(request.getContextPath());	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
