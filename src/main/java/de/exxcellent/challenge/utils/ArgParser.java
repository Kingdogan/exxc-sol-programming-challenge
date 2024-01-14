package de.exxcellent.challenge.utils;

import org.apache.commons.cli.*;

/**
 * This class is used to parse the CLI arguments
 *
 * @author Öner Aydogan
 */
public class ArgParser {

  /**
   * This function parses the command line arguments
   * @param args The CLI arguments
   * @return The parsed CLI arguments
   */
  public static CommandLine parseArgs(String... args) {
    CommandLineParser parser = new DefaultParser();
    Options options = new Options();

    Option weatherTask = new Option(
        "w",
        "weather",
        true,
        "Executes the weather task which reads the given input file and outputs the day with the " +
            "smallest temperature spread.");
    weatherTask.setArgName("inputFile");
    options.addOption(weatherTask);

    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println("Error parsing command-line arguments: " + e.getMessage());
      System.exit(1);
    }
    return cmd;
  }
}
