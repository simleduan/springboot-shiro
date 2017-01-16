package com.chan.info.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, String username,String password,Map<String, Object> map) throws Exception {
		System.out.println("我机那里了。。。。。。。。。。。。。。。。。。。。。我接收到的用户名是");
		
		
		System.out.println("LoginController我接收到的用户名是"+username);
		System.out.println("LoginController我接收到的密码是"+password);
		if(username!=null && password!=null){
			//0.对密码加密
			Md5Hash md5=new Md5Hash(password, "", 1);
			System.out.println("LoginController我接收到的密码是"+md5.toString());
			
			//1.创建令牌( 对用户名和密码的封装 )
			UsernamePasswordToken token=new UsernamePasswordToken(username, md5.toString());
			System.out.println("token的值是"+token);
			//2.获得subject( 主题， 当前用户操作类，封装了一系列的操作 ,应用程序与shiro交互的入口部分)
			Subject subject = SecurityUtils.getSubject();
			System.out.println("token的值是"+subject);
			//3.执行认证		
			try {
				subject.login(token);
				return "index";
			} catch (Exception e) {
				String exception = (String) request.getAttribute("shiroLoginFailure");
				String msg = null;
				System.out.println("LoginController.login--我登陆失败了，从shiro获得到的异常信息是--"+exception);
				if(UnknownAccountException.class.getName().equals(exception)){
					msg = "您输入的---用户名---有误";
				}
				else if(IncorrectCredentialsException.class.getName().equals(exception)){
					//org.apache.shiro.authc.IncorrectCredentialsException
					msg = "您输入的---密码---有误";
				} else if("kaptchaValidateFailed".equals(exception)){
					msg = "您输入的---验证吗---有误";
				}
				map.put("msg", msg);
				return "login";
			}
		} else {
			String msg = null;
			msg = "请输入用户名和密码";
			map.put("msg", msg);
			return "login";
		}
	}
	
	@RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logout(){
        SecurityUtils.getSubject().logout();
        System.out.println("login退出正常跳转");
        return "login";
    }
}
