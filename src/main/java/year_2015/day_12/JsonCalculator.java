package year_2015.day_12;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonCalculator {
//    private final String jsonInput;
//
//    private final ObjectMapper objectMapper;
//
//    public JsonCalculator(String jsonInput) {
//        this.jsonInput = jsonInput;
//        this.objectMapper = new ObjectMapper();
//    }

//    public static int sumAllNumbers() {
//        int sum = 0;
//        Pattern numbersPattern = Pattern.compile("-?\\d+");
//        Matcher numbersMatcher = numbersPattern.matcher(this.input);
//        while (numbersMatcher.find()) {
//            sum += Integer.parseInt(numbersMatcher.group());
//        }
//        return sum;
//    }

    public static int calculateSum(JsonNode node) {
        return calculateSum(node, null);
    }

    public static int calculateSum(JsonNode node,
                                   String excludedValue) {

        int sum = 0;

        if (node.canConvertToInt()) {
            return node.asInt();
        }

        if (node.isObject()) {
            if (excludedValue != null) {
                if (containsValue(node, excludedValue)) {
                    return 0;
                }
            }
            var elements = node.elements();
            while (elements.hasNext()) {
                var next = elements.next();
                sum += calculateSum(next, excludedValue);
            }
        }
        if (node.isArray()) {
            for (var element : node) {
                sum += calculateSum(element, excludedValue);
            }
        }
        return sum;
    }


    public static boolean containsValue(JsonNode node,
                                        String searchedValue) {
        var elements = node.elements();
        while (elements.hasNext()) {
            var next = elements.next();
            if (next.isTextual() && next.asText().equals(searchedValue)) {
                return true;
            }
        }
        return false;
    }
}




