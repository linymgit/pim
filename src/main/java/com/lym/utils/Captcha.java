package com.lym.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @auth linyimin
 * @QQ: 1317113287
 * @desc: 验证码
 **/
public class Captcha {

    public static String str = "abcdefghijklmnopqrstuvwxyz";

    public static String getCharCode(HttpServletResponse response) {

        //1、使用stringbuilder类，对字符串进行处理。不用String，因为其赋值后不能改变。
        StringBuilder builder = new StringBuilder();
        //2、产生随机数，长度为4
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 4; i++) {
            builder.append(str.charAt(random.nextInt(str.length())));
        }

        String code = builder.toString();
        //3、定义图片的宽度和高度，使用BufferedImage对象。
        int width = 120;
        int height = 25;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //4、获取Graphics2D 绘制对象，开始绘制验证码
        Graphics2D g = bi.createGraphics();
        //5、定义文字的字体和大小
        Font font = new Font("微软雅黑", Font.PLAIN, 20);
        //6、定义字体的颜色
        Color color = new Color(0, 0, 0);
        //设置字体
        g.setFont(font);
        //设置颜色
        g.setColor(color);
        //设置背景
        g.setBackground(new Color(226, 226, 240));
        //开始绘制对象
        g.clearRect(0, 0, width, height);
        //绘制形状，获取矩形对象
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        //计算文件的坐标和间距
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        //结束绘制
        g.dispose();

        try {
            ImageIO.write(bi, "jpg", response.getOutputStream());
            //刷新响应流
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;

    }

    /**
     * 算术表达式验证码
     * <p>
     * 1、干扰线的产生
     * 2、范围随机颜色、随机数
     *
     * @param response
     * @return
     */
    public static String getArithmeticCode(HttpServletResponse response) {
        //定义验证码的宽度和高度
        int width = 100, height = 30;
        //在内存中创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建图片上下文
        Graphics2D g = image.createGraphics();
        //产生随机对象，用于算术表达式的数字
        Random random = new Random(System.currentTimeMillis());
        //设置背景
        g.setColor(getRandomColor(random, 240, 250));
        //设置字体
        g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        //开始绘制
        g.fillRect(0, 0, width, height);

        //干扰线的绘制，绘制线条到图片中
        g.setColor(getRandomColor(random, 180, 230));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            g.drawLine(x, y, x1, y1);
        }

        //对算术验证码表达式的拼接
        int num1 = (int) (Math.random() * 10 + 1);
        int num2 = (int) (Math.random() * 10 + 1);
        int operator = random.nextInt(3);
        String operatorStr = null;
        int result = 0;
        switch (operator) {
            case 0:
                operatorStr = "+";
                result = num1 + num2;
                break;
            case 1:
                operatorStr = "-";
                result = num1 - num2;
                break;
            case 2:
                operatorStr = "*";
                result = num1 * num2;
                break;

        }

        //图片显示的算术文字
        String calc = num1 + " " + operatorStr + " " + num2 + " = ?";
        //设置随机颜色
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        //绘制表达式
        g.drawString(calc, 5, 25);
        //结束绘制
        g.dispose();

        //输出图片到页面
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }


    /**
     * 随机颜色生成
     *
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandomColor(Random random, int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }


}
