import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ParseFile {
    String file = "FamilyTreeInputTextFile.txt";
    ArrayList<Hashtable<String,String>> parsedPerson = new ArrayList<>();
    ArrayList<Hashtable<String,String>> parsedRelationship = new ArrayList<>();

    public void readAndParse() {
        try{

            List<String> data = new Files.readAllLines(Paths.get(file));
            for(int i=0; i<data.size(); i++){
                Hashtable <String, String> lineHash = new  Hashtable <String, String>();
                String[] lineSplit = data(i).split(",");
                //this line split is for the divider, need to go deeper.
                //i'm stumped. I don't want to use another for loop since that will make time complex n^2
                if(lineSplit[0].equals("Person")){ 
                    lineHash.put("Key", lineSplit[0]);
                    lineHash.put("FamilyName", lineSplit[1]);
                    lineHash.put("GivenName", lineSplit[2]);
                    lineHash.put("Suffix", lineSplit[3]);
                    lineHash.put("DOB", lineSplit[4]);
                    lineHash.put("BirthPlace", lineSplit[5]);
                    lineHash.put("DOD", lineSplit[6]);
                    lineHash.put("DeathPlace", lineSplit[7]);
                    lineHash.put("Parents", lineSplit[8]);
                }
                else if(lineSplit[0].equals("Partnership")){

                }
                else if(lineSplit[0].equals("Children")){

                }

            }



        }
        catch(Exception e){
            System.out.println("wtf happened with parsing" + e);
        }

    }
} 
