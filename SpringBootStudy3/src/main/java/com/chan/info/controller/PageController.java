package com.chan.info.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("add")
	//@RequiresPermissions("add")
	public String personAdd() {
		return "personAdd";
	}

	@RequestMapping("person")
	@RequiresPermissions("person")
	public String person() {
		return "person";
	}

	@RequestMapping("success")
	@RequiresPermissions("success")
	public String success() {
		return "success";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("personOption")
	public String personOption() {
		return "personOption";
	}

	@RequestMapping("everyone")
	public String everyone() {
		return "everyone";
	}

	@RequestMapping("403")
	public String errorPage() {
		return "403";
	}

}
