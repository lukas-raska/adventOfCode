package year_2023.day_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static Path relativePath = Paths.get("src", "main", "resources", "2023", "input_2023_5.txt");

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        //načtení souboru a uložení do Listu
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(relativePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        //definice potřebných proměnných pro uložení dat
        List<Long> seeds = new ArrayList<>();
        List<MyMap> seedToSoilMapList = new ArrayList<>();
        List<MyMap> soilToFertilizerMapList = new ArrayList<>();
        List<MyMap> fertilizerToWaterMapList = new ArrayList<>();
        List<MyMap> waterToLightMapList = new ArrayList<>();
        List<MyMap> lightToTemperatureMapList = new ArrayList<>();
        List<MyMap> temperatureToHumidityMapList = new ArrayList<>();
        List<MyMap> humidityToLocationMapList = new ArrayList<>();

        List<SeedRange> seedsForPartTwo = new ArrayList<>();

        //logické proměnné sloužící k "přepínání", do kterého Listu ukládat
        boolean seedToSoilFollows = false;
        boolean soilToFertilizerFollows = false;
        boolean fertilizerToWaterFollows = false;
        boolean waterToLightFollows = false;
        boolean lightToTemperatureFollows = false;
        boolean temperatureToHumidityFollows = false;
        boolean humidityToLocationFollows = false;

        //cyklem projedu vstupní data a "rozhodím" do dílčích seznamů dle druhu dat
        for (String line : lines) {

            if (line.isEmpty()) {
                seedToSoilFollows = false;
                soilToFertilizerFollows = false;
                fertilizerToWaterFollows = false;
                waterToLightFollows = false;
                lightToTemperatureFollows = false;
                temperatureToHumidityFollows = false;
                humidityToLocationFollows = false;
                continue;
            }

            if (line.contains("seeds")) {

                line = line.substring(6).trim();

                // zpracování dat pro part one
                StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
                while (stringTokenizer.hasMoreElements()) {
                    seeds.add(Long.parseLong(stringTokenizer.nextToken()));
                }
                //zpracování pro part two
                stringTokenizer = new StringTokenizer(line, " ");
                while (stringTokenizer.hasMoreElements()) {
                    seedsForPartTwo
                            .add(new SeedRange(
                                    Long.parseLong(stringTokenizer.nextToken()),
                                    Long.parseLong(stringTokenizer.nextToken()))
                            );
                }
                continue;
            }
            if (line.contains("seed-to-soil")) {
                seedToSoilFollows = true;
                continue;
            }
            if (line.contains("soil-to-fertilizer")) {
                soilToFertilizerFollows = true;
                continue;
            }
            if (line.contains("fertilizer-to-water")) {
                fertilizerToWaterFollows = true;
                continue;
            }
            if (line.contains("water-to-light")) {
                waterToLightFollows = true;
                continue;
            }
            if (line.contains("light-to-temperature")) {
                lightToTemperatureFollows = true;
                continue;
            }
            if (line.contains("temperature-to-humidity")) {
                temperatureToHumidityFollows = true;
                continue;
            }
            if (line.contains("humidity-to-location")) {
                humidityToLocationFollows = true;
                continue;
            }
            if (seedToSoilFollows) {
                seedToSoilMapList.add(new MyMap(line));
                continue;
            }
            if (soilToFertilizerFollows) {
                soilToFertilizerMapList.add(new MyMap(line));
                continue;
            }
            if (fertilizerToWaterFollows) {
                fertilizerToWaterMapList.add(new MyMap(line));
                continue;
            }
            if (waterToLightFollows) {
                waterToLightMapList.add(new MyMap(line));
                continue;
            }
            if (lightToTemperatureFollows) {
                lightToTemperatureMapList.add(new MyMap(line));
                continue;
            }
            if (temperatureToHumidityFollows) {
                temperatureToHumidityMapList.add(new MyMap(line));
                continue;
            }
            if (humidityToLocationFollows) {
                humidityToLocationMapList.add(new MyMap(line));
            }
        }

        //mapování z seeds to Location
        long soilNumber, fertilizerNumber, waterNumber, lightNumber, temperatureNumber, humidityNumber, locationNumber;
        List<Long> locationsList = new ArrayList<>();

        //part one - OK
        for (long seed : seeds) {
            soilNumber = mapper(seed, seedToSoilMapList);
            fertilizerNumber = mapper(soilNumber, soilToFertilizerMapList);
            waterNumber = mapper(fertilizerNumber, fertilizerToWaterMapList);
            lightNumber = mapper(waterNumber, waterToLightMapList);
            temperatureNumber = mapper(lightNumber, lightToTemperatureMapList);
            humidityNumber = mapper(temperatureNumber, temperatureToHumidityMapList);
            locationNumber = mapper(humidityNumber, humidityToLocationMapList);
            locationsList.add(locationNumber);
        }

        //part two - nutno předělat --
        long locationMinimumPartTwo = Long.MAX_VALUE;

        for (SeedRange seedRange : seedsForPartTwo) {



        }


//            neefektivní NEHLEDAT CELÝM CYKLEM,ALE POUZE V HRANIČNÍCH BODECH!
           /* Long currentMinimum = LongStream.range(seedRange.getStartOfRange(), seedRange.getEndOfRange())
                    .map(seed -> mapper(seed, seedToSoilMapList))
                    .map(soil -> mapper(soil, soilToFertilizerMapList))
                    .map(fertilizer -> mapper(fertilizer, fertilizerToWaterMapList))
                    .map(water -> mapper(water, waterToLightMapList))
                    .map(light -> mapper(light, lightToTemperatureMapList))
                    .map(temperature -> mapper(temperature, temperatureToHumidityMapList))
                    .map(humidity -> mapper(humidity, humidityToLocationMapList))
                    .min()
                    .getAsLong();*/

            /*if (currentMinimum<locationMinimumPartTwo){
                locationMinimumPartTwo=currentMinimum;
            }*/

        System.out.println("\nThe answer of day 5");
        System.out.println("Part one: " + locationsList.stream().min(Long::compareTo).get());
        System.out.println("Part two: " + locationMinimumPartTwo);

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Doba běhu: " + duration.toMinutesPart() + ":" + duration.toSecondsPart() + " min");
    }

    public static long mapper(long sourceNumber, List<MyMap> sourceToTargetMapList) {
        long targetNumber = sourceNumber;
        for (MyMap myMap : sourceToTargetMapList) {
            if (sourceNumber >= myMap.getSourceRangeStart() && sourceNumber < myMap.getSourceRangeEnd()) {
                Long dif = sourceNumber - myMap.getSourceRangeStart();
                targetNumber = myMap.getDestinationRangeStart() + dif;
                return targetNumber;
            }
        }
        return targetNumber;
    }


}
