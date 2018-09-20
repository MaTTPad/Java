package SpringBoot.Extras;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<MyImage> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MyImage> getResult() {
        return result;
    }

    public void setResult(List<MyImage> result) {
        this.result = result;
    }
}
