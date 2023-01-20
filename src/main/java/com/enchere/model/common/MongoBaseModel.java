package com.enchere.model.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
public class MongoBaseModel {

    @Id
    protected String id;

    public void setId(String id) {
        this.id = id;
    }
}
