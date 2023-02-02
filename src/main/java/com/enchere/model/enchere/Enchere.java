package com.enchere.model.enchere;

import com.enchere.exception.CustomException;
import com.enchere.model.common.BaseModel;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static com.enchere.util.DateUtil.getDateNow;

@Entity
@Getter
@Setter
public class Enchere extends BaseModel {

    private Date dateDebut;
    private Date dateFin;
    private String nom;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @Column
    private Double prixMinimalVente;

    private Integer status;
    private Double comission;
    private Double duree;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "idEnchere")
    private List<MiseEnchere> mises;

    @OneToMany(mappedBy = "idEnchere")
    private List<PhotosEnchere> photos;

    @JsonIgnore
    public boolean isEnchereOver() {
        if (!getDateFin().before(getDateNow())) return false;
        return true;
    }
}
