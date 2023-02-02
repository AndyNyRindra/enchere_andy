package com.enchere.model.enchere;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhotosEnchere extends BaseModel {

    private Long idEnchere;
    private String bytes;
}
