package GeneticsApp;

import com.sun.jdi.event.StepEvent;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import java.util.*;
import java.util.stream.Collectors;

public class FamilyGraph {

    //Currently main gets the three parsed sections of the input file then uses them to build a graph
    //and print it
    public static void main(String[] args){

        //File parsers
        ParseFile parser = new ParseFile();
        DefaultUndirectedGraph<Person, RelationshipEdge> g = null;

        Scanner input = new Scanner(System.in);
        boolean choiceExit = false;

        do {
            System.out.println("--------------------------------------------------------------");
            System.out.println("Family Graph Main Menu\n \n1. Explore Graph \n2. Add person \n3. Output file \n4. Import File \n5. Display graph \n6. Exit Program");
            System.out.println("--------------------------------------------------------------");

            String choice = input.next();

            switch (choice){
                case "1":
                    if(g!=null) {
                        System.out.println("--------------------------------------------------------------");
                        System.out.println("Exploring Menu\n \n1. Explore by first and last names \n2. Explore by first name \n3. Explore by last name ");
                        System.out.println("--------------------------------------------------------------");
                        String exploreChoice = input.next();

                        switch (exploreChoice) {
                            case "1":
                                System.out.println("type the first name");
                                String firstName = input.next();
                                System.out.println("type the last name");
                                String lastName = input.next();
                                exploreGraph(g, firstName, lastName);
                                break;
                            case "2":
                                System.out.println("type the first name");
                                String firstNameOnly = input.next();
                                exploreGraphFirst(g,firstNameOnly);
                                break;
                            case "3":
                                System.out.println("type the last name");
                                String lastNameOnly = input.next();
                                exploreGraphLast(g,lastNameOnly);
                                break;
                            default:
                                System.out.println("Error, not a valid choice");
                        }
                    }
                    else
                    {
                        System.out.println("\nThe graph is currently empty. Please import a file or add people.\n");
                    }
                    break;

                case "2":
                    g = addPeople(g);
                    break;

                case "3":
                    outputFile(g);
                    break;

                case "4":
                    ArrayList<Hashtable<String,String>> peopleList = parser.getParsedPeople();
                    ArrayList<Hashtable<String,String>> relationshipList = parser.getParsedRelationship();
                    ArrayList<Hashtable<String,String>> parentList = parser.getParsedChild();
                    g = createGraph(peopleList, relationshipList, parentList);

                    System.out.println("\nFile successfully imported. Returning to main menu.\n");

                    break;

                case "5":
                    if(g != null)
                    {
                        printGraph(g);
                    }
                    else
                    {
                       System.out.println("\nThe graph is currently empty. Please import a file or add people.\n");
                    }
                    break;

                case "6":
                    System.out.println("\nExiting now. Thank you!\n");
                    choiceExit = true;
                    break;

                default:
                    System.out.println("\nError: Not a valid choice.\n");
            }
        }
        while (!choiceExit);

        //PART C AND B WILL BE A NEW FUNCTION WILL BE CALLED HERE VIA A SIMPLE CONSOLE FRONT-END

    }

