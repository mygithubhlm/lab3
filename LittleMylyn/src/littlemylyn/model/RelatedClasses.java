package littlemylyn.model;

import java.util.ArrayList;

public class RelatedClasses extends NodeWrapper<ArrayList<NodeWrapper<String>>> {


	public RelatedClasses(ArrayList<NodeWrapper<String>> _dat) {
		super(_dat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Object> getChildren() {
		// TODO Auto-generated method stub
		ArrayList<Object> outcome=new ArrayList<Object>();
		
		for(int i=0;i<this.basic_data.size();i++)
			outcome.add(this.basic_data.get(i));
		return outcome;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Related Class("+this.basic_data.size()+")";
	}
	
	public boolean hasChildren(){
		return !this.basic_data.isEmpty();
	}

}
