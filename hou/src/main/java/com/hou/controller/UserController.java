package com.hou.controller;

import com.hou.entity.Login;
import com.hou.entity.LoginDto;
import com.hou.entity.LogincDto;
import com.hou.entity.User;
import com.hou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.io.IOException;
import javax.imageio.ImageIO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session)  {
        String storedCaptcha = (String) session.getAttribute("captcha");
        if (storedCaptcha != null && storedCaptcha.equalsIgnoreCase(loginDto.getCode())) {
            // 验证码正确，继续验证用户名和密码
            User user = userService.login(loginDto.getName(), loginDto.getPassword());
            if (user != null) {
                // 登录成功，可以返回用户信息或者token等
                return ResponseEntity.ok(user);
            } else {
                // 用户名或密码错误
                return ResponseEntity.badRequest().body("用户名或密码不正确");
            }
        } else {
            // 验证码错误
            return ResponseEntity.badRequest().body("验证码不正确");
        }
    }

    @PostMapping("/loginc")
    public ResponseEntity<?> loginc(@RequestBody LogincDto logincDto, HttpSession session)  {
        String storedCaptcha = (String) session.getAttribute("captcha");
        if (storedCaptcha != null && storedCaptcha.equalsIgnoreCase(logincDto.getCode())) {
            // 验证码正确，继续验证用户名和密码
            User user = userService.loginc(logincDto.getPhone(), logincDto.getPassword());
            if (user != null) {
                // 登录成功，可以返回用户信息或者token等
                return ResponseEntity.ok(user);
            } else {
                // 用户名或密码错误
                return ResponseEntity.badRequest().body("手机号或密码不正确");
            }
        } else {
            // 验证码错误
            return ResponseEntity.badRequest().body("验证码不正确");
        }
    }

    //修改密码
    @PostMapping("/forget")
    public Integer forget(@RequestBody Map<String, String> loginData) {
        String name = loginData.get("name");
        String password = loginData.get("password");
        return userService.forget(name, password);
    }
    @PostMapping("/forgetc")
    public Integer forgetc(@RequestBody Map<String, String> loginData) {
        String phone = loginData.get("phone");
        String password = loginData.get("password");
        return userService.forgetc(phone, password);
    }

    //根据测试结果更改user的studyshort
    @PostMapping("/update-studyshort")
    public Integer updateStudyShort(@RequestBody Map<String, String> loginData) {

        String name = loginData.get("name");
        String belong = loginData.get("belong");
        return userService.updateStudyShort(name, belong);

    }

    //得到验证码
    @GetMapping(value = "/getcode", produces = MediaType.IMAGE_PNG_VALUE)
    public void getCaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码文本
        String captchaText = userService.generateCaptchaText(6); // 假设长度为6

        // 将验证码文本保存到会话中
        session.setAttribute("captcha", captchaText);

        // 生成验证码图片
        BufferedImage captchaImage = userService.generateCaptchaImage(captchaText);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        // 设置响应头和内容类型
        response.setContentType("image/png");

        try (ServletOutputStream sos = response.getOutputStream()) {
            // 将图片写入响应输出流
            ImageIO.write(captchaImage, "png", sos);
            sos.flush();
        }
    }

    //新增
    @PostMapping("/register")
    public Integer register(@RequestBody Login login, HttpSession session) {
        String code=login.getcode();
        String name=login.getname();
        String phone=login.getphone();
        String password=login.getpassword();
        // 从会话中获取之前保存的验证码
        String storedCaptcha = (String) session.getAttribute("captcha");

        // 验证验证码是否正确
        if (storedCaptcha != null && storedCaptcha.equalsIgnoreCase(code)) {

            return userService.add(name,phone,password); // 假设的视图名称
        } else {
            // 验证码错误，返回错误信息或重新显示注册表单
            // ...
            return 2;
        }
    }

}
