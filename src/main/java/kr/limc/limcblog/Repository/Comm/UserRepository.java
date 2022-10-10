package kr.limc.limcblog.Repository.Comm;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.limc.limcblog.Entity.Comm.User;

public interface UserRepository extends JpaRepository<User, String>{
    User findByUserId(String userId);
    
}
