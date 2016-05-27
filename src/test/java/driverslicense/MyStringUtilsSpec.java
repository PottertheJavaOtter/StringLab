package driverslicense;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by minlee on 5/22/16.
 */
public class MyStringUtilsSpec {


    @Test
    public void combineArrayIntoOneStringTest() throws Exception {
        String[] animals = {"dog","cat","mouse"};
        String expectedString = "dog,cat,mouse";
        String actualString = MyStringUtils.arrayToString(animals);
        assertEquals(expectedString,actualString);
    }

    @Test
    public void separateStringIntoArrayTest() throws Exception {
        String animals = "dog\ncat\nmouse";
        String[] expectedStringArray = {"dog","cat","mouse"};
        String[] actualStringArray = MyStringUtils.stringToArrayOfStrings(animals);
        assertArrayEquals(expectedStringArray,actualStringArray);
    }

    @Test
    public void reverseCapitalizeTest() throws Exception {
        String animals = "dog Cat mouse chicken snake";
        String expectedString = "dOG cAT mOUSE cHICKEN sNAKE";
        String actualString = MyStringUtils.reverseCapitalizeString(animals);
        assertEquals(expectedString,actualString);
    }

    @Test
    public void reverseWordTest() throws Exception {
        String animals = "dog Cat mouse chicken snake";
        String expectedString = "god taC esuom nekcihc ekans";
        String actualString = MyStringUtils.reverseSpellingOfString(animals);
        assertEquals(expectedString,actualString);
    }

    @Test
    public void trimToNewLineTest() throws Exception {
        String animals = "dog cat mouse";
        String [] expectedString = {"d","do","dog","o","og","g","c","ca","cat","a","at","t","m","mo","mou","mous","mouse","o","ou","ous","ouse","u","us","use","s","se","e"};
        String [] actualString = MyStringUtils.getArrayOfAllSubstrings(animals);
        assertArrayEquals(expectedString,actualString);
    }
}