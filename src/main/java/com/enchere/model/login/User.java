package com.enchere.model.login;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "\"user\"")
@Getter
@Setter
public class User extends BaseModel {

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mdp;

}