    //This function provides a menu with which to create a person and then add relationships to them
    public static DefaultUndirectedGraph addPeople(DefaultUndirectedGraph<Person, RelationshipEdge> g)
    {

        Scanner input = new Scanner(System.in);
        String choiceID = null;
        boolean continueChoice = false;

        do{
            //This is the person object we will be adding to the graph
            Person newPerson = new Person();

            choiceID = checkIfDuplicateID(choiceID, g); // Function call that checks if the input entered by the user already exists in the graph

            newPerson.setId(choiceID);

            //This is some pretty straightforward data entry, for each necessary aspect of a Person
            System.out.println("\n...");
            System.out.println("ID accepted.\n");
            System.out.println("For ANY selection, enter '-' to leave blank.\n");
            System.out.print("Enter a first name: ");
            String choiceFirstName = input.next();
            if(choiceFirstName.equals("-"))
            {
                choiceFirstName = "";
            }
            newPerson.setFirstName(choiceFirstName);

            System.out.print("Enter a last name: ");
            String choiceLastName = input.next();
            if(choiceLastName.equals("-"))
            {
                choiceLastName = "";
            }
            newPerson.setLastName(choiceLastName);

            System.out.print("Enter a suffix: ");
            String choiceSuffix = input.next();
            if(choiceSuffix.equals("-"))
            {
                choiceSuffix = "";
            }
            newPerson.setSuffix(choiceSuffix);

            System.out.print("Enter a DOB (mm/dd/yyyy): ");
            String choiceDOB = input.next();
            if(choiceDOB.equals("-"))
            {
                choiceDOB = "";
            }
            newPerson.setDob(choiceDOB);

            System.out.print("Enter a birth place: ");
            String choiceBirthPlace = input.next();
            if(choiceBirthPlace.equals("-"))
            {
                choiceBirthPlace = "";
            }
            newPerson.setBirthPlace(choiceBirthPlace);

            System.out.print("Enter a DOD (mm/dd/yyyy): ");
            String choiceDOD = input.next();
            if(choiceDOD.equals("-"))
            {
                choiceDOD = "";
            }
            newPerson.setDod(choiceDOD);

            System.out.print("Enter a death place: ");
            String choiceDeathPlace = input.next();
            if(choiceDeathPlace.equals("-"))
            {
                choiceDeathPlace = "";
            }
            newPerson.setDeathPlace(choiceDeathPlace);

            System.out.println("\n...");
            System.out.println("Result:");

            boolean stillAdding = true;
            do {
                g.addVertex(newPerson); //THIS MAY NEED TO MOVE, it adds the new person to the graph
                System.out.println("\n--------------------------------------------------------");
                System.out.println(String.format("ID: %s", choiceID));
                System.out.println(String.format("First name: %s", choiceFirstName));
                System.out.println(String.format("Last name: %s", choiceLastName));
                System.out.println(String.format("Suffix: %s", choiceSuffix));
                System.out.println(String.format("DOB: %s", choiceDOB));
                System.out.println(String.format("Birth place: %s", choiceBirthPlace));
                System.out.println(String.format("DOD: %s", choiceDOD));
                System.out.println(String.format("Death place: %s", choiceDeathPlace));
                System.out.println("--------------------------------------------------------\n");

                //TODO: Have the relationships associated printed here, this can be nicked from printGraph()
                System.out.println("Select an option:");
                System.out.println("\t1. Add relationship \n\t2. Confirm addition \n\t3. Return to main menu");
                String choiceOption = input.next();

                //TODO: Currently, confirm addition does the same thing as return to menu. Change that so that returning to menu
                //DOES NOT change the loaded graph, this will require work in main

                switch (choiceOption){
                    case "1":
                        boolean IDNotFound = true;
                        String relatedID;
                        String relationshipType;
                        do {
                            //I say this is a temp feature since we should prolly use the lookup being coded here
                            System.out.print("\nEnter the ID of the related person (TEMP-FEATURE): ");
                            relatedID = input.next();


                            System.out.println(String.format("\nHow is %s related to %s?", relatedID, choiceID));
                            System.out.println("\t1. Parent of \n\t2. Child of \n\t3. Partner of");
                            relationshipType = input.next();

                            //In this section I'm looking to see if this ID exists in our graph. The functionality if it isnt is further down
                            GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<Person, RelationshipEdge>(g);
                            while (iterator.hasNext())
                            {
                                Person person = iterator.next();
                                if(person.getId().toString().equals(relatedID))
                                {
                                    System.out.println("\n...");
                                    System.out.println(String.format("%s found.", relatedID));
                                    IDNotFound = false;

                                    switch (relationshipType)
                                    {

                                        // There is something weird going on with cases 1 and 2. Not sure if they are working as intended
                                        case "1": //The parent case, where the new person is the parent
                                            Relationship parent = new Relationship(String.format("Parent %s", relatedID));
                                            parent.setMaleParent(newPerson);
                                            parent.setFemaleParent(person);
                                            RelationshipEdge parentEdge = new RelationshipEdge(parent);
                                            g.addEdge(newPerson, person, parentEdge);
                                            break;

                                        case "2":  //The child case, where the new person is the child
                                            Relationship child = new Relationship(String.format("Child %s", choiceID));
                                            child.setMaleParent(person);
                                            child.setFemaleParent(newPerson);
                                            RelationshipEdge childEdge = new RelationshipEdge(child);
                                            g.addEdge(person, newPerson, childEdge);
                                            break;
                                        case "3": //The partner case, where the new person is a partner
                                            boolean duplicatePartnership = false;
                                            String partnershipID;
                                            do {
                                                duplicatePartnership = false; // Prevents infinite loop

                                                System.out.print("\nEnter a unique partnership ID: ");
                                                partnershipID = input.next();

                                                //This is how we iterate through all existing edges in our graph, looking for duplicate relationship ID
                                                Set<RelationshipEdge> edges = g.edgeSet().stream().collect(Collectors.toSet());
                                                for(RelationshipEdge edge : edges)
                                                {
                                                    String currentEdgeID = edge.getLabel().getId();
                                                    if(partnershipID.equals(currentEdgeID))
                                                    {
                                                        duplicatePartnership = true;
                                                        System.out.println("\nDuplicate partnership ID found. Please enter another.");
                                                    }
                                                }
                                            } while(duplicatePartnership);

                                            // This is where partnership info is asked
                                            Relationship partner = new Relationship(partnershipID);

                                            System.out.println("\n...");
                                            System.out.println("Partnership ID accepted.");
                                            System.out.println("\nFor ANY selection, enter '-' to leave blank.\n");

                                            System.out.print("Set the partnership start date (mm/dd/yyyy): ");
                                            String choiceStartDate = input.next();

                                            if(choiceStartDate.equals("-"))
                                            {
                                                choiceStartDate = "";
                                            }

                                            partner.setStartDate(choiceStartDate);

                                            System.out.print("Set the partnership end date (mm/dd/yyyy): ");
                                            String choiceEndDate = input.next();

                                            if(choiceEndDate.equals("-"))
                                            {
                                                choiceStartDate = "";
                                            }

                                            partner.setEndDate(choiceEndDate);

                                            System.out.print("Set a description for the partnership: ");
                                            String choiceDescription = input.next();

                                            if(choiceDescription.equals("-"))
                                            {
                                                choiceStartDate = "";
                                            }

                                            partner.setDescription(choiceDescription);

                                            partner.setMaleParent(newPerson);
                                            partner.setFemaleParent(person);
                                            RelationshipEdge partnerEdge = new RelationshipEdge(partner);
                                            g.addEdge(newPerson, person, partnerEdge);


                                            // Container to print newly added partnership info
                                            System.out.println("\n...");
                                            System.out.println("Result:");
                                            System.out.println("\n--------------------------------------------------------");
                                            System.out.println("Partnership ID: " + partnershipID);
                                            System.out.println("Partnership start date: " + choiceStartDate);
                                            System.out.println("Partnership end date: " + choiceEndDate);
                                            System.out.println("Partnership description: " + choiceDescription);
                                            System.out.println("--------------------------------------------------------\n");

                                            System.out.println("Returning to new person entry.");
                                            System.out.println("...");

                                            break;
                                    }
                                }
                            }
                            if(IDNotFound)
                            {
                                System.out.println("\nID not found. Would you like to search again or return to person creation? \n\t1. Look for different ID \n\t2. Create a new person with searched ID \n\t3. Return to creation");
                                String keepTrying = input.next();
                                switch (keepTrying){
                                    case "1":
                                        break;
                                    case "2":
                                        addPeople(g); // Works just fine, just requires the searched ID to be re-entered when it asks for a unique ID (searched ID isn't automatically added)
                                        break;
                                    case "3":
                                        IDNotFound = false;
                                        break;
                                }
                            }
                        }while(IDNotFound);

                        break;
                    case "2":
                        // Saves any additions and changes loaded graph
                        stillAdding = false;
                        continueChoice = false;

                        break;
                    case "3":
                        // TODO: Exit without saving any additions
                        // This will require work in main()

                        break;
                }
            }
            while (stillAdding);
        }
        while (continueChoice);

        return g;
    }

