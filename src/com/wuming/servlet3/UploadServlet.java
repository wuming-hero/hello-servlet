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
 *
 * @WebServlet的urlPatterns和value属性都可以用来表示Servlet的部署路径，它们都是对应的一个数组。 如果只部署一个路径可直接写成一个字符串
 */

/**
 *  在Servlet3.0中上传文件变得非常简单。我们只需通过request的getPart(String partName)获取到上传的对应文件对应的Part或者通过getParts()方法获取到所有上传文件对应的Part。
 *  之后我们就可以通过part的write(String fileName)方法把对应文件写入到磁盘。或者通过part的getInputStream()方法获取文件对应的输入流，然后再对该输入流进行操作。
 *  要使用request的getPart()或getParts()方法对上传的文件进行操作的话，有两个要注意的地方。首先，用于上传文件的form表单的enctype必须为multipart/form-data；
 *  其次，对于使用注解声明的Servlet，我们必须在其对应类上使用@MultipartConfig进行标注，而对于在web.xml文件进行配置的Servlet我们也需要指定其multipart-config属性，
 *  如：Xml代码
 *  <servlet>
 *      <servlet-name>xxx</servlet-name>
 *      <servlet-class>xxx.xxx</servlet-class>
 *      <multipart-config></multipart-config>
 *  </servlet>
 *  <servlet-mapping>
 *      <servlet-name>xxx</servlet-name>
 *      <url-pattern>/servlet/xxx</url-pattern>
 *  </servlet-mapping>

 *  不管是基于注解的@MultipartConfig，还是基于web.xml文件配置的multipart-config，我们都可以给它们设置几个属性。
 *  file-size-threshold：数字类型，当文件大小超过指定的大小后将写入到硬盘上。默认是0，表示所有大小的文件上传后都会作为一个临时文件写入到硬盘上。
 *  location：指定上传文件存放的目录。当我们指定了location后，我们在调用Part的write(String fileName)方法把文件写入到硬盘的时候可以，
 *      文件名称可以不用带路径，但是如果fileName带了绝对路径，那将以fileName所带路径为准把文件写入磁盘。
 *  max-file-size：数值类型，表示单个文件的最大大小。默认为-1，表示不限制。当有单个文件的大小超过了max-file-size指定的值时将抛出IllegalStateException异常。
 *  max-request-size：数值类型，表示一次上传文件的最大大小。默认为-1，表示不限制。当上传时所有文件的大小超过了max-request-size时也将抛出IllegalStateException异常。
 *  上面的属性是针对于web.xml中配置Servlet而言的，其中的每一个属性都对应了multipart-config元素下的一个子元素。对于基于注解配置的Servlet而言，
 *  @MultipartConfig的属性是类型的，我们只需把上述对应属性中间的杠去掉，然后把对应字母大写即可，如maxFileSize 单位:bytes。
 */

@WebServlet(name = "UploadServlet", value = "/upload")
@MultipartConfig(maxFileSize = 1024 * 1000 * 10)
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Part part = request.getPart("file");
        PrintWriter out = response.getWriter();
        String uploadPath = request.getServletContext().getRealPath("/upload");
        System.out.println(uploadPath + "----------------");
        out.println("此文件的大小：" + part.getSize() + "bytes<br />");
        out.println("此文件类型：" + part.getContentType() + "<br />");
        out.println("文件名称" + UploadUtil.getFileName(part) + "<br />");
        String fileName = DateUtil.dateTimeToStr(new Date()) + "." + UploadUtil.getFileType(part);
        // 上传到 mac 系统临时文件夹
        String filePath = "/tmp/" + fileName;
        part.write(filePath);
    }
}
