package year_2015.day_5;

public class Main {

    private static final String[] TEST_DATA = {"ugknbfddgicrmopn", "jchzalrnumimnmhp", "haegwjzuvuyypxyu",
            "dvszwmarrgswjxmb"};

    public static void main(String[] args) {

        SantaStringsChecker checker = new SantaStringsChecker();

        for (String data : TEST_DATA) {
            System.out.println(data + " is nice? : " + checker.isNice(data));
        }


    }
}
