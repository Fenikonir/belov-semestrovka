package com.example.demo.servlets.requestHandler;

import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.Article;
import com.example.demo.database.entity.User;
import com.example.demo.database.entity.UserFiles;
import com.example.demo.database.repository.PgRepository;
import com.example.demo.servlets.Names;
import com.example.demo.singleton.FreemarkerConfigSingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

@WebServlet(Names.POST_LINK)
@MultipartConfig
public class PostHandler extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        String poster = request.getParameter("message");
        Article article = new Article(0, PgRepository.getUserByEmail(userAuthed).getId(), "био", poster);
        System.out.println(article);
        PgRepository.saveUserBio(article);
    }

    public void destroy() {
    }
}