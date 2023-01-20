package com.enchere.model;

import com.enchere.exception.CustomException;
import com.enchere.model.common.BaseModel;

public abstract class HasFK<FK> extends BaseModel {
    public abstract void setFK(FK fk) throws CustomException;
}
