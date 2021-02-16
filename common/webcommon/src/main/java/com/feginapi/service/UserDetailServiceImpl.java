package com.feginapi.service;

import com.feginapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) {
        String sql = "select * from t_user t where t.username='"+username+"'";
        List<Map<String,Object>> userList = jdbcTemplate.queryForList(sql);
        if(userList == null && userList.size() ==0){
              throw new UsernameNotFoundException("用户名或密码错误");
        }
        Map<String,Object> userMap  = userList.get(0);
        User user = new User(userMap.get("id").toString(),userMap.get("username").toString(),userMap.get("password").toString(),"");
        return user;
    }
}
