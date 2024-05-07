package com.oasm.utils;

/*
 * 2024/03/19
 * 조주영
 * 자바스크립트 도우미 클래스
 */


import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class JSFunction {

    static String contentType = "text/html; charset=utf-8";

    // 알림창만
    public static void alert(HttpServletResponse resp, String msg){

        try {
            resp.setContentType(contentType);
            PrintWriter writer = resp.getWriter();

            String script = ""
                    + "<script>"
                    + " alert ('" + msg + "');"
                    + "</script>";
            writer.print(script);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 알림창 띄운 후 새로고침
    public static void alertReload(HttpServletResponse resp, String msg){

        try {
            resp.setContentType(contentType);
            PrintWriter writer = resp.getWriter();

            String script = ""
                    + "<script>"
                    + " alert ('" + msg + "');"
                    + " location.reload();"
                    + "</script>";
            writer.print(script);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 알림창 띄운 후 url로 이동
    public static void alertLocation(HttpServletResponse resp, String msg, String url){

        try {
            resp.setContentType(contentType);
            PrintWriter writer = resp.getWriter();

            String script = ""
                    + "<script>"
                    + " alert ('" + msg + "');"
                    + " location.href='" + url + ";"
                    + "</script>";
            writer.print(script);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 알림창 띄운 후 뒤로가기
    public static void alertBack(HttpServletResponse resp, String msg){

        try {
            resp.setContentType(contentType);
            PrintWriter writer = resp.getWriter();

            String script = ""
                    + "<script>"
                    + " alert ('" + msg + "');"
                    + " history.back();"
                    + "</script>";
            writer.print(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
