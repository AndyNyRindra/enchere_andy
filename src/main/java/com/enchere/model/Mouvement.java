package com.enchere.model;

import com.enchere.model.common.BaseModel;
import com.enchere.model.login.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "mouvement_compte")
@Getter
@Setter
public class Mouvement extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "id_type_mouvement")
    TypeMouvement typeMouvement;
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user;
    @Column(name = "montant", nullable = false)
    double montant;
}
