package com.andrea.posty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrea.posty.models.Post;
import com.andrea.posty.repositories.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	public Post create(Post p) {
		return postRepo.save(p);
	}
	
	public Post getById(Long id) {
		Optional<Post> post = postRepo.findById(id);
		if (post.isPresent()){
			return post.get();		//if the Id is found then the object would be return 
		}
		else {
			return null;			// if its not will not return anything 
		}
	}
	
	public void delete(Long id) {
		postRepo.deleteById(id);
	}
	
	public Post update(Post p) {		//need this due to save method two different things 
		return postRepo.save(p);
	}
	
	public List<Post> getAll(){
		return postRepo.findAll();
	}

}
