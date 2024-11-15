<form action="BookServlet" method="post">
    <input type="hidden" name="action" value="update">
    Book ID: <input type="number" name="bookId" required><br>
    Book Name: <input type="text" name="bookName" required><br>
    Author Name: <input type="text" name="authorName" required><br>
    Category: <input type="text" name="category" required><br>
    <input type="submit" value="Update Book">
</form>