package GeneticsApp;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Hashtable;

class Person{
    String firstName;
    String lastName;
    String dob;
    String dod;
    String birthPlace;
    String deathPlace;
    String suffix;
    String parents;
    String id;

    public Person()
    {
        //TODO
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setDob(String dob)
    {
        this.dob = dob;
    }
    public void setDod(String dod)
    {
        this.dod = dod;
    }
    public void setBirthPlace(String birthPlace)
    {
        this.birthPlace = birthPlace;
    }
    public void setDeathPlace(String deathPlace)
    {
        this.deathPlace = deathPlace;
    }
    public void setSuffix(String suffix){this.suffix = suffix;}
    public void setParents(String parents){this.parents = parents;}
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getDob(){
        return this.dob;
    }
    public String getDod(){
        return this.dod;
    }
    public String getBirthPlace(){
        return this.birthPlace;
    }
    public String getDeathPlace(){
        return this.deathPlace;
    }
    public String getSuffix(){return this.suffix;}
    public String getId(){return this.id;}
    public String getParents(){return this.parents;}

}

class Relationship{
    Person maleParent;
    Person femaleParent;
    String startDate;
    String endDate;
    String description;
    String id;

    public Relationship(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setMaleParent(Person maleParent) {
        this.maleParent = maleParent;
    }

    public void setFemaleParent(Person femaleParent){
        this.femaleParent = femaleParent;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Person getMaleParent() {
        return this.maleParent;
    }

    public Person getFemaleParent() {
        return this.femaleParent;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getDescription() {
        return this.description;
    }


}

//This is the custom edge were using, it has NO direction and hold a label which SHOULD be the a
//relationship
class RelationshipEdge
        extends
        DefaultEdge
{
    private Relationship label;
    public RelationshipEdge(Relationship label)
    {
        this.label = label;
    }

    public Relationship getLabel()
    {
        return label;
    }

    @Override
    public String toString()
    {
        return "(" + getSource() + " : " + getTarget() + " : " + label + ")";
    }
}

public class FamilyGraph {
    public static void main(String[] args)
    {
//        DefaultUndirectedGraph<Person, RelationshipEdge> g = new DefaultUndirectedGraph<>(RelationshipEdge.class);
//
//        Person bob = new Person(1);
//        Person tom = new Person(2);
//        Person tony = new Person(3);
//
//        bob.setFirstName("Bob");
//        tom.setFirstName("Tom");
//        tony.setFirstName("Tony");
//
//        g.addVertex(bob);
//        g.addVertex(tom);
//        g.addVertex(tony);
//
//        g.addEdge(bob, tom, new RelationshipEdge("1"));
        ArrayList<Hashtable> test2 = new ArrayList<Hashtable>();
        Hashtable <String, String> testHash2 = new  Hashtable <String, String>();
        testHash2.put("MaleParent", "P1");
        testHash2.put("FemaleParent", "");
        testHash2.put("Description", "NOTHING HAPPEN");
        testHash2.put("EndDate", "NOW");
        testHash2.put("StartDate", "LATER");
        testHash2.put("Key", "R1");
        test2.add(testHash2);
//


        ArrayList<Hashtable> test = new ArrayList<Hashtable>();
        Hashtable <String, String> testHash = new  Hashtable <String, String>();
        testHash.put("GivenName", "Bob");
        testHash.put("FamilyName", "Fuchs");
        testHash.put("Key", "P1");
        testHash.put("Suffix", "Sr.");
        testHash.put("DOD", "yesterday");
        testHash.put("DOB", "today");
        testHash.put("BirthPlace", "St Louis");
        testHash.put("DeathPlace", "Chicago");
        testHash.put("Parents", "None");
        test.add(testHash);

        Hashtable <String, String> testHash3 = new  Hashtable <String, String>();
        testHash3.put("GivenName", "Tony");
        testHash3.put("FamilyName", "Fuchs");
        testHash3.put("Key", "P2");
        testHash3.put("Suffix", "Jr");
        testHash3.put("DOD", "yesterday");
        testHash3.put("DOB", "today");
        testHash3.put("BirthPlace", "St Louis");
        testHash3.put("DeathPlace", "Chicago");
        testHash3.put("Parents", "R1");
        test.add(testHash3);


        DefaultUndirectedGraph<Person, RelationshipEdge> g = createGraph(test, test2);

//        GraphIterator<Person, RelationshipEdge> iterator =
//                new BreadthFirstIterator<Person, RelationshipEdge>(g);
//        while (iterator.hasNext()) {
//            Person man = iterator.next();
//            System.out.println( man.getId() );
//        }
//
        for(RelationshipEdge edge : g.edgeSet())
        {
            System.out.println(edge.getLabel().getId());
//            System.out.println(edge.getLabel().getMaleParent().getId());
//            System.out.println(edge.getLabel().getFemaleParent().getId());
        }

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
                male.setId("Unknown");
                g.addVertex(male);
            }

            if (female.getId() == null)
            {
                female.setId("Unknown");
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
                    RelationshipEdge maleEdge = new RelationshipEdge(maleRelationship);
                    g.addEdge(man, maleParent, maleEdge);

                    Relationship femaleRelationship = new Relationship(String.format("Child-%s", man.getId()));
                    RelationshipEdge femaleEdge = new RelationshipEdge(femaleRelationship);
                    g.addEdge(man, femaleParent, femaleEdge);
                }
            }
        }

        return g;
    }
}

