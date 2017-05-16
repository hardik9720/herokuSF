package com.ccpoc.ccpoc.dao;

import com.ccpoc.ccpoc.model.User;

public interface UserDao {

	User findByUserName(String username);

}