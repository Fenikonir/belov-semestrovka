package com.belov.semestrovka.servlets.requestHandler;

import com.belov.semestrovka.database.repository.PgRepository;
import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.database.entity.Article;
import com.belov.semestrovka.servlets.Names;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

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