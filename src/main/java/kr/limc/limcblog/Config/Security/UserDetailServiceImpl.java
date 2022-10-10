package kr.limc.limcblog.Config.Security;

import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.limc.limcblog.Entity.Comm.User;
import kr.limc.limcblog.Repository.Comm.UserRepository;
import kr.limc.limcblog.Util.Exceptions.BlogException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uId) {
        String authenticatedLoginId = "";
        String authenticatedPassword = "";
        SimpleGrantedAuthority grantedAuthority = null;

        if(StringUtils.hasLength(uId)) {
            User user = userRepository.findByUserId(uId);

            if(Objects.isNull(user)) throw new BlogException("존재하지 않는 사용자입니다.");

            authenticatedLoginId = user.getUserId();
            authenticatedPassword = user.getPassword();
            grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        }

        return new org.springframework.security.core.userdetails.User(authenticatedLoginId, authenticatedPassword, Collections.singletonList(grantedAuthority));
    }

    

}
