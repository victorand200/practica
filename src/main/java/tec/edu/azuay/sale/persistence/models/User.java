package tec.edu.azuay.sale.persistence.models;

import jakarta.persistence.*;
import lombok.*;
import tec.edu.azuay.sale.config.secondary.listeners.UserListener;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(UserListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false, length = 60, updatable = false)
    private String user;

    private String password;

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
