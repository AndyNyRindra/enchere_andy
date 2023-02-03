package com.enchere.model;

import java.util.Date;

import com.enchere.model.common.BaseModel;
import com.enchere.model.login.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class DemandeRechargement extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user;
    @Column(name = "montant", nullable = false)
    Double montant;
    @Column(name = "date", nullable = false)
    Date date;
    @Column(name = "status", nullable = false)
    int status;

}
