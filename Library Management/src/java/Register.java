import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userName = request.getParameter("UserName");
        String password = request.getParameter("Password");
        String confirmPassword = request.getParameter("ConfirmPassword");
        String email = request.getParameter("UserEmail");

        PrintWriter out = response.getWriter();

        if (!password.equals(confirmPassword)) {
            out.print("<h3>Passwords do not match!</h3>");
            RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
            rd.include(request, response);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, email);

            int count = ps.executeUpdate();

            if (count > 0) {
                out.print("<h3>Registration Successful!</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.include(request, response);
            } else {
                out.print("<h3>Registration Failed!</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
