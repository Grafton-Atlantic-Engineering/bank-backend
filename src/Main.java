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

        Map<Integer, ArrayList<String[]>> lengthPositions = new HashMap<Integer, ArrayList<String[]>>();

        StringBuilder interpol;
        for (String[] dataRow : dataRows) {
            // to keep track of the various lengths
            if (!lengthPositions.containsKey(dataRow.length)) {
                lengthPositions.put(dataRow.length, new ArrayList<String[]>());
            }
            lengthPositions.get(dataRow.length).add(dataRow);

            interpol = new StringBuilder();
            for (int k = 0; k < schema.length; k++) {
                interpol.append('\t');
                interpol.append(schema[k]);
                interpol.append(": ");
                interpol.append(dataRow[k]);
                interpol.append('\n');
            }
            System.out.println(interpol);
            // send to the GC
            interpol = null;
        }
    }
}
