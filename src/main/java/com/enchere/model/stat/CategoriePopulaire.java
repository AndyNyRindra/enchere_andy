package com.enchere.model.stat;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "v_categorie_populaire")
@Getter
@Setter
public class CategoriePopulaire extends BaseModel {
    private String nom;
    private Double stat;

}
