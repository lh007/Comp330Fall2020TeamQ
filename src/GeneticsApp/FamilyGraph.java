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

        ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
        ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
        DefaultUndirectedGraph<Person, RelationshipEdge> g = createGraph(peopleList, relationshipList);
        printGraph(g);

    }

    public static void printGraph(DefaultUndirectedGraph familyTree)
    {
        GraphIterator<Person, RelationshipEdge> iterator =
                new BreadthFirstIterator<Person, RelationshipEdge>(familyTree);


        while (iterator.hasNext()) {
            Person man = iterator.next();
            String relatedOutput = "";

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
    }

    public static DefaultUndirectedGraph createGraph(ArrayList<Hashtable<String, String>> people, ArrayList<Hashtable<String, String>> relationships)
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

        System.out.println(relationships.size());
        for(i = 0; i < relationships.size(); i++)
        {
            Hashtable relationshipData = relationships.get(i);
            String id = (String) relationshipData.get("Key");
            Relationship r = new Relationship(id);

            String maleID;
            String femaleID;

            if(relationshipData.get("MaleParent").toString().isEmpty())
            {
                maleID = "Unknown";
            }
            else
            {
                maleID = (String) relationshipData.get("MaleParent");
            }

            if(relationshipData.get("FemaleParent").toString().isEmpty())
            {
                femaleID = "Unknown";
            }
            else
            {
                femaleID = (String) relationshipData.get("FemaleParent");
            }
//            System.out.println(String.format("If [%s] is blank, then [%s] should be unknown", relationshipData.get("FemaleParent"), femaleID));

            Person male = new Person();
            Person female = new Person();
            GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<Person, RelationshipEdge>(g);

            while (iterator.hasNext()) {
                Person person = iterator.next();

                if(person.getId().equals(maleID))
                {
                    male = person;
                }
                else if (person.getId().equals(femaleID))
                {
                    female = person;
                }
                else
                {
                    //System.out.println(String.format("[%s] is not equal to [%s] or [%s]", person.getId(), maleID, femaleID));
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

