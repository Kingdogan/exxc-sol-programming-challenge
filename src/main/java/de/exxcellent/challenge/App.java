package de.exxcellent.challenge;

import de.exxcellent.challenge.tasks.FootballAnalyzer;
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
                executeTask(cmd, "weather");
            }

            // football task
            if (cmd.hasOption("f") || cmd.hasOption("football")) {
                executeTask(cmd, "football");
            }
        }
        catch (ParseException | IOException | URISyntaxException e){
            System.out.println("There has been an error: " + e.getMessage());
        }
    }

    /**
     * Executes the football or weather task based on the provided task name.
     *
     * @param cmd      The parsed CLI arguments.
     * @param taskName The name of the task to be executed ("weather" or "football").
     * @throws IOException        If an I/O error occurs.
     * @throws URISyntaxException If the file URL is not a valid URI.
     * @throws FileNotFoundException If the specified file for the task is not found.
     */
    private static void executeTask(CommandLine cmd, String taskName) throws IOException, URISyntaxException {
        String fileName = cmd.getOptionValue(taskName);
        if (checkIfFileExists(fileName)) {
            List<String[]> data = new CsvReader().readFile(fileName);
            if (taskName.equals("weather")) {
                analyzeWeather(data);
            }
            if (taskName.equals("football")) {
                analyzeFootballTeams(data);
            }
        } else {
            throw new FileNotFoundException("File " + fileName + " not found. Check if file is in the " +
                "resources/de/exxcellent/challenge folder.");
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

    /**
     * Analyzes weather data to find the day with the smallest temperature spread.
     *
     * @param weatherData The data of the weather.
     */
    private static void analyzeWeather(List<String[]> weatherData) {
        String dayWithSmallestTempSpread = WeatherAnalyzer.findSmallestSpread(weatherData);
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
    }

    /**
     * Analyzes football data to find the team with the smallest goal spread.
     *
     * @param footballData The football data.
     */
    private static void analyzeFootballTeams(List<String[]> footballData) {
        String teamWithSmallestGoalSpread = FootballAnalyzer.findSmallestSpread(footballData);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
