package year_2023.day_7;

import java.util.*;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {
    private String cards;
    private int bid;
    private int handTypeValue;
    private static boolean partTwo;
    public static String cardHierarchy;

    public Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        Hand.partTwo = false;
        Hand.cardHierarchy = "23456789TJQKA";
    }

    public int getCardTypeValue() {
        return switch (this.getCardFrequencies()) {
            case ("11111") -> 1; //high card
            case ("11122") -> 2; //one pair
            case ("12222") -> 3; //two pairs
            case ("11333") -> 4; //three of a kind
            case ("22333") -> 5; //full house
            case ("14444") -> 6; // four of a kind
            case ("55555") -> 7; //five of a kind
            default -> 0; // no match
        };
    }

    public String getCardFrequencies() {

        List<Character> cardsCharacterList = this.cards.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
        List<Integer> cardFrequency = new ArrayList<>();

        if (Hand.isPartTwo() && this.cards.contains("J")) {

            Map<Character, Integer> cardFrequenciesMap = new HashMap<>();

            //odstraním z ruky jokery a analyzuji zbytek karet
            List<Character> cardsWithoutJokers = cardsCharacterList.stream().filter(c -> c != 'J').toList();
            if (!cardsWithoutJokers.isEmpty()) {
                for (Character card : cardsWithoutJokers) {
                    cardFrequenciesMap.put(card, Collections.frequency(cardsWithoutJokers, card));
                }
                int maxNonJokerCardFrequency = Collections.max(cardFrequenciesMap.values());
                //najdu nejvíce zastoupenou kartu v ruce
                Character highestNonJokerCard = cardFrequenciesMap.entrySet().stream()
                        .filter(entry -> entry.getValue() == maxNonJokerCardFrequency)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .get();
                //a nahradím ji žulíkem
                Collections.replaceAll(cardsCharacterList, highestNonJokerCard, 'J');
            }

        }
        // projdu jednotlivé karty a přiřadím k nim  jejich frekvenci
        for (Character card : cardsCharacterList) {
            cardFrequency.add(Collections.frequency(cardsCharacterList, card));
        }
        Collections.sort(cardFrequency);
        String output = "";
        for (Integer freq : cardFrequency) {
            output += freq;
        }

        return output;
    }


    public int getBid() {
        return bid;
    }

    public static boolean isPartTwo() {
        return partTwo;
    }

    public static void setPartTwo(boolean partTwo) {
        Hand.partTwo = partTwo;
    }

    public static void setCardHierarchy(String cardHierarchy) {
        Hand.cardHierarchy = cardHierarchy;
    }

    //metoda k porovnávání hodnoty karet
    @Override
    public int compareTo(Hand o) {
        if (this.getCardTypeValue() > o.getCardTypeValue()) {
            return 1;
        } else if (this.getCardTypeValue() < o.getCardTypeValue()) {
            return -1;
        } else {
            for (int i = 0; i < cards.length(); i++) {
                if (cardHierarchy.indexOf(String.valueOf(this.cards.charAt(i))) >
                        cardHierarchy.indexOf(String.valueOf(o.cards.charAt(i)))) {
                    return 1;
                } else if (cardHierarchy.indexOf(String.valueOf(this.cards.charAt(i))) <
                        cardHierarchy.indexOf(String.valueOf(o.cards.charAt(i)))) {
                    return -1;
                }
            }
            return 0;
        }

    }

    @Override
    public String toString() {
        return this.cards + " " + this.bid;
    }
}
