package Wx.Service.impl;

import Wx.Service.UserService;
import Wx.dao.UserDao;
import Wx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public  User findByName(String username){
        return userDao.findByName(username);
    }
    public  int update(User user){
        return userDao.update(user);
    }
     public List<User> findAll(){
        return  userDao.findAllUser();
     }
     public  int delete(Long id){
        return  userDao.delete(id);
     }



}
