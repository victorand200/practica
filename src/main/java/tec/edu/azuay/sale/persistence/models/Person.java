package tec.edu.azuay.sale.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String phone;

    private String email;

    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "person")
    private List<User> users = new ArrayList<>();

    @OneToMany(targetEntity = Bill.class, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Bill> bills = new ArrayList<>();
}
