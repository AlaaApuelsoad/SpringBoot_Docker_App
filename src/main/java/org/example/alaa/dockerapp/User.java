package org.example.alaa.dockerapp;

import jakarta.persistence.*;

@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
}
