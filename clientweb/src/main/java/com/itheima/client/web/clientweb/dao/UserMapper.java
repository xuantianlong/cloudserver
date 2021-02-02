package com.itheima.client.web.clientweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webcommon.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
