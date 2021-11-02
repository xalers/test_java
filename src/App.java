
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class App {

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