import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ReaderHttp implements IReader {
    public ReaderHttp() {
        printTerminal = new PrintTerminal();
    }

    private String urlPage;
    private PrintTerminal printTerminal;

    public void SetSource(String source) {
        urlPage = source;
    }

    public String GetBodyText() throws IOException {
        printTerminal.print("Fetching %s...", urlPage);
        String text = "";
        Document doc = Jsoup.parse(Jsoup.connect(urlPage).get().html());
        text = doc.body().text();
        return text;
    }

}