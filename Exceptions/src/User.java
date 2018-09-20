/**
 * Created by mpadula on 2018-03-13.
 */
public class User {
    long id;
    String name;
    long numberOfStars;
    long numberOfSentMessages;


    public User(String name)
    {
        this.name=name;
        id=-1;
        numberOfSentMessages=0;
        numberOfStars=0;
    }

    public void setId(long id)
    {
        this.id=id;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setNumberOfStars(long numberOfStars)
    {
        this.numberOfStars=numberOfStars;
    }

    public void setNumberOfSentMessages(long numberOfSentMessages)
    {
        this.numberOfSentMessages=numberOfSentMessages;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (numberOfStars != user.numberOfStars) return false;
        if (numberOfSentMessages != user.numberOfSentMessages) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (numberOfStars ^ (numberOfStars >>> 32));
        result = 31 * result + (int) (numberOfSentMessages ^ (numberOfSentMessages >>> 32));
        return result;
    }

    public long getNumberOfStars(){
        return numberOfStars;
    }
    public long getNumberOfSentMessages(){
        return numberOfSentMessages;
    }

    public String getName(){
        return name;
    }

}
