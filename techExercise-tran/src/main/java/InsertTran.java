
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertTran")
public class InsertTran extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertTran() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   String firstName = request.getParameter("firstName");
	   String lastName = request.getParameter("lastName");
	   String phone = request.getParameter("phone");
	   String email = request.getParameter("email");
	   
      Connection connection = null;
      String insertSql = " INSERT INTO contactTableTran (id, FIRST_NAME, LAST_NAME, PHONE, EMAIL) values (default, ?, ?, ?, ?)";

      try {
         DBConnectionTran.getDBConnection();
         connection = DBConnectionTran.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, firstName);
         preparedStmt.setString(2, lastName);
         preparedStmt.setString(3, phone);
         preparedStmt.setString(4, email);
        
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Vehicle Added!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + firstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //
            "  <li><b>phone</b>: " + phone + "\n" + //
            "  <li><b>email</b>: " + email + "\n" + //

            "</ul>\n");

      out.println("<a href=/techExercise-Tran/search_tran.html>Search Contact</a> <br>");
      out.println("<a href=/techExercise-Tran/insert_tran.html>Insert Contact</a> <br>");
      out.println("<a href=/techExercise-Tran/delete_tran.html>Remove Contact</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
