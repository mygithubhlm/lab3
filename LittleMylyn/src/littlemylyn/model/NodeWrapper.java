package littlemylyn.model;

import java.util.ArrayList;

// here E should be a basic datatype
public class NodeWrapper<E> extends Node {
	E basic_data; 
	public NodeWrapper(E _dat){
		this.basic_data=_dat;
	}
	
	@Override
	public ArrayList<Object> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return basic_data.toString();
	}
}
