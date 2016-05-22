package com.wuming.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateCode() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 7.禁止浏览器缓存随机图片
		System.out.println("---------create auth code-------------");
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 6.通知客户机以图片方式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");
		// 1.创建一副图片
		BufferedImage image = new BufferedImage(50, 20, BufferedImage.TYPE_INT_RGB);
		// 2.向图片上写数据
		Graphics g = image.getGraphics();
		// 设置背景颜色
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 80, 30);
		// 3.设置写入数据的颜色和字体
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));
		// 4.向图片上写数据
		String num = makeNum();
		// 这句话就是把随机生成的数值保存到session里
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);
		// 5.把写好数据的图片输出给浏览器
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}

	// 随机生成７位数字的字符串
	public String makeNum() {
		Random r = new Random();
		String num = r.nextInt(9999) + "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4 - num.length(); i++) {
			sb.append("0");
		}
		num = sb.toString() + num;
		return num;
	}

}
