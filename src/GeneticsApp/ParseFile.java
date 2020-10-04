package GeneticsApp;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ParseFile {
    String file = "FamilyTreeInputTextFile.txt";
    ArrayList<Hashtable<String,String>> parsedPerson = new ArrayList<>();
    ArrayList<Hashtable<String,String>> parsedRelationship = new ArrayList<>();
    ArrayList<Hashtable<String,String>> parsedChild = new ArrayList<>();

    public ParseFile(){
        readAndParse();
    }

    public void readAndParse() {
        try{

            List<String> data = Files.readAllLines(Paths.get(file));
            int swap = 0;
            for(int i=0; i < data.size(); i++){
                Hashtable <String, String> lineHash = new  Hashtable <String, String>();
                String[] lineSplit = data.get(i).split(",",-1);

                if(lineSplit[0].equals("Person")){
                    swap=1;
                }
                else if(lineSplit[0].equals("Partnership")){
                    swap=2;
                }

                else if(lineSplit[0].equals("Children")){
                    swap=3;
                }

                else if(lineSplit[0].equals("")){
                    swap=0;
                }

                if(swap==1){
                    lineHash.put("Key", lineSplit[0]);
                    lineHash.put("FamilyName", lineSplit[1]);

                    System.out.println(lineSplit[0]);

                    lineHash.put("GivenName", lineSplit[2]);
                    lineHash.put("Suffix", lineSplit[3]);
                    lineHash.put("DOB", lineSplit[4]);
                    lineHash.put("BirthPlace", lineSplit[5]);
                    lineHash.put("DOD", lineSplit[6]);
                    lineHash.put("DeathPlace", lineSplit[7]);
                    lineHash.put("Parents", lineSplit[8]);
                    parsedPerson.add(lineHash);
                }
                else if(swap==2){

                    lineHash.put("Key", lineSplit[0]);
                    lineHash.put("MaleParent", lineSplit[1]);
                    lineHash.put("FemaleParent", lineSplit[2]);
                    lineHash.put("StartDate", lineSplit[3]);
                    lineHash.put("EndDate", lineSplit[4]);
                    lineHash.put("Location", lineSplit[5]);
                    parsedRelationship.add(lineHash);
                }
                else if(swap==3){
                    //do children stuff
                    lineHash.put("MaleParent", lineSplit[0]);
                    lineHash.put("FemaleParent", lineSplit[1]);
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


} 
