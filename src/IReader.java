import java.io.IOException;

public interface IReader {
    public void SetSource(String source);
    public String GetBodyText() throws IOException;
}