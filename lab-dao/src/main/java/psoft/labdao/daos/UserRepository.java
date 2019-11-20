package psoft.labdao.daos;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psoft.labdao.entities.UserDao;

@Repository
public interface UserRepository<T, ID extends Serializable> extends JpaRepository<UserDao, Long> {

    Optional<UserDao> findByEmail(String email);
}