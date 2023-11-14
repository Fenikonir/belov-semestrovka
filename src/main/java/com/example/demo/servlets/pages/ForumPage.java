package com.example.demo.servlets.pages;

import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.Article;
import com.example.demo.database.entity.User;
import com.example.demo.database.entity.UserFiles;
import com.example.demo.database.repository.PgRepository;
import com.example.demo.servlets.Button;
import com.example.demo.servlets.Names;
import com.example.demo.singleton.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(Names.FORUM_LINK)
public class ForumPage extends HttpServlet {
    private static final int PAGE_SIZE = 10; // Number of articles per page

    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        try {
            Template template = FreemarkerConfigSingleton.getCfg().getTemplate(Names.FORUM_FILE);
            Map<String, Object> dataModel = new HashMap<>();

            // Get the page number from the request parameter
            int pageNumber = 1;
            String pageNumberParam = request.getParameter("page");
            if (pageNumberParam != null) {
                pageNumber = Integer.parseInt(pageNumberParam);
            }

            // Get the articles for the current page
            List<Article> articleList = PgRepository.getPostsByPage(pageNumber, PAGE_SIZE);
            Set<Integer> userIds = new HashSet<>();
            for (Article article : articleList) {
                userIds.add(article.getAuthorId());
            }
            Map<Integer, String> userAvatars = new HashMap<>();
            for (Integer id : userIds) {
                UserFiles avatar = PgRepository.getUserAvatar(id);
                String avatarUrl = (avatar.getFile_path() == null) ? "https://bootdey.com/img/Content/avatar/avatar3.png" : "../resources/images/" + avatar.getFile_path();
                userAvatars.put(id, avatarUrl);
            }
            for (Article article : articleList) {
                article.setPhoto(userAvatars.get(article.getAuthorId()));
            }
            // Get the total number of articles
            int totalArticles = PgRepository.getTotalPosts();

            // Calculate the total number of pages
            int totalPages = (int) Math.ceil((double) totalArticles / PAGE_SIZE);

            // Set the pagination data in the data model
            System.out.println(String.format("ForumPage: totalPages %s, pageNumber: %s", totalPages, pageNumber));
            dataModel.put("pageNumber", pageNumber);
            dataModel.put("totalPages", totalPages);

            // Add other data to the data model
            dataModel.put("host", Names.HOST_LINK);
            dataModel.put("posts", articleList);
            dataModel.put("buttons", (userAuthed != null) ? Button.getAuthButton() : Button.getNonAuthButton());

            template.process(dataModel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            response.getWriter().println("Error processing Freemarker template.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            String userEmail = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
            User user = PgRepository.getUserByEmail(userEmail);
            String message = request.getParameter("message");
            Article article = new Article();
            article.setValue(message);
            article.setAuthorId(user.getId());
            article.setType("пост");
            System.out.println("Сообщение отправлено: " + article.getValue());
            DAOFabric.getArticleDAO().save(article);
            response.getWriter().println("Data received successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error processing the request.");
        }
    }

    public void destroy() {
    }
}