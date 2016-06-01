package littlemylyn.model;

import java.util.ArrayList;

public abstract class Node {
	abstract public ArrayList<Object> getChildren();
	abstract public boolean hasChildren();
	abstract public String toString();
	
	
}
