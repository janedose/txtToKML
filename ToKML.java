import java.util.Scanner;
import java.io.*;
/**
 *This program converts coordinate data from .txt input file to .kml format.
 */
public class ToKML {
    public static final int ALTITUDE = 0;
    /**
     * Main method to generate a .kml output file based on existing .txt input file.
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) 
            throws FileNotFoundException {
        Scanner console = new Scanner (System.in);
        Scanner input = getInput(console);
        outFile(input);
    }
    /**
     * This method prompts user for the name of the input file.
     * @param console The Scanner object to retrieve user inputs
     * @return scanner for file
     * @throws FileNotFoundException 
     */
    public static Scanner getInput (Scanner console)
            throws FileNotFoundException {
        System.out.println("What is your input file name?");
        File f = new File (console.nextLine());
        while (!f.canRead()) {
            System.out.println("File not found. Try again.");
            System.out.println("What is your input file name?");
            f = new File (console.nextLine());
        }
        return new Scanner (f);
    }
    /**
     * This method generates a .kml output file.
     * @param input The data from input file
     * @throws FileNotFoundException 
     */
    public static void outFile(Scanner input) 
            throws FileNotFoundException {
        PrintStream myOutfile = new PrintStream(new File("KMLFile.kml"));
        myOutfile.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://earth.google.com/kml/2.1\">\n" +
            "<Document>\n" +
            "\t<name>Mystery Locations</name> ");
        int count = 0;
        while (input.hasNextLine()) {
            count++;
        String line = input.nextLine();
        Scanner lineScan = new Scanner (line);
        double longitude = lineScan.nextDouble();
        double latitude = lineScan.nextDouble();
        myOutfile.println("\t<Placemark>\n" +
            "\t  <name>Location " + count + "</name>\n" +
            "\t <Point>\n" +
            "\t\t<coordinates>" + longitude + "," + latitude + "," + ALTITUDE + "</coordinates>\n" +
            "\t </Point>\n" +
            "\t</Placemark>");
        }
        myOutfile.println("</Document>\n" + 
            "</kml>");
    } 
}