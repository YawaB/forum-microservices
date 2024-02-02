package org.forum.postandreplyms.repository;

import org.forum.postandreplyms.entities.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(String status);

    List<Post> findByUserId(Long userId);

    List<Post> findByStatus(String status, Sort sort);

    List<Post> findByUserIdAndStatus(Long userId, String status);

    List<Post> findByUserIdAndStatus(Long userId, String status, Sort sort);

//    List<Post> findAllByIdAndStatus(List<String> postIds, String status);

    Optional<Post> findTopByUserIdOrderByDateCreatedDesc(Long userId);
}
