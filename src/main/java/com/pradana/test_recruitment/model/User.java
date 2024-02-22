package com.pradana.test_recruitment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_user")
@Data
public class User {

    @Id
    @Column(name = "userid", nullable = false)
    private Integer userid;

    @Column(name = "namalengkap")
    private String namalengkap;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Character status;
}
