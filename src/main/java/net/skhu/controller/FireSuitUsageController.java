package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import net.skhu.dto.FireSuitUsage;
import net.skhu.dto.User;
import net.skhu.model.FireSuitUsageEdit;
//import net.skhu.model.Pagination;
import net.skhu.model.Pagination1;
//import net.skhu.model.FireSuitUsageEdit;
import net.skhu.service.FireSuitUsageService;
import net.skhu.service.UserService;

@Controller
@RequestMapping("fire")
public class FireSuitUsageController {

    @Autowired FireSuitUsageService fireSuitUsageService;
    @Autowired UserService userService;

    // 방화복 사용 내역 목록 조회
    @GetMapping("fireSuitUsageslist")
    public String list(Model model, Pagination1 pagination) {
    	List<FireSuitUsage> firesuitusages = fireSuitUsageService.findAll(pagination);
    	model.addAttribute("fireSuitUsages", firesuitusages);
        return "eq/fireSuitUsageslist";
    }

    // 방화복 사용 내역 추가 또는 수정 폼
    @GetMapping("create")
    public String create(Model model, Pagination1 pagination) {
        FireSuitUsageEdit FireSuitUsageEdit = new FireSuitUsageEdit();
        List<User> users = userService.findAll();
        model.addAttribute("FireSuitUsageEdit", FireSuitUsageEdit);
        model.addAttribute("users", users);
        return "eq/fireSuitUsage";
    }

    @PostMapping("create")
    public String create(Model model, Pagination1 pagination,
            @Valid FireSuitUsageEdit FireSuitUsageEdit, BindingResult bindingResult) {
        try {
        	fireSuitUsageService.insert(FireSuitUsageEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("users", userService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "eq/fireSuitUsage";
        }
    }

    @GetMapping("edit")
    public String edit(Model model,@RequestParam("id") String id, Pagination1 pagination) {
        FireSuitUsageEdit FireSuitUsageEdit = fireSuitUsageService.findOne(id);
        List<User> users = userService.findAll();
        model.addAttribute("FireSuitUsageEdit", FireSuitUsageEdit);
        model.addAttribute("users", users);
        return "eq/fireSuitUsage";
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, Pagination1 pagination,
            @Valid FireSuitUsageEdit FireSuitUsageEdit, BindingResult bindingResult) {
        try {
        	fireSuitUsageService.update(FireSuitUsageEdit, bindingResult);
            return "redirect:list?" + pagination.getQueryString();
        } catch (Exception e) {
            model.addAttribute("users", userService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "eq/fireSuitUsage";
        }
    }

    @PostMapping(value="create", params="cmd=delete")
    public String delete(Model model, Pagination1 pagination,
            FireSuitUsageEdit FireSuitUsageEdit, BindingResult bindingResult) {
        try {
        	fireSuitUsageService.delete(FireSuitUsageEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
        } catch (Exception e) {
            model.addAttribute("users", userService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "eq/fireSuitUsage";
        }
    }
}
