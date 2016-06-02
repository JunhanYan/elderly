package com.elderly.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elderly.model.Admin;

@Controller
@RequestMapping("/loginController")
public class LoginController {
	
 
	//返回json @ResponseBody，直接把json返回view
	//         @RequestBody 解析view发来的json 
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	public @ResponseBody Admin login(@RequestBody Admin adm) {
		
		if(adm.getAdminName().equals("admin")&&adm.getAdminPass().equals("admin"))
			return adm;
		else
			return null;
		/*System.out.println("==========================="+user.getUserAccount()+user.getPassword()+user.getUserName());
		return null;*/
	}
	
}
