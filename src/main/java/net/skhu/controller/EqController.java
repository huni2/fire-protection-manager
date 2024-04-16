package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import net.skhu.dto.Eq;
import net.skhu.dto.User;
import net.skhu.model.EqEdit;
import net.skhu.model.Pagination1;
import net.skhu.service.EQService;
import net.skhu.service.UserService;

@Controller
@RequestMapping("eq")
public class EqController {

    @Autowired EQService eqService;
    @Autowired UserService userService;

    @GetMapping("list")
    public String list(Model model, Pagination1 pagination) {
        List<Eq> eqList = eqService.findAll(pagination);
        model.addAttribute("eqLists", eqList);
        model.addAttribute("orders", eqService.getOrders());
        System.out.println("======" + eqList.toString());
        return "eq/list";
    }

    @GetMapping("create")
    public String create(Model model, Pagination1 pagination) {
        EqEdit eqEdit = new EqEdit();
        List<User> users = userService.findAll();
        model.addAttribute("eqEdit", eqEdit);
        model.addAttribute("users", users);
        return "eq/edit";
    }

    @PostMapping("create")
    public String create(Model model, Pagination1 pagination,
            @Valid EqEdit eqEdit, BindingResult bindingResult) {
        try {
            eqService.insert(eqEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("users", userService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "eq/edit";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, int EQ_SEQ, Pagination1 pagination) {
        EqEdit eqEdit = eqService.findOne(EQ_SEQ);
        List<User> users = userService.findAll();
        model.addAttribute("eqEdit", eqEdit);
        model.addAttribute("users", users);
        return "eq/edit";
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, Pagination1 pagination,
            @Valid EqEdit eqEdit, BindingResult bindingResult) {
        try {
            eqService.update(eqEdit, bindingResult);
            return "redirect:eq/list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("users", userService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "eq/edit";
        }
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, Pagination1 pagination,
            EqEdit eqEdit, BindingResult bindingResult) {
        try {
            eqService.delete(eqEdit.getEQ_SEQ());
            return "redirect:eq/list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("users", userService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "eq/edit";
        }
    }
}


