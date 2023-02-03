package com.enchere.model.enchere;

import com.enchere.exception.CustomException;
import com.enchere.exception.ValueInvalideException;
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

    private Double prixVente;

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

    public void setDateDebut(Date valeur) throws ValueInvalideException {
        if (dateFin!=null && valeur!=null) if (valeur.after(dateFin)) throw new ValueInvalideException();
        this.dateDebut=valeur;
    }
    public void setDateFin(Date valeur) throws ValueInvalideException {
        if (dateDebut!=null&& valeur!=null) if (valeur.before(dateDebut)) throw new ValueInvalideException();
        this.dateFin=valeur;
    }

    public void setPrixMinimalVente(Double prixMinimalVente) throws ValueInvalideException {
        if (prixMinimalVente!= null && (prixMinimalVente.isNaN()||prixMinimalVente.isInfinite()||prixMinimalVente<0)) throw new ValueInvalideException();
        this.prixMinimalVente = prixMinimalVente;
    }

    public void setComission(Double comission) throws ValueInvalideException {
        if (comission != null && (comission.isNaN()||comission.isInfinite()||comission<0)) throw new ValueInvalideException();
        this.comission = comission;
    }

    public void setDuree(Double duree) throws ValueInvalideException {
        if (duree != null && (duree.isNaN()||duree.isInfinite()||duree<0)) throw new ValueInvalideException();
        this.duree = duree;
    }
}
