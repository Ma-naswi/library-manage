<%@ page import="java.sql.ResultSet" %>
<%
    ResultSet rs = (ResultSet) request.getAttribute("resultSet");
%>
<h2>Book List</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Book ID</th>
        <th>Book Name</th>
        <th>Author Name</th>
        <th>Category</th>
        <th>Actions</th>
    </tr>
<%
    if (rs != null) {
        while (rs.next()) {
%>
    <tr>
        <td><%= rs.getInt("BookId") %></td>
        <td><%= rs.getString("BookName") %></td>
        <td><%= rs.getString("AuthorName") %></td>
        <td><%= rs.getString("Category") %></td>
        <td>
            <form action="BookServlet" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="bookId" value="<%= rs.getInt("BookId") %>">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
<%
        }
    } else {
%>
    <tr>
        <td colspan="5">No books available.</td>
    </tr>
<%
    }
%>
</table>
