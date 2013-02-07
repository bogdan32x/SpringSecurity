package com.blog.cavalr.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class UserDetailsServiceImpl implements UserDetailsService {

    //I am not connected to Database, so all users will be stored here.
    //In your production app. these users must be stored in database.
    static Map<String, UserFromDB> users = new HashMap<String, UserFromDB>();
    static {
        users.put("test1", new UserFromDB("test1", "test1", "ROLE_USER"));
        users.put("test2", new UserFromDB("test2", "test2", "ROLE_USER,ROLE_ADMIN"));
        users.put("test3", new UserFromDB("test3", "test3", "ROLE_ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserFromDB userFromDB = findUserFromDatabase(username);

        return new User(userFromDB.getUserName(), userFromDB.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(userFromDB.getRoles()));
    }

    //here put your own implementation to fetch user from Database or from anywhere else you want.
    private UserFromDB findUserFromDatabase(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        //if user is not found, null will be returned back and Authentication will fail(spring will take care of that, you
        // dont need to worry about it), you just need to help spring with finding the user.
        return null;
    }
}
