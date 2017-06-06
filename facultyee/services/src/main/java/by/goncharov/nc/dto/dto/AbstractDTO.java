package by.goncharov.nc.dto.dto;

/**
 * Created by ivan on 20.05.2017.
 */
public abstract class AbstractDto {



    protected int id;
    public AbstractDto() {
    }



    public AbstractDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    }

