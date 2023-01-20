package com.enchere.model.enchere;

import com.enchere.model.common.BaseModel;
import com.enchere.model.login.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class MiseEnchere extends BaseModel {

    private Long idEnchere;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private Double montant;
    private Date date;
    private Boolean estPlusHaut;

}
