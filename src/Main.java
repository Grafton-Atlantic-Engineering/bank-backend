import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* TODO: create CSV reader class
*   TODO: add essential field specification for csv parser
*    TODO: find hash function that produces a digest size & convert from hex to base32 so digest is 32 digits
*     TODO: add linter */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("customers.csv"));
        // set -1 so it captures all empty strings
        String[] schema = csvReader.readLine().split(",", -1);
        for (var title: schema) {
            System.out.print(title + ", ");
        }

        List<String[]> dataRows = new ArrayList<String[]>();
        String row;
        while((row = csvReader.readLine()) != null) {
            // catch the empty strings
            dataRows.add(row.split(",", -1));
        }
        csvReader.close();
    }
}
