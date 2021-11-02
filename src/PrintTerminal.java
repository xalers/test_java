

public class PrintTerminal implements IPrintTerminal {
    public PrintTerminal() {
    }

    public void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}