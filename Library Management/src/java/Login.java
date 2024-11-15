import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        // Retrieving the login details (username and password) from the form
        String username = request.getParameter("UserName");
        String password = request.getParameter("Password");
        
        PrintWriter pw = response.getWriter();
        
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/library"; // Database name is 'library'
        String dbUser = "root"; // Your database username
        String dbPwd = "";      // Your database password

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Establish the database connection
            Connection con = DriverManager.getConnection(url, dbUser, dbPwd);
            
            // Step 3: Prepare the SQL query to check the user's credentials
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            // Step 4: Set the parameters for the query (username and password)
            ps.setString(1, username);
            ps.setString(2, password);
            
            // Step 5: Execute the query
            ResultSet rs = ps.executeQuery();
            
            // Step 6: Check if user exists
            if (rs.next()) {
                // User found, login successful
                String userEmail = rs.getString("email");
                pw.write("<h3>Welcome, " + username + "</h3>");
                
                // Redirect to the homepage
                RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
                request.setAttribute("UserName", username);
                request.setAttribute("Email", userEmail);
                rd.forward(request, response);
            } else {
                // Invalid username or password
                pw.write("<h3>Invalid credentials. Please try again.</h3>");
                
                // Redirect to login page
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
