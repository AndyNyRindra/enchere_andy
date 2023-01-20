package com.enchere.model.enchere;

import com.enchere.exception.CustomException;
import com.enchere.model.common.BaseModel;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    private Double prixMinimalVente;
    private Integer status;
    private Double comission;
    private Double duree;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "idEnchere")
    private List<MiseEnchere> mises;

    public boolean isEnchereOver() throws CustomException {
        if (!getDateFin().before(java.sql.Date.valueOf(java.time.LocalDate.now()))) return false;
        throw new CustomException("L'enchere est terminee");
    }
}
