package org.example.DataBase.Models;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Company")
public class CompanyModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

<<<<<<< HEAD
=======
    public String getComapanyName() {
        return comapanyName;
    }

    public void setComapanyName(String comapanyName) {
        this.comapanyName = comapanyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

>>>>>>> 27c5454 (update)
    @Column(name = "ComapanyName")
    private String comapanyName;

    @Column(name = "Email")

    private String email;
}


