package Wx.dao;

import Wx.model.User;

import java.util.List;

public interface UserDao {
    User findByName(String username);
    int update(User user);
    List<User> findAllUser();
    int delete(Long id);

}