    // New function that checks if the ID entered by the user exists within the graph or not to avoid duplicates
    public static String checkIfDuplicateID(String ID, DefaultUndirectedGraph<Person, RelationshipEdge> graph)
    {
        Scanner input = new Scanner(System.in);
        boolean duplicateID;

        do
        {
            duplicateID = false; // Prevents infinite loop

            System.out.print("\nEnter a unique ID (required): ");
            ID = input.next();

            if (ID.equalsIgnoreCase("Child"))
            {
                System.out.println("\nYou cannot enter that as an ID. Please enter another.");
                duplicateID = true;
            }

            else if (ID.equalsIgnoreCase("Parent"))
            {
                System.out.println("\nYou cannot enter that as an ID. Please enter another.");
                duplicateID = true;
            }

            else if (ID.equalsIgnoreCase("Partner"))
            {
                System.out.println("\nYou cannot enter that as an ID. Please enter another.");
                duplicateID = true;
            }

            else
            {
                //This is how you iterate through all the existing vertexes in our graph, looking to see if the chosen ID is a duplicate
                GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<Person, RelationshipEdge>(graph);
                while (iterator.hasNext())
                {
                    Person person = iterator.next();
                    // Not sure if these still need to be here
                    // System.out.println(String.format("]%s[", person.getId()));
                    // System.out.println(String.format("]%s[", choiceID));
                    if (person.getId().equals(ID))
                    {
                        System.out.println("\nDuplicate ID found. Please enter another.");

                        duplicateID = true;
                    }
                }
            }
        }
        while (duplicateID);

        return ID;
    }

