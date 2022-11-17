package com.andrea.posty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrea.posty.models.Comment;
import com.andrea.posty.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired 
	private CommentRepository commentRepo;
	
	public Comment create(Comment c) {
		return commentRepo.save(c);
	}

}
