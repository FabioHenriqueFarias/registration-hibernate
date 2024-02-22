package org.example.DataBase.Models;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Company")
public class CompanyModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ComapanyName")
    private String comapanyName;

    @Column(name = "Email")

    private String email;
}


