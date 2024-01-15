package de.exxcellent.challenge;

import de.exxcellent.challenge.tasks.WeatherAnalyzer;
import de.exxcellent.challenge.utils.ArgParser;
import de.exxcellent.challenge.utils.io.CsvReader;
import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        try {
            CommandLine cmd = ArgParser.parseArgs(args);

            // weather task
            if (cmd.hasOption("w") || cmd.hasOption("weather")) {
                String fileName = cmd.getOptionValue("weather");
                if (checkIfFileExists(fileName)) {
                    analyzeWeather(fileName);
                } else {
                    throw new FileNotFoundException("File " + fileName + " not found. Check if file is in the " +
                        "resources/de/exxcellent/challenge folder.");
                }
            }

            String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
        }
        catch (ParseException | IOException | URISyntaxException e){
            System.out.println("There has been an error: " + e.getMessage());
        }
    }

    /***
     * Checks if the given file exists in the resources/de/exxcellent/challenge folder
     * @param fileName The name of the file that is checked
     * @return true if file exists
     */
    private static boolean checkIfFileExists(String fileName){
        URL fileURL = App.class.getClassLoader().getResource("de/exxcellent/challenge/" + fileName);
        return fileURL != null;
    }

    private static void analyzeWeather(String fileName) throws IOException, URISyntaxException {
        List<String[]> weatherData = new CsvReader().readFile(fileName);
        String dayWithSmallestTempSpread = WeatherAnalyzer.findDayWithSmallestTemperatureSpread(weatherData);
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
    }

}
