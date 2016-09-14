package Controllers;
import DAO.Model.Book;
import Service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    BookService bookService = new BookService();

    @RequestMapping(value = "/")
    String showHomePage() throws SQLException {
        bookService.addAll();
        return "home";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(int id, String name, int price) throws SQLException {
        Book book = new Book(id, name, price);
        bookService.saveBook(book);
        return "success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getBookForm() {
        return new ModelAndView("creating");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteeBook(int id) throws SQLException {
        bookService.deleteBook(id);
        return "success";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView getBookForm1() {
        return new ModelAndView("deleting");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String deleteeBook(int id, int price) throws SQLException {
        bookService.updateBook(id, price);
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView getBookForm2() {
        return new ModelAndView("updating");
    }

    @RequestMapping(value = "/getbyid")
    @ResponseBody
    public Book getBook(@RequestParam(value = "id") int id) throws SQLException {
        return bookService.getBook(id);
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public List<Book> showBooks() throws SQLException {
        return bookService.showBooks();
    }
}
