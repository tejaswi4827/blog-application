package com.tejaswi.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tejaswi.blog.entity.Category;
import com.tejaswi.blog.entity.Post;
import com.tejaswi.blog.entity.User;

public interface postRepo extends JpaRepository<Post, Integer> {

	
	//findByTitleContaining this way will auto create sql query in back ground
	// we dont need to use @query annotation
	List<Post> findByUser( User user);
	List<Post> findByCategory(Category category);
	// native query is used for sql query
	//@Query("select u from post u  where u.title like :key")  and use @param("key") in method
	//@Query(value = "select * from post where title like :?key",nativeQuery = true)
	//@Query("select u from Post u where u.title like :key")
	//@Query(value="select * from Post p where p.title like = ?1",nativeQuery = true) error aa rha hai
	@Query("select u from Post u where u.title like :key")
	List<Post> searchTitle(String key);
}
