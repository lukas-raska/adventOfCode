package year_2025.day_11;

public record Connection(String input, String[] outputs) {

    public static Connection parseConnection(String data) {

        String input = data.substring(0, data.indexOf(":"));
        String[] outputs = data.substring(data.indexOf(":") + 1).trim().split(" ");

        return new Connection(input, outputs);
    }
}
