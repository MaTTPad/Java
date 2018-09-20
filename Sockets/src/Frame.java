
import java.io.Serializable;

public abstract class Frame implements Serializable
{
    public enum FrameType
    {
        Login,
        Disconnect,
        Message,
        Result
    }

    private static final long serialVersionUID = 3851837710608297424L;

    private FrameType type;

    public FrameType getType()
    {
        return this.type;
    }

    public void setType( FrameType type )
    {
        this.type = type;
    }
}
