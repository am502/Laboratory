package Service;

import DAO.Model.Book;
import DAO.BooksDaoJdbcTemplateBasedImpl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookService {
    BooksDaoJdbcTemplateBasedImpl booksService = new BooksDaoJdbcTemplateBasedImpl();

    public void saveBook(Book book) throws SQLException {
        booksService.addBook(book);
    }

    public void deleteBook(int id) throws SQLException {
        booksService.deleteBook(id);
    }

    public void updateBook(int id, int price) throws SQLException {
        booksService.updateBook(id, price);
    }

    public Book getBook(int id) throws SQLException {
        return booksService.getBook(id);
    }

    public List<Book> showBooks() throws SQLException {
        return booksService.showBooks();
    }

    public void addAll(){
        booksService.addAll();
    }
}