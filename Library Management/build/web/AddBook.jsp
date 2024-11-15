<form action="BookServlet" method="post">
    <input type="hidden" name="action" value="add">
    Book Name: <input type="text" name="bookName" required><br>
    Author Name: <input type="text" name="authorName" required><br>
    Category: <input type="text" name="category" required><br>
    <input type="submit" value="Add Book">
</form>