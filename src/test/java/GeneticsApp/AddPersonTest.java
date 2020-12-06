package GeneticsApp;

import static org.junit.Assert.*;

import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import org.junit.Test;

public class AddPersonTest
{
    FamilyGraph func = new FamilyGraph();

    DefaultUndirectedGraph<Person, RelationshipEdge> g = new DefaultUndirectedGraph<>(RelationshipEdge.class);

    // All tests will include the ID since adding an ID for each person is a requirement, even in tests like 'onlyAddFullName'. All other fields can be left blank
    @Test
    public void onlyAddID()
    {
        g = func.addPerson("P45", "", "", "", "", "", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P45"))
            {
                assertEquals("P45", person.getId());
            }
        }
    }

    @Test
    public void onlyAddFullName()
    {
        g = func.addPerson("P46", "Maximus", "Decimus Meridius", "", "", "", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P46"))
            {
                assertEquals("Maximus", person.getFirstName());
                assertEquals("Decimus Meridius", person.getLastName());
            }
        }
    }

    @Test
    public void onlyAddFirstName()
    {
        g = func.addPerson("P47", "Commodus", "", "", "", "", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P47"))
            {
                assertEquals("Commodus", person.getFirstName());
            }
        }
    }

    @Test
    public void onlyAddLastName()
    {
        g = func.addPerson("P48", "", "Caesar", "", "", "", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P48"))
            {
                assertEquals("Caesar", person.getLastName());
            }
        }
    }

    @Test
    public void onlyAddSuffix()
    {
        g = func.addPerson("P49", "", "", "Mrs.", "", "", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P49"))
            {
                assertEquals("Mrs.", person.getSuffix());
            }
        }
    }

    @Test
    public void onlyAddDOB()
    {
        g = func.addPerson("P50", "", "", "", "07/12/100", "", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P50"))
            {
                assertEquals("07/12/100", person.getDob());
            }
        }
    }

    @Test
    public void onlyAddDOD()
    {
        g = func.addPerson("P50", "", "", "", "", "03/15/44", "", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P50"))
            {
                assertEquals("03/15/44", person.getDod());
            }
        }
    }

    @Test
    public void onlyAddBirthPlace()
    {
        g = func.addPerson("P51", "", "", "", "", "", "Rome", "" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P51"))
            {
                assertEquals("Rome", person.getBirthPlace());
            }
        }
    }

    @Test
    public void onlyAddDeathPlace()
    {
        g = func.addPerson("P52", "", "", "", "", "", "", "Gaul" , g);

        GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<>(g);

        while (iterator.hasNext())
        {
            Person person = iterator.next();

            if (person.getId().equals("P52"))
            {
                assertEquals("Gaul", person.getDeathPlace());
            }
        }
    }
}