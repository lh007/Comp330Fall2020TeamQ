package GeneticsApp;

import static org.junit.Assert.*;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Unit test for ParseFile class.
 */
public class ParseFileTest
{

    @Test
    public void readFileLines(){
        String testFileName = "test";
        ParseFile parser = new ParseFile(testFileName);
        List<String> testData = parser.getData();

        assertEquals(58, testData.size());
    }

    @Test
    public void readFile(){
        String testFileName = "test.txt";
        String testFileName2 = "test";
        ParseFile parser = new ParseFile(testFileName2);
        List<String> testData = parser.getData();

        try {
            List<String> data = Files.readAllLines(Paths.get(testFileName));
            assertEquals(data, testData);
        }
        catch(Exception e){
            fail("test failed: " + e);
        }
    }

    @Test
    public void parseFullPerson(){
        String testFileName = "test";
        Hashtable<String, String> lineHash = new Hashtable<>();
        ArrayList<Hashtable<String,String>> expected = new ArrayList<>();
        lineHash.put("Key", "P1");
        lineHash.put("FamilyName", "Johnson");
        lineHash.put("GivenName", "Dick");
        lineHash.put("Suffix", "Jr");
        lineHash.put("DOB", "9/1/1940");
        lineHash.put("BirthPlace", "Flint");
        lineHash.put("DOD", "12/30/2020");
        lineHash.put("DeathPlace", "Lansing");
        lineHash.put("Parents", "R1");
        expected.add(lineHash);
        ParseFile parser = new ParseFile(testFileName);
        ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
        //P1,Johnson,Dick,Jr,9/1/1940,Flint,12/30/2020,Lansing,R1
        assertEquals(expected.get(0),peopleList.get(0));
    }

    @Test
    public void parsePartialPerson(){
        String testFileName = "test";
        Hashtable<String, String> lineHash = new Hashtable<>();
        ArrayList<Hashtable<String,String>> expected = new ArrayList<>();
        lineHash.put("Key", "P2");
        lineHash.put("FamilyName", "Johnson");
        lineHash.put("GivenName", "Jane Sarah");
        lineHash.put("Suffix", "");
        lineHash.put("DOB", "6/15/1942");
        lineHash.put("BirthPlace", "Saginaw");
        lineHash.put("DOD", "");
        lineHash.put("DeathPlace", "");
        lineHash.put("Parents", "R1");
        expected.add(lineHash);
        ParseFile parser = new ParseFile(testFileName);
        ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
        //P2,Johnson,Jane Sarah,,6/15/1942,Saginaw,,,R1
        assertEquals(expected.get(0),peopleList.get(1));
    }

    @Test
    public void parseMoreEmptyPerson(){
        String testFileName = "test";
        Hashtable<String, String> lineHash = new Hashtable<>();
        ArrayList<Hashtable<String,String>> expected = new ArrayList<>();
        lineHash.put("Key", "P16");
        lineHash.put("FamilyName", "Smith");
        lineHash.put("GivenName", "John J");
        lineHash.put("Suffix", "");
        lineHash.put("DOB", "");
        lineHash.put("BirthPlace", "");
        lineHash.put("DOD", "");
        lineHash.put("DeathPlace", "");
        lineHash.put("Parents", "R7");
        expected.add(lineHash);
        ParseFile parser = new ParseFile(testFileName);
        ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
        //P16,Smith,John J,,,,,,R7
        assertEquals(expected.get(0),peopleList.get(10));
    }

    @Test
    public void parseRelationship(){
        String testFileName = "test";
        Hashtable<String, String> lineHash = new Hashtable<>();
        ArrayList<Hashtable<String,String>> expected = new ArrayList<>();
        lineHash.put("Key", "R1");
        lineHash.put("MaleParent", "P7");
        lineHash.put("FemaleParent", "P6");
        lineHash.put("StartDate", "6/7/1938");
        lineHash.put("EndDate", "");
        lineHash.put("Location", "St Matthew's Flint Michigan");
        expected.add(lineHash);
        ParseFile parser = new ParseFile(testFileName);
        ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
        //R1,P7,P6,6/7/1938,,St Matthew's Flint Michigan,,,
        assertEquals(expected.get(0),relationshipList.get(0));
    }

    @Test
    public void parsePartialRelationship(){
        String testFileName = "test";
        Hashtable<String, String> lineHash = new Hashtable<>();
        ArrayList<Hashtable<String,String>> expected = new ArrayList<>();
        lineHash.put("Key", "R6");
        lineHash.put("MaleParent", "");
        lineHash.put("FemaleParent", "P1");
        lineHash.put("StartDate", "");
        lineHash.put("EndDate", "");
        lineHash.put("Location", "");
        expected.add(lineHash);
        ParseFile parser = new ParseFile(testFileName);
        ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
        //R6,,P1,,,,,,
        assertEquals(expected.get(0),relationshipList.get(3));
    }

    @Test
    public void parseChild(){
        String testFileName = "test";
        Hashtable<String, String> lineHash = new Hashtable<>();
        ArrayList<Hashtable<String,String>> expected = new ArrayList<>();
        lineHash.put("Partnership", "R1");
        lineHash.put("Child", "P1");
        expected.add(lineHash);
        ParseFile parser = new ParseFile(testFileName);
        ArrayList<Hashtable<String,String>> childList = parser.getParsedChild();
        //R1,P1,,,,,,,
        assertEquals(expected.get(0),childList.get(0));
    }



}
