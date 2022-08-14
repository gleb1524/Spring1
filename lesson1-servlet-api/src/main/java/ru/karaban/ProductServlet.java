package ru.karaban;

import ru.karaban.persist.Product;
import ru.karaban.persist.ProductRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>title</th>");
        writer.println("<th>cost</th>");
        writer.println("</tr>");


        for (Product product : productRepository.findAll()) {

            if (null == request.getPathInfo()) {
                writer.println("<tr>");
                writer.println("<td><a href='" + getServletContext().getContextPath() + "/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
                writer.println("<td>" + product.getTitle() + "</td>");
                writer.println("<td>" + product.getCost() + "</td>");
                writer.println("</tr>");
            } else {
                String[] token = request.getPathInfo().split("/");
                long id;
                try {
                    id = Long.parseLong(token[1]);
                } catch (Exception e) {
                    id = -1;
                }
                if ("/".equals(request.getPathInfo()) || productRepository.findById(id) == null) {
                    String path = "/404";
                    ServletContext servletContext = getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
                    requestDispatcher.forward(request, response);
                }
                if (request.getPathInfo().equals("/" + product.getId())) {
                    writer.println("<tr>");
                    writer.println("<td><a href='" + getServletContext().getContextPath() + "/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
                    writer.println("<td>" + product.getTitle() + "</td>");
                    writer.println("<td>" + product.getCost() + "</td>");
                    writer.println("</tr>");
                }
            }
        }

        writer.println("</table>");
    }
}
