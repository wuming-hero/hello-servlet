package com.wuming.servlet3;

import com.wuming.util.DateUtil;
import com.wuming.util.servlet.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by wuming on 16/5/22.
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Part part = request.getPart("file");
        PrintWriter out = response.getWriter();
        out.println("此文件的大小：" + part.getSize() + "<br />");
        out.println("此文件类型：" + part.getContentType() + "<br />");
        out.println("文本框内容：" + request.getParameter("name") + "<br />");
        out.println(UploadUtil.getFileName(part) + "<br />");
        String fileName = DateUtil.dateTimeToStr(new Date()) + "." + UploadUtil.getFileType(part);
        String filePath = "/web/file/" + fileName;
        filePath = "20160522210126.png";
        System.out.println("filepath: " + filePath);
        part.write(filePath);
    }
}
