package de.exxcellent.challenge.utils.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface FileReader {
  List<String[]> readFile(String fileName) throws IOException, URISyntaxException;
}
