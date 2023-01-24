package com.sp.spring.repository.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.spring.util.DateAudit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
@Builder
public class Role extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ROLE_ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "roles")
    @JsonIgnore
    private Set<User> people = new HashSet<>();

    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;

    public void addUser(User user) {
        this.people.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        this.people.remove(user);
        user.getRoles().remove(this);
    }
}
