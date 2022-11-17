package com.andrea.posty.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andrea.posty.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Long> {
	

}
