package Wx.Service;

import Wx.model.User;

import java.util.List;

public interface UserService {
      public User  findByName(String username);
      public  int  update(User user);
       public List<User> findAll();
       public  int delete(Long id);
}
