package kr.limc.limcblog.Repository.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.limc.limcblog.Entity.Post.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
}
