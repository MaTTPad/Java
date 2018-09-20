/**
 * Created by mpadula on 2018-03-13.
 */
public class UserLoginException extends ChatException{

    public UserLoginException() {
        super("Użytkownik o podanej nazwie już istnieje.");
    }
}
