package com.example.laboratorio3.entity;

import javax.persistence.*;

@Entity
@Table(name="jobs")
public class Jobs {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int job_id;


        @Column(nullable = false)
        private String job_title;
}
