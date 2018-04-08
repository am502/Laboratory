package ru.kpfu.itis.auth;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;


@Component(value = "authProvider")
@ComponentScan(basePackages = {"ru.kpfu.itis.repository."})
public class AuthProviderImpl implements AuthenticationProvider {

    public static final String PREFIX_ROLE = "ROLE_";

    @Autowired
    UsersRepository usersRepository;

    public AuthProviderImpl() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String nickname = authentication.getName();

        User user = usersRepository.findByNickname(nickname);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        String password = authentication.getCredentials().toString();

        String cryptPassword = DigestUtils.md5Hex(password);
        if (!cryptPassword.equals(user.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(PREFIX_ROLE + user.getRole().name()));

        return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
