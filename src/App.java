
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class App {

    public interface IPrintTerminal {
        public void print(String msg, Object... args);
    }
    public static class PrintTerminal implements IPrintTerminal {
        public PrintTerminal() {
        }

        public void print(String msg, Object... args) {
            System.out.println(String.format(msg, args));
        }
    }

    public interface IReader {
        public void SetSource(String source);
        public String GetBodyText() throws IOException;
    }
    public static class ReaderHttp implements IReader {
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

    public interface ICalculatorUnic {
        public void SetText(String _text);
        public TreeMap<String, Integer> Calculate();
    }
    public static class CalculatorUnic implements ICalculatorUnic {
        public CalculatorUnic() {
            printTerminal = new PrintTerminal();
        }

        private PrintTerminal printTerminal;
        private String text;
        private String regExpSplit = "[\s|,|\\.|!|?|\"|;|:|\\[|\\]|(|)|\n|\r|\t]";

        public void SetText(String _text) {
            text = _text;
        }

        private String[] SplitText(String _text) {
            printTerminal.print("Split...");
            String[] worldsArray = new String[] {};
            worldsArray = _text.split(regExpSplit);
            return worldsArray;
        }

        public TreeMap<String, Integer> Calculate() {
            printTerminal.print("Calculate...");
            String[] worldsArray = SplitText(text);
            TreeMap<String, Integer> map = new TreeMap<String, Integer>();
            for (String world : worldsArray) {
                if (map.containsKey(world.toUpperCase())) {
                    Integer count = map.get(world.toUpperCase());
                    count++;
                    map.replace(world.toUpperCase(), count);
                } else {
                    map.put(world.toUpperCase(), 1);
                }
            }
            return map;
        }

    }

    public static void main(String[] args) throws IOException {
        PrintTerminal printTerminal = new PrintTerminal();
        IReader reader = new ReaderHttp();
        ICalculatorUnic calculater = new CalculatorUnic();

        Scanner sc = new Scanner(System.in);
        printTerminal.print("Выбрать url страницы для анализа: 1: https://www.simbirsoft.com, 2: Ввести свой, 3: Выход");
        int urlType = sc.nextInt();
        String url = "";
        if (urlType == 1) {
            url = "https://www.simbirsoft.com";
        } else if (urlType == 2) {
            url = sc.nextLine();
        } else {
            sc.close();
            return;
        }
        sc.close();

        reader.SetSource(url);
        String text = reader.GetBodyText();
        calculater.SetText(text);
        TreeMap<String, Integer> map = calculater.Calculate();
        map.forEach((key, value) -> printTerminal.print(" Слово: %s - %s", key, value));
        printTerminal.print(" Всего уникальных слов: %s", map.size());
    }
}