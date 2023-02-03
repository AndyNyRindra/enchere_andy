package com.enchere.model.stat;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "v_user_rentable")
@Getter
@Setter
public class UserRentable extends BaseModel {

    private String nom;
    private String email;
    private String mdp;
    private Double stat;
}
