package com.rafaG.rentacar.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id") @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name") @Getter @Setter
    private String name;

    @Column(name = "email") @Getter @Setter
    private String email;

    @Column(name = "email_verified_at") @Getter @Setter
    private LocalDateTime email_verified_at;

    @Column(name = "password") @Getter @Setter
    private String password;

    @Column(name = "remember_token") @Getter @Setter
    private String remember_token;

    @Column(name = "apellido") @Getter @Setter
    private String apellido;

    @Column(name = "cedula") @Getter @Setter
    private String cedula;

    @Column(name = "no_licencia") @Getter @Setter
    private String no_licencia;

    @Column(name = "foto_perfil") @Getter @Setter
    private String foto_perfil;

    @Column(name = "foto_licencia") @Getter @Setter
    private String foto_licencia;

    @Column(name = "rol",  columnDefinition = "varchar(255) default 'vendedor'") @Getter @Setter
    private String rol;

    @Column(name = "estado", columnDefinition = "boolean default true") @Getter @Setter
    private boolean estado;

    @Column(name = "fecha_nac") @Getter @Setter
    private LocalDate fecha_nac;

    @Column(name = "fecha_venc") @Getter @Setter
    private LocalDate  fecha_venc;

    @Column(name = "created_at") @Getter @Setter
    private LocalDateTime created_at;

    @Column(name = "updated_at") @Getter @Setter
    private LocalDateTime updated_at;
}
