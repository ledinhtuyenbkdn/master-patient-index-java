package com.ledinhtuyenbkdn.masterpersonindex.config.security;

import com.ledinhtuyenbkdn.masterpersonindex.model.User;
import com.ledinhtuyenbkdn.masterpersonindex.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(s);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("UserName is not found: " + s);
        }

        User user = optionalUser.get();
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), role);
    }
}
