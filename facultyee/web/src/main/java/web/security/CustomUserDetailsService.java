package web.security;

import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ivan on 02.06.2017.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO user = userService.getByLogin(login);
        if (user != null) {
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("could not find the user '" + login + "'");
        }
    }

}
