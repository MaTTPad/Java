package SpringBoot.Extras;

import java.io.Serializable;

public class Result implements Serializable {

    boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
