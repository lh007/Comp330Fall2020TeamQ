package GeneticsApp;

import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

//This is the custom edge were using, it has NO direction and hold a label which SHOULD be the a
//relationship
class RelationshipEdge extends DefaultEdge{
    
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
