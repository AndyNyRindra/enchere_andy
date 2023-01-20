package com.enchere.model;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TypeMouvement extends BaseModel {
    @Column(name = "type", nullable = false)
    String type;

    public TypeMouvement(long id) {
        super.setId(id);
        if(id==1) setType("rechargement");
    }

    public TypeMouvement() {
    }
}
