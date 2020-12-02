package GeneticsApp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ParseFile {

    String file; //name of file to parse, prob want it to look for the csv file too
    ArrayList<Hashtable<String,String>> parsedPerson = new ArrayList<>();
    ArrayList<Hashtable<String,String>> parsedRelationship = new ArrayList<>();
    ArrayList<Hashtable<String,String>> parsedChild = new ArrayList<>();
    List<String> data;

    public ParseFile(String fileName){
        file = fileName + ".txt";
        readAndParse();
    }
//makes list of lines from input file
//one for loop needed. Did this to keep the time down for really big files. Might need tweaking later
//linesplit splits the line into an array where it's split up by the "," if it is empty it will still hold an empty string at that spot of the array
//swap is to swap between the templates of the lines, if it is a person it will swap to that hashtable keys and same with the oters
//the last 3 ifs make sure to not parse the headers of the sections, will add the appropriate strings from linesplit to the hashtable
//used try and catch for errors
//linehash will then be added to the appropriate arraylist for use with the graph
//get methods just to get those arraylists
    public void readAndParse() {
        try{

            data = Files.readAllLines(Paths.get(file));
            int swap = 0;
            for (String datum : data) {
                Hashtable<String, String> lineHash = new Hashtable<>();
                String[] lineSplit = datum.split(",", -1);

                switch (lineSplit[0]) {
                    case "Person":
                         swap = 1;
                     break;
                    case "Partnership":
                        swap = 2;
                     break;
                    case "Children":
                        swap = 3;
                     break;
                    case "":
                        swap = 0;
                     break;
                }

                if (swap == 1 && !lineSplit[0].equals("Person")) {
                    lineHash.put("Key", lineSplit[0]);
                    lineHash.put("FamilyName", lineSplit[1]);
                    lineHash.put("GivenName", lineSplit[2]);
                    lineHash.put("Suffix", lineSplit[3]);
                    lineHash.put("DOB", lineSplit[4]);
                    lineHash.put("BirthPlace", lineSplit[5]);
                    lineHash.put("DOD", lineSplit[6]);
                    lineHash.put("DeathPlace", lineSplit[7]);
                    lineHash.put("Parents", lineSplit[8]);
                    parsedPerson.add(lineHash);
                } else if (swap == 2 && !lineSplit[0].equals("Partnership")) {

                    lineHash.put("Key", lineSplit[0]);
                    lineHash.put("MaleParent", lineSplit[1]);
                    lineHash.put("FemaleParent", lineSplit[2]);
                    lineHash.put("StartDate", lineSplit[3]);
                    lineHash.put("EndDate", lineSplit[4]);
                    lineHash.put("Location", lineSplit[5]);
                    parsedRelationship.add(lineHash);
                } else if (swap == 3 && !lineSplit[0].equals("Children")) {
                    //do children stuff
                    lineHash.put("Partnership", lineSplit[0]);
                    lineHash.put("Child", lineSplit[1]);
                    parsedChild.add(lineHash);
                }

            }

        }
        catch(Exception e){
            System.out.println("wtf happened with parsing" + e);
        }

    }


    public ArrayList<Hashtable<String,String>> getParsedPeople(){
        return this.parsedPerson;
    }
    
    public ArrayList<Hashtable<String,String>> getParsedRelationship(){
        return this.parsedRelationship;
    }

    public ArrayList<Hashtable<String,String>> getParsedChild(){
        return this.parsedChild;
    }

    public List<String> getData(){return this.data;}


} 
