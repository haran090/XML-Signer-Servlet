

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;

@WebServlet("/upload")
@MultipartConfig
public class XMLSigner extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public XMLSigner() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Start");
		String file_name = request.getPart("file").getSubmittedFileName();
		
		InputStream fileContent = request.getPart("file").getInputStream();
		
		FileOperations.signFile(fileContent, file_name, response);
		
		File file = new File(FileOperations.output_path + file_name);
		if(file.exists()) {
			InputStream output_file_input = new FileInputStream(file);
			response.setContentType("application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader( "Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		
			OutputStream outStream = response.getOutputStream();
	         
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = output_file_input.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	         
	        output_file_input.close();
	        outStream.close();
		}
		else {
			response.getWriter().append("File not found : " + System.getProperty("user.dir") + " : " + file.getAbsolutePath());
	        System.out.println("Fail");
		}
	}

}
