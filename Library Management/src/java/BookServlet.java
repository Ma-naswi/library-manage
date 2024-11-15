import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    // Database connection details
    private String dbUrl = "jdbc:mysql://localhost:3306/library";
    private String dbUser = "root";
    private String dbPwd = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter pw = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

            if ("add".equals(action)) {
                String bookName = request.getParameter("bookName");
                String authorName = request.getParameter("authorName");
                String category = request.getParameter("category");

                String query = "INSERT INTO books (BookName, AuthorName, Category) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, bookName);
                ps.setString(2, authorName);
                ps.setString(3, category);
                ps.executeUpdate();

                pw.write("<h3>Book added successfully!</h3>");

            } else if ("update".equals(action)) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                String bookName = request.getParameter("bookName");
                String authorName = request.getParameter("authorName");
                String category = request.getParameter("category");

                String query = "UPDATE books SET BookName=?, AuthorName=?, Category=? WHERE BookId=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, bookName);
                ps.setString(2, authorName);
                ps.setString(3, category);
                ps.setInt(4, bookId);
                ps.executeUpdate();

                pw.write("<h3>Book updated successfully!</h3>");

            } else if ("delete".equals(action)) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));

                String query = "DELETE FROM books WHERE BookId=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, bookId);
                ps.executeUpdate();

                pw.write("Book deleted successfully!</");

            } else if ("display".equals(action)) {
                String query = "SELECT * FROM books";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                request.setAttribute("resultSet", rs);
                RequestDispatcher rd = request.getRequestDispatcher("DisplayBooks.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}