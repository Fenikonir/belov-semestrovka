package com.belov.semestrovka.servlets.requestHandler;

import com.belov.semestrovka.database.repository.PgRepository;
import com.belov.semestrovka.singleton.FreemarkerConfigSingleton;
import com.belov.semestrovka.database.entity.User;
import com.belov.semestrovka.database.entity.UserFiles;
import com.belov.semestrovka.servlets.Names;
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
        String userAuthed = (String) request.getSession().getAttribute(Names.SESSION_AUTH_ATTRIBUTE);
        User user = PgRepository.getUserByEmail(userAuthed);
        // Create the directory if it doesn't exist
        Path directoryPath = Path.of(uploadDir);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        HttpSession session = request.getSession();
//        String oldFileName = PgRepository.getUserAvatar(user.getId()).getFile_path();
//        if (oldFileName != null) {
//            Path oldFilePath = directoryPath.resolve(oldFileName);
//            Files.deleteIfExists(oldFilePath);
//        }

        // Save the file to the directory
        try (InputStream fileContent = filePart.getInputStream()) {
            System.out.println("Начало записи файла " + fileName);
            Path filePath = directoryPath.resolve(fileName);
            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Файл не записан");
        }

        session.setAttribute("filename", fileName);

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