package com.wuming.util.servlet;

import javax.servlet.http.Part;

/**
 * Created by wuming on 16/5/22.
 */
public class UploadUtil {

    public static String getFileType(Part p) {
        String name = p.getHeader("content-disposition");
        String fileNameTmp = name.substring(name.indexOf("filename=") + 10);
        String type = fileNameTmp.substring(fileNameTmp.indexOf(".") + 1, fileNameTmp.indexOf("\""));
        return type;
    }

    public static String getFileName(Part p) {
        // name 格式如：form-data; name="upload"; filename="xxx.jpg"
        String name = p.getHeader("content-disposition");
        String fileNameTmp = name.substring(name.indexOf("filename=") + 10);
        String fileName = fileNameTmp.substring(0, fileNameTmp.indexOf("\""));
        return fileName;
    }
}
