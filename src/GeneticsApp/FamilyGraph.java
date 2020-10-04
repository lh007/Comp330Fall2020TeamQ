package GeneticsApp;

import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class FamilyGraph {
    public static void main(String[] args){


        //this should start parser on file and parse these array lists
        ParseFile parser = new ParseFile();

//        ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
//        ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
//        ArrayList<Hashtable<String,String>> childList = parser.getParsedChild();


//        ArrayList<Hashtable> test2 = new ArrayList<Hashtable>();
//        Hashtable <String, String> testHash2 = new  Hashtable <String, String>();
//        testHash2.put("MaleParent", "P1");
//        testHash2.put("FemaleParent", "");
//        testHash2.put("Description", "NOTHING HAPPEN");
//        testHash2.put("EndDate", "NOW");
//        testHash2.put("StartDate", "LATER");
//        testHash2.put("Key", "R1");
//        test2.add(testHash2);

//        ArrayList<Hashtable> test = new ArrayList<Hashtable>();
//        Hashtable <String, String> testHash = new  Hashtable <String, String>();
//        testHash.put("GivenName", "Bob");
//        testHash.put("FamilyName", "Fuchs");
//        testHash.put("Key", "P1");
//        testHash.put("Suffix", "Sr.");
//        testHash.put("DOD", "yesterday");
//        testHash.put("DOB", "today");
//        testHash.put("BirthPlace", "St Louis");
//        testHash.put("DeathPlace", "Chicago");
//        testHash.put("Parents", "None");
//        test.add(testHash);

//        Hashtable <String, String> testHash3 = new  Hashtable <String, String>();
//        testHash3.put("GivenName", "Tony");
//        testHash3.put("FamilyName", "Fuchs");
//        testHash3.put("Key", "P2");
//        testHash3.put("Suffix", "Jr");
//        testHash3.put("DOD", "yesterday");
//        testHash3.put("DOB", "today");
//        testHash3.put("BirthPlace", "St Louis");
//        testHash3.put("DeathPlace", "Chicago");
//        testHash3.put("Parents", "R1");
//        test.add(testHash3);


//        DefaultUndirectedGraph<Person, RelationshipEdge> g = createGraph(test, test2);
//
//        printGraph(g);

//        GraphIterator<Person, RelationshipEdge> iterator =
//                new BreadthFirstIterator<Person, RelationshipEdge>(g);
//        while (iterator.hasNext()) {
//            Person man = iterator.next();
//            System.out.println( man.getId() );
//        }
//
//        for(RelationshipEdge edge : g.edgeSet())
//        {
//            System.out.println(edge.getLabel().getId());
//            System.out.println(edge.getLabel().getMaleParent().getId());
//            System.out.println(edge.getLabel().getFemaleParent().getId());
//        }

    }

    public static void printGraph(DefaultUndirectedGraph familyTree)
    {
        GraphIterator<Person, RelationshipEdge> iterator =
                new BreadthFirstIterator<Person, RelationshipEdge>(familyTree);


        while (iterator.hasNext()) {
            Person man = iterator.next();
            String relatedOutput = "";

//            List<Person> neighbors = Graphs.neighborListOf(familyTree, man);

            Set<RelationshipEdge> relationships = familyTree.edgesOf(man);
            for(RelationshipEdge connection : relationships){
                String maleID = connection.getLabel().getMaleParent().getId();
                String femaleID = connection.getLabel().getFemaleParent().getId();
                String manID = man.getId();

                String connectedTo = "";
                if(manID == maleID)
                {
                    connectedTo = femaleID;
                }
                else if (manID == femaleID)
                {
                    connectedTo = maleID;
                }
                else
                {
                    System.out.print("This shouldnt happen");
                }
                relatedOutput = relatedOutput + String.format("\tConnected via %s to %s\n", connection.getLabel().getId(), connectedTo);
            }

            System.out.println(String.format("ID: %s", man.getId()));
            System.out.println(String.format("\tFirst name: %s\n\tLast name: %s", man.getFirstName(), man.getLastName()));
            System.out.print(relatedOutput);

        }

//        Set<RelationshipEdge> yesTree = (Set<RelationshipEdge>) familyTree.edgeSet();
//        for(RelationshipEdge edge :  yesTree)
//        {
//            System.out.print("NEW EDGE \n");
//            System.out.println(edge.getLabel().getId());
//            System.out.println(edge.getLabel().getMaleParent().getId());
//            System.out.println(edge.getLabel().getFemaleParent().getId());
//            Person origin = (Person) familyTree.getEdgeSource(edge);
//            System.out.println(origin.getId());
//        }

    }

    public static DefaultUndirectedGraph createGraph(ArrayList<Hashtable> people, ArrayList<Hashtable> relationships)
    {
        DefaultUndirectedGraph<Person, RelationshipEdge> g = new DefaultUndirectedGraph<>(RelationshipEdge.class);

        int i;
        for(i = 0; i < people.size(); i++)
        {
            Hashtable personData = people.get(i);
            String id = (String) personData.get("Key");
            Person person = new Person();
            person.setId(id);

            person.setFirstName((String) personData.get("GivenName"));
            person.setLastName((String) personData.get("FamilyName"));
            person.setFirstName((String) personData.get("GivenName"));
            person.setSuffix((String) personData.get("Suffix"));
            person.setDob((String) personData.get("DOB"));
            person.setDod((String) personData.get("DOD"));
            person.setBirthPlace((String) personData.get("BirthPlace"));
            person.setDeathPlace((String) personData.get("DeathPlace"));
            person.setParents((String)personData.get("Parents"));

            g.addVertex(person);
        }

        for(i = 0; i < relationships.size(); i++)
        {
            Hashtable relationshipData = relationships.get(i);
            String id = (String) relationshipData.get("Key");
            Relationship r = new Relationship(id);

            String maleID;
            String femaleID;
            if((String) relationshipData.get("MaleParent") == "")
            {
                maleID = "Unknown";
            }
            else
            {
                maleID = (String) relationshipData.get("MaleParent");
            }

            if((String) relationshipData.get("FemaleParent") == "")
            {
                femaleID = "Unknown";
            }
            else
            {
                femaleID = (String) relationshipData.get("FemaleParent");
            }


            Person male = new Person();
            Person female = new Person();
            GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<Person, RelationshipEdge>(g);

            while (iterator.hasNext()) {
                Person person = iterator.next();
                if(person.getId() == maleID)
                {
                    male = person;
                }
                else if (person.getId() == femaleID)
                {
                    female = person;
                }
            }
            if(male.getId() == null)
            {
                male.setId("Unknown Person");
                g.addVertex(male);
            }

            if (female.getId() == null)
            {
                female.setId("Unknown person");
                g.addVertex(female);
            }

            r.setMaleParent(male);
            r.setFemaleParent(female);
            r.setDescription((String) relationshipData.get("Description"));
            r.setEndDate((String) relationshipData.get("EndDate"));
            r.setStartDate((String) relationshipData.get("StartDate"));

            RelationshipEdge edge = new RelationshipEdge(r);
            g.addEdge(male, female, edge);
        }


        GraphIterator<Person, RelationshipEdge> iterator =
                new BreadthFirstIterator<Person, RelationshipEdge>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();

            Person maleParent = new Person();
            Person femaleParent = new Person();
            for(RelationshipEdge edge : g.edgeSet())
            {
//                System.out.println(edge.getLabel().getId());
//                System.out.println(man.getParents());
                if(edge.getLabel().getId() == man.getParents())
                {
                    maleParent = edge.getLabel().getMaleParent();
                    femaleParent = edge.getLabel().getFemaleParent();

                    Relationship maleRelationship = new Relationship(String.format("Child-%s",  man.getId()));
                    maleRelationship.setMaleParent(maleParent);
                    maleRelationship.setFemaleParent(man);
                    RelationshipEdge maleEdge = new RelationshipEdge(maleRelationship);
                    g.addEdge(man, maleParent, maleEdge);

                    Relationship femaleRelationship = new Relationship(String.format("Child-%s", man.getId()));
                    femaleRelationship.setMaleParent(femaleParent);
                    femaleRelationship.setFemaleParent(man);
                    RelationshipEdge femaleEdge = new RelationshipEdge(femaleRelationship);
                    g.addEdge(man, femaleParent, femaleEdge);
                }
            }
        }
        return g;
    }
}

