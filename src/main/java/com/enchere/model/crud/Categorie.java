package com.enchere.model.crud;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categorie extends BaseModel {
    @Column(name = "nom", nullable = false)
    private String nom;
}
