package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.skhu.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService; // 사용자 인증을 위한 서비스 (필요에 따라 구현)

    @PostMapping("/Login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        boolean isAuthenticated = userService.authenticate(username, password);

        if (isAuthenticated) {
            return "redirect:eq/list"; // 로그인 성공 시 list.html로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/main"; // 로그인 실패 시 main.html로 리다이렉트
        }
    }
}

