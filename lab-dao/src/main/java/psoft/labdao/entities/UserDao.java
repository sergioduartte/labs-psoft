package psoft.labdao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserDao {
    @GeneratedValue
    private long id;
    @Id
    private String email;
    private String name;
    private double senha;

    public UserDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDao(long id, String email, String name, double senha) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.senha = senha;
    }

    public UserDao(String email, String name, double senha) {
        super();
        this.email = email;
        this.name = name;
        this.senha = senha;
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

    public double getSenha() {
        return senha;
    }

    public void setSenha(double senha) {
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", nome=" + name  + "]";
    }

}