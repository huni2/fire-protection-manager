//package net.skhu.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import jakarta.validation.Valid;
//import net.skhu.model.UserSignUp;
//import net.skhu.service.UserService;
//
//@Controller
//public class SignUpController {
//
//	@Autowired UserService userService;
//
//	@GetMapping("signUp")
//    public String signup(Model model) {
//        model.addAttribute(new UserSignUp());
//        return "eq/signup";
//    }
//
//    @PostMapping("signUp")
//    public String signup(Model model,
//            @Valid UserSignUp userSignUp, BindingResult bindingResult)
//    {
//        try {
//            userService.insert(userSignUp, bindingResult);
//            return "redirect:eq/signUpSuccess";
//        }
//        catch (Exception e) {
//            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
//            e.printStackTrace();
//            return "eq/signup";
//        }
//    }
//
//
//
//}
