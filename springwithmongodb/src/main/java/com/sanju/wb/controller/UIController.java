package com.sanju.wb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sanju.model.LoginForm;
import com.sanju.pageconstant.PageConstants;
import com.sanju.wb.dao.entity.UserEntity;
import com.sanju.wb.service.WBUtilityService;
import com.sanju.wb.utils.ResponseWrapper;
@Controller
@EnableSpringHttpSession
@EnableWebMvc
@ComponentScan(basePackages = {"com.sanju"})
public class UIController extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/WEB-INF/jsp/common/index").setViewName("index");
		registry.addViewController("/WEB-INF/jsp/common/signupPage").setViewName("signupPage");
		registry.addViewController("/WEB-INF/jsp/common/forgotPassword").setViewName("forgotPassword");
		registry.addViewController("/WEB-INF/jsp/common/delete").setViewName("delete");
	}

	@Autowired
	WBUtilityService wbUtility;
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String loginPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("login");
		return PageConstants.LOGINPAGE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userRegsiter", method = { RequestMethod.GET })
	public String userRegister(@ModelAttribute("submitButton") LoginForm form, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("userRegister");
		JSONObject userRegisterDetails = new JSONObject();
		JSONObject obj = new JSONObject();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(form.getPassword());
		System.out.println("password:"+hashedPassword);
		obj.put("email",form.getEmail());
		obj.put("password",hashedPassword);
		obj.put("fname", form.getFname());
		obj.put("lname", form.getLname());
		obj.put("mobile", form.getMobile());
		obj.put("userRegisterDetails", userRegisterDetails.toJSONString());
		ResponseWrapper resultWr = wbUtility.createUserRegister(obj.toJSONString());
		System.out.println("message:----------"+resultWr.errorMessage);
		model.addAttribute("errorMessage",resultWr.errorMessage);
		return PageConstants.LOGINPAGE;
	}
	@RequestMapping(value = "/authenticate", method = { RequestMethod.GET })
	public String authenticatePageShow(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("authenticatePageShow");
		return PageConstants.SIGNUPPAGE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authenticateWithCredential", method = { RequestMethod.POST })
	public String authenticatePage(@ModelAttribute("submitButton") LoginForm form, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("authenticatePage");
		try{
			JSONObject userRegisterDetails = new JSONObject();
			JSONObject obj = new JSONObject();
			obj.put("email",form.getEmail());
			obj.put("password",form.getPassword());
			obj.put("userRegisterDetails", userRegisterDetails.toJSONString());
			UserEntity resultWr = wbUtility.passwordValidated(obj.toJSONString());
			if (resultWr!=null){
				System.out.println(resultWr.toString());
				model.addAttribute("user", resultWr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return PageConstants.SIGNUPPAGE;
	}

	@RequestMapping(value = "/forgotpasspage", method = { RequestMethod.GET })
	public String fogotPasswordPage(@ModelAttribute("submitButton") LoginForm form, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("fogotPasswordPage");
		return PageConstants.FORGOTPASSWORD;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/passwordupdate", method = { RequestMethod.POST })
	public String forgotPasswordUpdate(@ModelAttribute("submitButton") LoginForm form, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("forgotPasswordUpdate");
		try{
			JSONObject userRegisterDetails = new JSONObject();
			JSONObject obj = new JSONObject();
			obj.put("email",form.getEmail());
			obj.put("password",form.getPassword());
			obj.put("mobile", form.getMobile());
			obj.put("userRegisterDetails", userRegisterDetails.toJSONString());
			boolean passWordUpdate=wbUtility.passwordUpdate(obj.toJSONString());
			if (passWordUpdate){
				System.out.println("updated Successfuly"+form.getEmail());
			}
			else {
				System.out.println("not updated "+form.getEmail());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return PageConstants.FORGOTPASSWORD;
	}
	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public String deletePage(@ModelAttribute("submitButton") LoginForm form, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("fogotPasswordPage");
		return PageConstants.DELETE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteuser", method = { RequestMethod.POST })
	public String deleteUser(@ModelAttribute("submitButton") LoginForm form, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		System.out.println("forgotPasswordUpdate");
		try{
			JSONObject userRegisterDetails = new JSONObject();
			JSONObject obj = new JSONObject();
			obj.put("email",form.getEmail());
			obj.put("mobile", form.getMobile());
			obj.put("userRegisterDetails", userRegisterDetails.toJSONString());
			boolean passWordUpdate=wbUtility.deleteUser(obj.toJSONString());
			if (passWordUpdate){
				System.out.println("Deleted Successfuly"+form.getEmail());
			}
			else {
				System.out.println("not Deleted "+form.getEmail());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return PageConstants.DELETE;
	}





}
