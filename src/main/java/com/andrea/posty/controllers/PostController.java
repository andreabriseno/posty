package com.andrea.posty.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andrea.posty.models.Comment;
import com.andrea.posty.models.Post;
import com.andrea.posty.models.User;
import com.andrea.posty.services.CommentService;
import com.andrea.posty.services.PostService;
import com.andrea.posty.services.UserService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postServ;
	
	@Autowired
	private UserService userSer;
	
	@Autowired 
	private CommentService commentServ;
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute(name="post") Post post, BindingResult result, HttpSession session) {
			if(!result.hasErrors()) {
				User user = userSer.getById((Long) session.getAttribute("userId"));			//attaching id and post to gether w/o this would creat empty data 
				post.setUser(user);
				postServ.create(post);				
			}
			return "redirect:/dashboard";
		}
	
	@GetMapping("/{id}")			//model is needing for view so that view model can pass data to jsp		
	public String view(@PathVariable(name="id")Long id, Model model, HttpSession session) {
		Post post = postServ.getById(id);
		model.addAttribute("post", post);
		Long userId =(Long) session.getAttribute("userId"); //getting session casting it as long so that it remembers 
		Boolean isAuthor = (post.getUser().getId().equals(userId)); //comparing the ID of the author and id in session to see id they match
		model.addAttribute("isAuthor", isAuthor);
		model.addAttribute("comment", new Comment());
		return "viewPost.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable(name="id")Long id) {
		postServ.delete(id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/{id}/update")
	public String edit(@PathVariable(name="id")Long id, Model model){
		Post post =postServ.getById(id);
		model.addAttribute("post", post);
		return "updatePost.jsp";
	}
	
	@PutMapping("/update")
	public String update(@Valid @ModelAttribute(name="post") Post post, BindingResult result) {
		if(result.hasErrors()) {
			return "updatePost.jsp";
		}
		else {
			postServ.update(post);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/{id}/like")
	public String likePost(@PathVariable(name="id")Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		Post post = postServ.getById(id);		//pass in id not userid bc its attached to the post
		User user = userSer.getById(userId);
		post.getLikedBy().add(user);
		postServ.update(post);    //save likes to the database
		return "redirect:/dashboard";
	}
	
	@PostMapping("/{id}/comment")
	public String addComment(@Valid @ModelAttribute(name="comment") Comment comment, BindingResult result, HttpSession session, @PathVariable(name="id")Long id) {
		comment.setPost(postServ.getById(id));			//attach user and comment to create relationship
		Long userId =(Long) session.getAttribute("userId");		//grabbing user id out session 
		comment.setUser(userSer.getById(userId));
		commentServ.create(comment);
		return "redirect:/dashboard";
	}
	

}
