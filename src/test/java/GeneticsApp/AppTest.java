package GeneticsApp;

import static org.junit.Assert.*;

import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    FamilyGraph func = new FamilyGraph();
    String fileName = "test";
    ParseFile parser = new ParseFile(fileName);

    ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
    ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
    ArrayList<Hashtable<String,String>> parentList = parser.getParsedChild();
    DefaultUndirectedGraph<Person, RelationshipEdge> g = new DefaultUndirectedGraph<>(RelationshipEdge.class);


    @Test
    public void numberOfPeople(){
        g = func.createPeople(peopleList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        int index = 0;
        while (iterator.hasNext()) {
            index += 1;
            Person man = iterator.next();
        }
        assertTrue(index == 22);
    }

    @Test
    public void checkPersonData(){
        g = func.createPeople(peopleList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId() == "P1")
            {
                assertTrue(man.getFirstName() == "Dick");
                assertTrue(man.getLastName() == "Johnson");
                assertTrue(man.getSuffix() == "Jr");
            }
        }
    }

    @Test
    public void numberOfRelationships()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        Set<RelationshipEdge> edges = new HashSet<>(g.edgeSet());
        int index = 0;
        for (RelationshipEdge edge : edges)
        {
            index += 1;
        }
        assertTrue(index == 9);
    }

    @Test
    public void checkRelationshipInfo()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        Set<RelationshipEdge> edges = new HashSet<>(g.edgeSet());
        for (RelationshipEdge edge : edges)
        {
            if(edge.getLabel().getId().equals("R1"))
            {
                assertTrue(edge.getLabel().getStartDate().equals("6/7/1938"));
                assertTrue(edge.getLabel().getEndDate().equals(""));
                assertTrue(edge.getLabel().getDescription().equals("St Matthew's Flint Michigan"));
            }
        }
    }

    @Test
    public void checkRelationshipEdges()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P1"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge connection : r)
                {
                    assertTrue(connection.getLabel().getFemaleParent().getId().equals("P1"));
                    assertTrue(connection.getLabel().getMaleParent().getId().equals("Unknown Person"));
                    assertTrue(connection.getLabel().getId().equals("R6"));
                }
            }
        }

    }

    @Test
    public void numberOfParents()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        int index = 0;
        Set<RelationshipEdge> edges = new HashSet<>(g.edgeSet());
        for (RelationshipEdge edge : edges)
        {
            String[] list = edge.getLabel().getId().split("-");
            if (list[0].equals("Child"))
            {
                index += 1;
            }
        }
        assertTrue(index == 38);
    }

    @Test
    public void checkParentEdge()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P19"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Child"))
                    {
                        assertTrue(edge.getLabel().getFemaleParent().getId().equals("P6") || edge.getLabel().getFemaleParent().getId().equals("P21"));
                    }
                }
            }
        }
    }

}
