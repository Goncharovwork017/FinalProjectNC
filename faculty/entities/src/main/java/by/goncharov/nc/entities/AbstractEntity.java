package by.goncharov.nc.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by ivan on 01.06.2017.
 */


@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    protected int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity other = (AbstractEntity) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "AbstractEntity [id=" + id + "]";
    }


    public AbstractEntity() {
    }


    public AbstractEntity(int id) {
        this.id = id;
    }
}