    public static void exploreGraph(DefaultUndirectedGraph<Person, RelationshipEdge> g, String firstName, String lastName)
    {
        Person start1 = g.vertexSet().stream().filter(uri -> uri.getFirstName().equals(firstName)).findAny().get();
        Iterator<Person> iterator = new DepthFirstIterator<>(g, start1);
        System.out.println("People with the first name: "+ firstName + ", and last name of: "+lastName);
        while (iterator.hasNext()) {
            Person selectedNameNodes = iterator.next();
            if(selectedNameNodes.getFirstName()!=null && selectedNameNodes.getFirstName().equals(firstName) && selectedNameNodes.getLastName().equals(lastName)) {
                printNode(selectedNameNodes);
            }
        }

    }
    public static void exploreGraphFirst(DefaultUndirectedGraph<Person, RelationshipEdge> g, String firstName)
    {

        Person start = g.vertexSet().stream().filter(person -> person.getFirstName().equals(firstName)).findAny().get();
        Iterator<Person> iterator = new BreadthFirstIterator<>(g, start);
        System.out.println("People with the first name of: "+ firstName);
        while (iterator.hasNext()) {
            Person selectedNameNodes = iterator.next();
            if(selectedNameNodes.getFirstName()!=null && selectedNameNodes.getFirstName().equals(firstName)) {
                printNode(selectedNameNodes);
            }
        }
    }
    public static void exploreGraphLast(DefaultUndirectedGraph<Person, RelationshipEdge> g, String lastName)
    {
        Person start = g.vertexSet().stream().filter(uri -> uri.getLastName().equals(lastName)).findAny().get();
        Iterator<Person> iterator = new DepthFirstIterator<>(g, start);
        System.out.println("People with the last name of: "+ lastName);
        while (iterator.hasNext()) {
            Person selectedNameNodes = iterator.next();
            if(selectedNameNodes.getLastName()!=null && selectedNameNodes.getLastName().equals(lastName)) {
                printNode(selectedNameNodes);
            }
        }
    }

    public static void printNode(Person p){
        System.out.println("ID: " + p.getId());
        System.out.println(p.getFirstName()+ " " + p.getLastName());
        System.out.println("suffix: " + p.getSuffix());
        System.out.println("born: " + p.getDob() + " location: " + p.getBirthPlace());
        System.out.println("died: " + p.getDod() + " location: " + p.getDeathPlace());
        System.out.println("parents: " + p.getParents());
        System.out.println("----------------");
    }

    public static void outputFile(DefaultUndirectedGraph<Person, RelationshipEdge> g)
    {
        //TODO: Export graph to a CSV. Not a big concern
    }

