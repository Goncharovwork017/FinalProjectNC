package by.goncharov.nc.dto.dto;

/**
 * Created by ivan on 20.05.2017.
 */
public abstract class AbstractDTO {



    protected int id;

    public AbstractDTO() {
    }

    public AbstractDTO(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
