package io.zipcoder.iotools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidginzberg on 5/18/16.
 */
public class FileUtils {

    private static String resourceDirectory = "./Resources/";

    public static void main(String[] args) {
        String filename = "testfile.txt";
        try{
            writeToFile(filename, "This is my\nmultiline file output\nmessage.");
        }
        catch (IOException e){
            System.err.println("Something's wrong with the file writer...");
            e.printStackTrace();
        }
        List<String> lines;
        try{
            lines = readLinesFromFile(filename);
        } catch (IOException e){
            System.err.println("Something's wrong with the file reader...");
            e.printStackTrace();
            lines = new ArrayList<>();
        }

        System.out.println("Here are the contents of the file:");
        for(String line : lines){
            System.out.println(line);
        }


    }

    /**
     * Writes a desired string to the specified file inside of the prespecified resource directory
     * @param filename
     * @param contents
     * @throws IOException
     */
    public static void writeToFile(String filename, String contents) throws IOException{
        Files.write(Paths.get(resourceDirectory + filename), contents.getBytes());
    }

    /**
     * returns a List of Strings where each String is a line that was read from the file named in `filename`
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<String> readLinesFromFile(String filename) throws IOException{
        return Files.readAllLines(Paths.get(resourceDirectory + filename), Charset.defaultCharset());
    }
}
