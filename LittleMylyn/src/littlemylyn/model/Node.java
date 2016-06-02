package littlemylyn.model;

import java.util.ArrayList;

// as you can see, this abstract class works as a model for view
public abstract class Node {
	abstract public ArrayList<Object> getChildren();
	abstract public boolean hasChildren();
	abstract public String toString();
	
	
}
