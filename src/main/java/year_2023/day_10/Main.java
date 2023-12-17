package year_2023.day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Path relativePath = Paths.get("src", "main", "java", "year_2023", "day_10", "input.txt");

        //načtení dat ze zdrojového souboru
        List<String> fetchedInput = new ArrayList<>();
        try {
            fetchedInput = Files.readAllLines(relativePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        //nalezení počátku smyčky, písmene S
        int xS = 0;
        int yS = 0;
        for (int i = 0; i < fetchedInput.size(); i++) {
            for (int j = 0; j < fetchedInput.get(i).length(); j++) {
                if (fetchedInput.get(i).charAt(j) == 'S') {
                    xS = j;
                    yS = i;
                    break;
                }
            }
        }


        //hledané výsledky
        int loopLength = 0;
        int farthestPointInLoop;

        //proměnné pomocí kterých se budu pohybovat v bludišti a jejich počáteční nastavení
        char currentPoint = fetchedInput.get(yS).charAt(xS);
        int x = xS;
        int y = yS;
        int dy = -1; //od S se vydám směrem nahoru po trubce '|'
        int dx = 0;
        char nextPoint;
        boolean endOfLoop = false;

        //cyklus pro procházení bludištěm potrubí
        while (!endOfLoop) {
            //počítám délku potrební smyčky
            loopLength++;
            //v každé iteraci aktualizuju souřadnice příštího bodu na základě aktuálního směru potrubí
            x = x + dx;
            y = y + dy;
            nextPoint = fetchedInput.get(y).charAt(x);
            //analyzuju tvar potrubí v příštím bodě
            //pokud narazím na "roháčky", je třeba upravit směry procházení
            //pokud narazím na S, jsem v cíli
            switch (nextPoint) {
                case 'L':
                    dx = (dx == 0) ? 1 : 0;
                    dy = (dy == 0) ? -1 : 0;
                    break;
                case 'J':
                    dx = (dx == 0) ? -1 : 0;
                    dy = (dy == 0) ? -1 : 0;
                    break;
                case '7':
                    dx = (dx == 0) ? -1 : 0;
                    dy = (dy == 0) ? 1 : 0;
                    break;
                case 'F':
                    dx = (dx == 0) ? 1 : 0;
                    dy = (dy == 0) ? 1 : 0;
                    break;
                case 'S':
                    endOfLoop = true;
            }
        }
        //požadovaný nejvzdálenější bod získám jako polovinu délky smyčky
        farthestPointInLoop = loopLength / 2;

        System.out.println("The answer of day 10: ");
        System.out.println("Part one: " + farthestPointInLoop);
    }
}
