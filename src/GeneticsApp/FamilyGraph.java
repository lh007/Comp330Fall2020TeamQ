package GeneticsApp;
import org.jgrapht.*;
import org.jgrapht.graph.*;

class Person{
    String firstName;
    String lastName;
    String dob;
    String dod;
    String birthPlace;
    String deathPlace;
    int id;

    public Person(int id)
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

}

class Relationship{
    Person maleParent;
    Person femaleParent;
    int startDate;
    int endDate;
    String description;
    int id;

    public Relationship(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setMaleParent(Person maleParent) {
        this.maleParent = maleParent;
    }

    public void setFemaleParent(Person femaleParent){
        this.femaleParent = femaleParent;
    }

    public void setStartDate(int startDate){
        this.startDate = startDate;
    }

    public void setEndDate(int endDate){
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

    public int getStartDate() {
        return this.startDate;
    }

    public int getEndDate() {
        return this.endDate;
    }

    public String getDescription() {
        return this.description;
    }


}

class RelationshipEdge
        extends
        DefaultEdge
{
    private String label;
    public RelationshipEdge(String label)
    {
        this.label = label;
    }

    public String getLabel()
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
        Graph<Person, RelationshipEdge> g = new SimpleDirectedGraph<>(RelationshipEdge.class);

        Person bob = new Person(1);
        Person tom = new Person(2);
        Person tony = new Person(3);

        g.addVertex(bob);
        g.addVertex(tom);
        g.addVertex(tony);

        g.addEdge(bob, tom, new RelationshipEdge("1"));
    }
}
