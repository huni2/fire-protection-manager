package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import net.skhu.model.UserSignUp;
import net.skhu.service.UserService;

@Controller
public class MainController {

	@Autowired UserService userService;

	@GetMapping("main")
    public String main(Model model) {

        return "eq/mainpage";
    }

	@PostMapping("check")
    public String check(Model model) {

        return "redirect:eq/list";
    }
	@GetMapping("Login")
    public String login(Model model) {

        return "eq/Login";
    }

	@GetMapping("register")
    public String register(Model model) {

        return "eq/register";
    }

	@GetMapping("signUp")
    public String signup(Model model) {
        model.addAttribute(new UserSignUp());
        return "eq/signup";
    }

    @PostMapping("signUp")
    public String signup(Model model,
            @Valid UserSignUp userSignUp, BindingResult bindingResult)
    {
        try {
            userService.insert(userSignUp, bindingResult);
            return "eq/signUpSuccess";
        }
        catch (Exception e) {
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            e.printStackTrace();
            return "eq/signup";
        }
    }



    @GetMapping("signUpSuccess")
    public String signupSuccess() {
        return "eq/signUpSuccess";
    }

}

//@GetMapping("create")
//public String create(Model model, Pagination1 pagination) {
//    EqEdit eqEdit = new EqEdit();
//    List<User> users = userService.findAll();
//    model.addAttribute("eqEdit", eqEdit);
//    model.addAttribute("users", users);
//    return "eq/edit";
//}