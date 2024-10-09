package bookstore.util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseCsvUtil {
    public static List<String> parseFileAsListOfLines(URL inputStream) {
        ArrayList<String> lines = new ArrayList<>();
        File csv = new File(inputStream.getFile());
        try {
            FileInputStream fileInputStream = new FileInputStream(csv);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
