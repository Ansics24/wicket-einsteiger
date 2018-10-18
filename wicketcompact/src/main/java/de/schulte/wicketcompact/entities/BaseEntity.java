package de.schulte.wicketcompact.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
