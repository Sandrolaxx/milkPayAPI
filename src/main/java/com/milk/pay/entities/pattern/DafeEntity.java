package com.milk.pay.entities.pattern;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.MappedSuperclass;

/**
 * @author SmartBr
 */
@MappedSuperclass
public abstract class DafeEntity extends PanacheEntityBase implements Serializable {

    public abstract void setId(Integer id);

    public abstract Integer getId();

    public boolean hasId() {
        return this.getId() != null;
    }

    public DafeEntity() {
        super();
    }

    @Override
    public String toString() {
        return getId() + "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        final DafeEntity other = (DafeEntity) obj;

        if (this.getId() != null && other.getId() == null) {
            return false;
        }

        if (other.getId() != null && this.getId() == null) {
            return false;
        }

        if (this.getId() == null && other.getId() == null) {
            return false;
        }

        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.getId());
        return hash;
    }

}
