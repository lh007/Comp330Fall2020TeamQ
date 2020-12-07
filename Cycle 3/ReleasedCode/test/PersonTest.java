package GeneticsApp;

import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    FamilyGraph func = new FamilyGraph();
    String fileName = "test";
    ParseFile parser = new ParseFile(fileName);

    ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
    ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
    ArrayList<Hashtable<String,String>> parentList = parser.getParsedChild();
    DefaultUndirectedGraph<Person, RelationshipEdge> g = new DefaultUndirectedGraph<>(RelationshipEdge.class);

    @Test
    public void TestGrandparents()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        int index = 0;
        Set<RelationshipEdge> edges = new HashSet<>(g.edgeSet());
        for (RelationshipEdge edge : edges) {
            String[] list = edge.getLabel().getId().split("-");
            if (list[0].equals("Grandparent")) {
                index += 1;
            }
        }
        assertEquals(index, 26);
    }


    @Test
    public void testGrandparent19()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P1"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P19" );
                    }
                }
            }
        }
    }


    @Test
    public void testGrand2()
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
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P1" );
                    }
                }
            }
        }
    }


    @Test
    public void testGrand20()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P20"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P1" );
                    }
                }
            }
        }
    }


    @Test
    public void testGrand10()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P10"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P1" );
                    }
                }
            }
        }
    }


    @Test
    public void testGrand8()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P8"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P1" );
                    }
                }
            }
        }
    }


    @Test
    public void testGrand7()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P7"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P9" );
                    }
                }
            }
        }
    }


    @Test
    public void testGrand6()
    {
        g = func.createPeople(peopleList, g);
        g = func.createRelationship(relationshipList, g);
        g = func.createParents(parentList, g);
        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);
        while (iterator.hasNext()) {
            Person man = iterator.next();
            if(man.getId().equals("P6"))
            {
                Set<RelationshipEdge> r = g.edgesOf(man);
                for(RelationshipEdge edge : r)
                {
                    String[] list = edge.getLabel().getId().split("-");
                    if (list[0].equals("Grandchild"))
                    {
                        assertEquals(edge.getLabel().getMaleParent().getId(),"P9" );
                    }
                }
            }
        }
    }
}

