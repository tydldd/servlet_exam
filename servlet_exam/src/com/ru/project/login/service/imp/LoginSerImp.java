package com.ru.project.login.service.imp;

import com.ru.project.login.dao.JdbcUtil;
import com.ru.project.login.service.LoginSerInter;

/**
 * Description:
 * User: NanChengRu
 * Date: 13-11-17
 * Time: 下午3:35
 * JDK: 1.6
 * version: 1.0
 */
public class LoginSerImp extends JdbcUtil implements LoginSerInter{
    public Object[] getUser(String userName, String password){
        Object[] obj = null;
        String sql = "select * from user where name = ? and password = ?";
        obj = sqlQueryUniqueObj(sql,userName,password);
        return obj;
    }
}
