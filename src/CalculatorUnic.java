import java.util.TreeMap;

public class CalculatorUnic implements ICalculatorUnic {
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