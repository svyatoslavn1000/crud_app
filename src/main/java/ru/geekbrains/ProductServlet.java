package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "ProductServlet", urlPatterns = {"/", ""})
public class ProductServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");

        if (productRepository == null) {
            throw new ServletException("ProductRepository not created");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getServletPath().equals("/")) {
                req.setAttribute("products", productRepository.findAll());
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else if (req.getServletPath().equals("/create")) {
                showCreateProductPage(req, resp);
            } else if (req.getServletPath().equals("/edit")) {
                showEditProductPage(req, resp);
            } else if (req.getServletPath().equals("/delete")) {
                deleteProduct(req, resp);
            }
        } catch (SQLException e) {
            logger.error("", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/update")) {
            updateProduct(req, resp);
        } else if (req.getServletPath().equals("/create")) {
            createProduct(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            productRepository.insert(new Product(
                    -1L,
                    req.getParameter("name"),
                    req.getParameter("description"),
                    new BigDecimal(req.getParameter("price"))
            ));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            productRepository.update(new Product(
                    Long.parseLong(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("description"),
                    new BigDecimal(req.getParameter("price"))
            ));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            productRepository.delete(Long.parseLong(req.getParameter("id")));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void showEditProductPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Product product;
        try {
            product = productRepository.findById(id);
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        req.setAttribute("product", product);
        req.setAttribute("action", "update");
        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
    }

    private void showCreateProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", new Product());
        req.setAttribute("action", "create");
        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
    }
}
