package ru.itis.info.semesterwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String email;
    private String country;
    private String hashPassword;
    private String gender;
    private String confirmCode;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private boolean confirmed = false;
    private boolean isBanned = false;
    @Enumerated(value = EnumType.STRING)
    private Way way;
    @OneToMany(mappedBy = "owner")
    private List<Ad> ads;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Resume> resumes;

    @ManyToMany
    private List<Team> team;

    public enum Role {
        USER,
        ADMIN
    }

    public enum Way {
        OAUTH, REGISTRATION
    }

    public enum Permission {
        GET,
        POST
    }
}
