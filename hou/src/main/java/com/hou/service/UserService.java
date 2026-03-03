package com.hou.service;

import com.hou.entity.User;
import com.hou.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String name, String password) {
        return userMapper.select(name, password);
    }

    public User loginc(String phone, String password) {
        return userMapper.selectc(phone, password);
    }

    public Integer forget(String name, String password) {
    return userMapper.updatepasswordByName(name, password);
    }

    public Integer forgetc(String phone, String password) {  return userMapper.updatepasswordByPhone(phone, password);  }

    public Integer updateStudyShort(String name, String belong) {
        return userMapper.save(name, belong);
    }

    public Integer add(String name,String phone,String password) {
        if (userMapper.selectByname(name) != null) {
            return 3;
        } else {
            return userMapper.add(name, phone, password);
        }
    }
    //先生成验证码文本
    public String generateCaptchaText(int length) {
        // 定义可能的字符集合
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        List<Character> charList = new ArrayList<>();
        // 将所有可能的字符添加到列表中
        for (char c : characters.toCharArray()) {
            charList.add(c);
        }
        // 打乱字符列表
        Collections.shuffle(charList, new Random());
        // 从打乱后的列表中取前length个字符来构建验证码
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captchaText.append(charList.get(i));
        }
        return String.valueOf(captchaText);
    }

    // 生成验证码图片
    public BufferedImage generateCaptchaImage(String captchaText) {
        int width = 120;
        int height = 41;
        int charWidth = width / captchaText.length(); // 假设字符等宽
        int charHeight = height * 2 / 3; // 假设字符高度为高度的2/3
        int charSpacing = (width - charWidth * captchaText.length()) / (captchaText.length() - 1); // 字符间距
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 设置背景色（通常只设置一次）
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.fillRect(0, 0, width, height);

        // 绘制字符
        FontMetrics fm = g.getFontMetrics(g.getFont());
        for (int i = 0; i < captchaText.length(); i++) {
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            int fontSize = (int) (charHeight * 0.7 + charHeight * 0.3 * r.nextDouble());
            g.setFont(new Font(null, Font.ITALIC | Font.BOLD, fontSize));
            char str = captchaText.charAt(i);
            // 使用FontMetrics来计算字符的基线位置，确保字符不会超出图片范围
            int baselineY = height - (int) (fm.getAscent() + 0.5f * fm.getDescent()); // 修正基线位置
            g.drawString(String.valueOf(str), i * (charWidth + charSpacing), baselineY);
        }

        // 绘制干扰线
        for (int i = 0; i < 3; i++) {
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 255)); // 确保透明度为255（不透明）
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);
            g.drawLine(x1, y1, x2, y2); // 绘制随机斜线
        }

        // 释放Graphics资源（可选，但推荐）
        g.dispose();

        return image;
    }


}