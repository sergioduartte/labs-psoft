package psoft.labdao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDao {
    @Id
    private String email;
    private String name;
    private String password;

    public UserDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDao(String email, String name, String password) {
        super();
        this.email = email;
        this.name = name;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", nome=" + name  + "]";
    }

}