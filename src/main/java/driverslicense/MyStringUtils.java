package driverslicense;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by minlee on 5/22/16.
 */
public class MyStringUtils {

    //1. Combine an array of characters or Strings into one String, separated by commas
    public static String arrayToString(String[] stringArray){
        StringBuilder stringBuilder = new StringBuilder();
        for(String entry : stringArray){
            stringBuilder.append(entry);
            stringBuilder.append(",");
        }
        return stringBuilder.toString().substring(0,stringBuilder.length()-1);
    }

//    2. Break a String up into an array of Strings, one for each line in the original String
    public static String[] stringToArrayOfStrings(String stringToArray){
        return stringToArray.split("\n");
    }
//    3. Given a string, rEVERSE-cAPITALIZE eVERY wORD iN tHE sTRING aND rETURN tHE rESULT.
    public static String reverseCapitalizeString(String reverseCapitalize){
       return WordUtils.uncapitalize(reverseCapitalize.toUpperCase(),' ');
    }
//    4. Given a String, reverse each word in the string and return the result.
    public static String reverseSpellingOfString(String stringToReverseWord){
        String[] stringArray = stringToReverseWord.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<stringArray.length;i++){
            stringBuilder.append(new StringBuilder(stringArray[i]).reverse().toString()).append(" ");
        }
        return stringBuilder.toString().trim();
    }
//    5. Given a String, remove all whitespace and return a string containing each word from the original string on
//    a separate line
    public static String separateEachWordOnNewLine(String stringToTrimToNewLine){
        String[] stringArray = stringToTrimToNewLine.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<stringArray.length;i++){
            if(i != stringArray.length-1){
                stringBuilder.append(stringArray[i]).append("\n");
            }
            else {
                stringBuilder.append(stringArray[i]);
            }
        }
        return stringBuilder.toString();
    }
//    6. Given a string, return an array of every possible substring (every sequence of consecutive characters
//            within the string)
    public static String[] getArrayOfAllSubstrings(String stringToConsecutiveChars){
        String[] stringArray = stringToConsecutiveChars.split(" ");
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i  = 0 ; i < stringArray.length ; i++){


            for(int k = 0 ; k < stringArray[i].length()-1; k++){
                for(int j = k+1; j < stringArray[i].length()+1; j++){
                    stringArrayList.add(stringArray[i].substring(k,j));
                }
            }
            stringArrayList.add(stringArray[i].charAt(stringArray[i].length()-1)+"");
        }
        String[] consecutiveCharsArray = new String[stringArrayList.size()];
        return stringArrayList.toArray(consecutiveCharsArray);
    }
//    7. (Optional Challenge:) Given an array of Strings, return a string containing each array element on a new
//    line, right justified, truncated to no more than 80 characters, followed by a hexadecimal representation of the number of characters in the original string (you should have two columns of text; headings are optional).

}
