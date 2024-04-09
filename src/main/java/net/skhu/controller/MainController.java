package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

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
}

//@GetMapping("create")
//public String create(Model model, Pagination1 pagination) {
//    EqEdit eqEdit = new EqEdit();
//    List<User> users = userService.findAll();
//    model.addAttribute("eqEdit", eqEdit);
//    model.addAttribute("users", users);
//    return "eq/edit";
//}