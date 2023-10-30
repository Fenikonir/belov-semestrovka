package com.example.demo.servlets;

import com.example.demo.database.dao.DAOFabric;
import com.example.demo.database.entity.User;
import com.example.demo.database.entity.UserFiles;
import com.example.demo.database.repository.PgRepository;
import com.example.demo.singleton.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Names.UPLOAD_AVATAR_LINK)
@MultipartConfig
public class UploadFiles extends HttpServlet {
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part filePart = request.getPart("avatar");
        String fileName = filePart.getSubmittedFileName();
        String uploadDir = Names.IMAGE_DIST; // Change this to your desired directory

        // Create the directory if it doesn't exist
        Path directoryPath = Path.of(uploadDir);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Save the file to the directory
        try (InputStream fileContent = filePart.getInputStream()) {
            Path filePath = directoryPath.resolve(fileName);
            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        HttpSession session = request.getSession();
        session.setAttribute("filename", fileName);
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        User user = PgRepository.getUserByEmail(userAuthed);
        try {
            setImageToBD(fileName, userAuthed, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Upload file: file sc uploaded");
        response.getWriter().println("File uploaded successfully.");
    }

    public static void setImageToBD(String filePath, String userName, int userId) throws SQLException {
        PgRepository.saveUserAvatar(new UserFiles(0, userId, "avatar", filePath));
    }

    public void destroy() {
    }
}