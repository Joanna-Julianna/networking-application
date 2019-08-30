package com.networkingapplication.posting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthorIdOrderByCreationDateDesc(Long authorId);

    List<Post> findByAuthorIdOrderByCreationDateDesc(Collection<Long> ids);

}
