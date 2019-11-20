package psoft.labdao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import psoft.labdao.daos.UserRepository;
import psoft.labdao.entities.UserDao;

@Service
public class UserService {

    private UserRepository<UserDao, Long> userDAO;

    public UserService(UserRepository<UserDao, Long> userDAO) {
        super();
        this.userDAO = userDAO;
    }

    public UserDao addUser(UserDao user) {
        return userDAO.save(user);
    }

    public List<UserDao> getUser() {
        return userDAO.findAll();
    }

    public Optional<UserDao> getUser(String email) { return userDAO.findByEmail(email); }
}