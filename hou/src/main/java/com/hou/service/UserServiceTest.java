package com.hou.service;


import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class UserServiceTest {
    @Test
    public void testGenerateCaptchaImage() throws IOException {
        UserService userService = new UserService(); //
        String captchaText = userService.generateCaptchaText(6); // 假设的验证码文本
        BufferedImage captchaImage = userService.generateCaptchaImage(captchaText);
        assertNotNull(captchaImage);

        // 将图片保存为文件以便查看
        File outputfile = new File("target/test-classes/captcha.png");
        ImageIO.write(captchaImage, "png", outputfile);
    }
}