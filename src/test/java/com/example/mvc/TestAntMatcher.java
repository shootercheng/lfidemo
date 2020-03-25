package com.example.mvc;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * @author James
 */
public class TestAntMatcher {

    @Test
    public void testAntMatcher() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String lookUpPath = "/resources/framework/bootstrap-3.0.0/css/bootstrap.min.css";
        boolean matches = antPathMatcher.match("/**/*.css", lookUpPath);
        System.out.println(matches);
        boolean matches2 = antPathMatcher.match("/**/*.jpg", "/resources/framework/1.jpg");
        System.out.println(matches2);
//        "/**/*.jpg",
//        "/**/*.png",
//        "/**/*.html",
//        "/**/*.js",
//        "/**/*.css",
//        "/**/*.jpeg",
//        "/**/*.doc",
//        "/**/*.pdf",
//        "/**/*.ignore"
    }
}
