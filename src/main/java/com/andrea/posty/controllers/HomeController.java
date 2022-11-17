package com.andrea.posty.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.andrea.posty.models.LoginUser;
import com.andrea.posty.models.Post;
import com.andrea.posty.models.User;
import com.andrea.posty.services.PostService;
import com.andrea.posty.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	private PostService postServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("loginUser", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute(name="user") User user, BindingResult result, Model model, HttpSession session ) {
		User newUser = userSer.register(user,result);
		if(newUser == null) {
			model.addAttribute("loginUser", new LoginUser());
			return "index.jsp";
		}
		else {
			session.setAttribute("userId", newUser.getId());		//So we know which user is logged in 
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute(name="loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User user = userSer.login(loginUser);
		if (user == null || result.hasErrors()) {
			result.rejectValue("email", "Matched", "Invaild Email or Password");
			model.addAttribute("user", new User());
			return"index.jsp";
		}
		else {
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		}
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id =(Long) session.getAttribute("userId");
		if(id != null) {
			model.addAttribute("post", new Post());
			model.addAttribute("loggedUser", userSer.getById(id));
			model.addAttribute("allPosts", postServ.getAll());
			return "dashboard.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
}
