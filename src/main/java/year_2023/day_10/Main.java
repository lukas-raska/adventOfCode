package year_2023.day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //načtení dat ze zdrojového souboru
        Path relativePath = Paths.get("src", "main", "resources", "2023", "input_2023_10.txt");
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

        //hledané výsledky - part one
        int loopLength = 0;
        int farthestPointInLoop;

        //proměnné pomocí kterých se budu pohybovat v bludišti a jejich počáteční nastavení
        //udává pozici bodu
        int x = xS;
        int y = yS;
        //udává směr
        int dy = -1; //od S se vydám směrem nahoru po trubce '|'
        int dx = 0;
        char nextPoint;
        boolean endOfLoop = false;

        //vytvořím labyrint pro vykreslování - pro začátek bude naplněn prázdným znakem a počátečním znakem
        char emptySpace = '░';
        List<char[]> labyrinth = new ArrayList<>();
        for (int i = 0; i < fetchedInput.size(); i++) {
            labyrinth.add(new char[fetchedInput.get(0).length()]);
        }
        for (char[] chars : labyrinth) {
            Arrays.fill(chars, emptySpace);
        }
        labyrinth.get(yS)[xS] = '║';


        //cyklus pro procházení bludištěm potrubí
        // během cyklu počítám délku smyčky a vykresluju labyrint
        while (!endOfLoop) {
            //počítám délku potrubní smyčky
            loopLength++;
            //v každé iteraci aktualizuju souřadnice příštího bodu na základě aktuálního směru potrubí
            x = x + dx;
            y = y + dy;

            nextPoint = fetchedInput.get(y).charAt(x);
            //analyzuju tvar potrubí v příštím bodě
            //pokud narazím na "kolínko", je třeba upravit směry procházení
            //pokud narazím na S, jsem v cíli
            switch (nextPoint) {
                case '|':
                    labyrinth.get(y)[x] = '║';
                    break;
                case '-':
                    labyrinth.get(y)[x] = '═';
                    break;
                case 'L':
                    dx = (dx == 0) ? 1 : 0;
                    dy = (dy == 0) ? -1 : 0;
                    labyrinth.get(y)[x] = '╚';
                    break;
                case 'J':
                    dx = (dx == 0) ? -1 : 0;
                    dy = (dy == 0) ? -1 : 0;
                    labyrinth.get(y)[x] = '╝';
                    break;
                case '7':
                    dx = (dx == 0) ? -1 : 0;
                    dy = (dy == 0) ? 1 : 0;
                    labyrinth.get(y)[x] = '╗';
                    break;
                case 'F':
                    dx = (dx == 0) ? 1 : 0;
                    dy = (dy == 0) ? 1 : 0;
                    labyrinth.get(y)[x] = '╔';
                    break;
                case 'S':
                    endOfLoop = true;
            }
        }

        //ray casting algoritmus - k získání počtu vnitřních bodů smyčky - part two
        //z každého bodu vedu přímku až na konec pole. Pokud počet průsečíků s křivkou je lichý --> bod leží uvnitř
        int innerPoints = 0; //hledaný počet vnitřních bodů
        //postupně pro všechny body
        for (int i = 0; i < labyrinth.size() - 1; i++) {
            for (int j = 0; j < labyrinth.get(i).length - 1; j++) {
                int intersections = 0;
                char verticalPart = '║';
                List<Character> cornerPartsOpening = List.of('╚', '╔');
                List<Character> cornerPartsClosing = List.of('╗', '╝');
                StringBuilder pipeBendShape = new StringBuilder();

                if (labyrinth.get(i)[j] == emptySpace) {
                    //osa X - kladný směr
                    for (int k = j + 1; k < labyrinth.get(i).length; k++) {
                        if (labyrinth.get(i)[k] == verticalPart) {
                            intersections++;
                        }
                        if (cornerPartsOpening.contains(labyrinth.get(i)[k])) {
                            pipeBendShape.append(labyrinth.get(i)[k]);
                        }
                        if (cornerPartsClosing.contains(labyrinth.get(i)[k])) {
                            pipeBendShape.append(labyrinth.get(i)[k]);
                        }
                        if (pipeBendShape.toString().equals("╚╗") || pipeBendShape.toString().equals("╔╝")) { //protíná
                            intersections++;
                            pipeBendShape = new StringBuilder();
                        }
                        if (pipeBendShape.toString().equals("╔╗") || pipeBendShape.toString().equals("╚╝")) { //neprotíná, pouze tečuje
                            pipeBendShape = new StringBuilder();
                        }
                    }
                }
                if (intersections % 2 != 0) {
                    labyrinth.get(i)[j] = 'X';
                    innerPoints++;
                }

            }
        }

        //vykreslení labyrintu
        for (char[] line : labyrinth) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }

        //požadovaný nejvzdálenější bod získám jako polovinu délky smyčky
        farthestPointInLoop = loopLength / 2;

        System.out.println("The answer of day 10: ");
        System.out.println("Part one: " + farthestPointInLoop);
        System.out.println("Part two: " + innerPoints);
    }
}
