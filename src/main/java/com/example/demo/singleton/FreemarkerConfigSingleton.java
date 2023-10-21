package com.example.demo.singleton;

import freemarker.template.Configuration;
import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.IOException;


public class FreemarkerConfigSingleton {
    private static Configuration cfg;
    private static ServletContext sc;
    public static void setServletContext(ServletContext servletContext) {
        sc = servletContext;
    }
    public static Configuration getCfg() throws IOException {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\belov\\IdeaProjects\\demo\\src\\main\\webapp\\templates"));
        }
        return cfg;
    }
}
