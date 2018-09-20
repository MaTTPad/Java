package SpringBoot.Extras;

import org.hibernate.validator.constraints.NotBlank;

public class IDCriteria {

    @NotBlank(message = "id can't be empty!")
    String idToDelete;

    public String getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(String idToDelete) {
        this.idToDelete = idToDelete;
    }
}