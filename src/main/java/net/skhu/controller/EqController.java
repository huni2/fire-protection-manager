package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.skhu.dto.EQ;
import net.skhu.mapper.EQMapper;
import net.skhu.service.EQService;

//@Controller
//public class EqController {
//	@Autowired EQMapper EQMapper;
//
//    @RequestMapping("views/eqlist")
//    public String list(Model model) {
//        model.addAttribute("EQs", EQMapper.);
//        return "views/eqlist";
//    }
//
//
//}

@Controller   //해당 클래스를 컨트롤러로 동작하게 한다.
public class EqController {
	
	@Autowired
	private EQService eqService;
	private Object EQMapper;
	
	@RequestMapping("views/eqlist")    // 이 주소로 접속하면 이 메소드를 호출한다.(매핑한다.)
	public ModelAndView selectEQList() throws Exception{
		ModelAndView mv = new ModelAndView("/views/eqlist");
		
		List<EQ> eqList = eqService.selectEQList();
		mv.addObject("EQ",eqList);
		
		return mv;
	}
}
