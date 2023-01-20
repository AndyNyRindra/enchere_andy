package com.enchere.model;

import com.enchere.model.common.BaseModel;
import com.enchere.model.login.Admin;
import com.enchere.model.login.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
public class DemandeRechargement extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user;
    @Column(name = "montant", nullable = false)
    double montant;
    @Column(name = "date", nullable = false)
    Date date;
    @Column(name = "status", nullable = false)
    int status;

}