    //Prints each vertex, it's name, and all its relationships
    public static void printGraph(DefaultUndirectedGraph familyTree)
    {
        //Create an iterator with which to traverse the graph
        GraphIterator<Person, RelationshipEdge> iterator =
                new BreadthFirstIterator<Person, RelationshipEdge>(familyTree);

        //Using the iterator we can iterate across all vertexes
        while (iterator.hasNext()) {
            Person man = iterator.next();
            String relatedOutput = "";

            //Gets all the edges associated with the current vertex
            Set<RelationshipEdge> relationships = familyTree.edgesOf(man);

            //Iterate across all associated edges
            for(RelationshipEdge connection : relationships){
                //Get the ID of the male and female parent of this relationships
                String maleID = connection.getLabel().getMaleParent().getId();
                String femaleID = connection.getLabel().getFemaleParent().getId();
                String manID = man.getId();

                String connectedTo = "";

                //Checks to see if the current person is the MALE or FEMALE in this relationships
                //Sets whichever the person ISNT as the connectedTo ID
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

                //THIS IS ROUGHLY WHERE PART D IS RELEVANT!

                //This sections attempts to discern from the relationship ID what TYPE of relationship
                //this is and add it to the string output for this person.
                String[] list = connection.getLabel().getId().split("-");
                if (list[0].equals("Child"))
                {
                    if(list[1].equals(manID))
                    {
                        relatedOutput = relatedOutput + String.format("\tChild of %s\n", connectedTo);
                    }
                    else
                    {
                        relatedOutput = relatedOutput + String.format("\tParent of %s\n", connectedTo);
                    }
                }
                else {
                    relatedOutput = relatedOutput + String.format("\tIn relationship %s to %s\n", connection.getLabel().getId(), connectedTo);
                }
            }
            //Print the person ID, their name, and the related output built above
            System.out.println(String.format("ID: %s", man.getId()));
            System.out.println(String.format("\tFirst name: %s\n\tLast name: %s", man.getFirstName(), man.getLastName()));
            System.out.print(relatedOutput);

        }
    }

    public static DefaultUndirectedGraph createGraph(ArrayList<Hashtable<String, String>> people, ArrayList<Hashtable<String, String>> relationships, ArrayList<Hashtable<String, String>> parentList)
    {
        DefaultUndirectedGraph<Person, RelationshipEdge> g = new DefaultUndirectedGraph<>(RelationshipEdge.class);

        //This first section iterates through the list of People we need to create and sets a Person
        //object with all the correct data for that person
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

            //Add this person as a vertex on the graph
            g.addVertex(person);
        }


        //This section iterates through the imported relationships
        for(i = 0; i < relationships.size(); i++)
        {
            Hashtable relationshipData = relationships.get(i);
            String id = (String) relationshipData.get("Key");
            Relationship r = new Relationship(id);

            String maleID;
            String femaleID;

            //If a parent ID is null set it to unknown, otherwise set it to the imported value
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

            Person male = new Person();
            Person female = new Person();
            GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<Person, RelationshipEdge>(g);

            //Iterate across the current Person elements implemented in our graph
            while (iterator.hasNext()) {
                Person person = iterator.next();

                //If the maleID is equal to another persons, then set them to their associated parents
                if(person.getId().equals(maleID))
                {
                    male = person;
                }
                else if (person.getId().equals(femaleID))
                {
                    female = person;
                }
            }

            //If the person is unknown, set their ID to unknown person and add to the vertex
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

            //The elements of a relationship are being set here before being added as an edge to
            //the graph
            r.setMaleParent(male);
            r.setFemaleParent(female);
            r.setDescription((String) relationshipData.get("Description"));
            r.setEndDate((String) relationshipData.get("EndDate"));
            r.setStartDate((String) relationshipData.get("StartDate"));


            RelationshipEdge edge = new RelationshipEdge(r);
            g.addEdge(male, female, edge);
        }

        //Iterates through all the edges with the intention of setting parent relationships using
        //parentList
        Set<RelationshipEdge> edges = g.edgeSet().stream().collect(Collectors.toSet());


        for(int index = 0; index < parentList.size(); index++)
        {
            GraphIterator<Person, RelationshipEdge> iterator = new BreadthFirstIterator<Person, RelationshipEdge>(g);
            while (iterator.hasNext()) {
                Person man = iterator.next();

                if(parentList.get(index).get("Child").equals(man.getId()))
                {
//                    System.out.println(String.format("%s equal to %s", parentList.get(index).get("Child"), man.getId()));

                    Person maleParent = new Person();
                    Person femaleParent = new Person();


                    for(RelationshipEdge edge : edges)
                    {
                        String currentEdgeID = edge.getLabel().getId();
                        String partnershipID = parentList.get(index).get("Partnership");

                        if(currentEdgeID.equals(partnershipID))
                        {
//                            System.out.println(String.format("%s equal to %s", currentEdgeID, partnershipID));

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

            }
        }
        return g;
    }
}

