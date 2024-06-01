package com.example.aiot_home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiot_home.pojo.Account;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper extends BaseMapper<Account> {


    @Update("Update account set faceid=#{faceid} where id=#{id}")
    int UpdateFaceId(int id,String faceid);
}
