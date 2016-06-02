package littlemylyn.model;

import java.util.ArrayList;

// here E should be a basic datatype
public class NodeWrapper<E> extends Node {
	E basic_data; 
	Object parent;
	public NodeWrapper(E _dat,Object p){
		this.basic_data=_dat;
		this.parent=p;
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
	
	public Object getParent(){
		return parent;
	}
}
