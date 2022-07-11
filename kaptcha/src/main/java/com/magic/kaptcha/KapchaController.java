package com.magic.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author mzk
 * @create 2022-07-11 11:14
 */
@RestController
public class KapchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @GetMapping("/img")
    public void captchaImg(HttpServletResponse resp) throws IOException {
        //生成验证码的文本
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @GetMapping("/hello")
    public void hello(HttpSession session) {
        System.out.println("session.getAttribute(\"kaptchaCode\") = " + session.getAttribute("kaptchaCode"));
    }
}
