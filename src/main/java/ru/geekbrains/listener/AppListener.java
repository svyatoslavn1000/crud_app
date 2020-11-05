package ru.geekbrains.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String jdbcConnectionString = ctx.getInitParameter("jdbcConnectionString");
        String dbUsername = ctx.getInitParameter("dbUsername");
        String dbPassword = ctx.getInitParameter("dbPassword");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, dbUsername, dbPassword);
            ProductRepository productRepository = new ProductRepository(conn);
            ctx.setAttribute("connection", conn);
            ctx.setAttribute("productRepository", productRepository);

            if (productRepository.findAll().size() == 0) {
                productRepository.insert(new Product(-1L, "Product1", "Desc1", new BigDecimal(10)));
                productRepository.insert(new Product(-1L, "Product2", "Desc2", new BigDecimal(102)));
                productRepository.insert(new Product(-1L, "Product3", "Desc3", new BigDecimal(1030)));
                productRepository.insert(new Product(-1L, "Product4", "Desc4", new BigDecimal(140)));
            }
        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }
}
