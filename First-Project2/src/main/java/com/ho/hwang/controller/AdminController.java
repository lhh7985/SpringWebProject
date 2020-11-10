package com.ho.hwang.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ho.hwang.service.UserService;
import com.ho.hwang.vo.CodeVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {
	private final UserService userService;

	@GetMapping("/codetable")
	public String admin_code(Model model) {
		List<CodeVO> list = userService.selectCodeList();
		model.addAttribute("list", list);
		return "admin/codetable";
	}

	@PostMapping("/codetable/delete")
	@ResponseBody
	public int deleteCode(@RequestParam(value = "chbox[]") List<Integer> charr) throws Exception {
		int result = 0;
		if (charr != null) {
			for (int i : charr) {
				userService.deleteCode(i);
			}
			result=1;
		}
		return result;
	}
	
	@GetMapping("/enroll")
	public String codetable() {
		return "admin/enroll";
	}
	
	@PostMapping("/enroll")
	public void codetable(CodeVO codevo) {
		userService.insertCode(codevo);
	}

}
