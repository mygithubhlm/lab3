package littlemylyn.entities;

import java.util.ArrayList;


import org.json.JSONObject;

/*
 * a bare entity class with no logic
 * */

public class Task {

	private int taskId;  //which is a generated random long numeric sequence
	private String title;
	private int type; //0:debug, 1:new feature, 2:refactor
	private int stat; //0: new, 1:activated, 2: finished
	private ArrayList<String> classRelated;
	
	
	// getter and setter, auto-generated code
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	
	
	//operation for related classes
	
	// remove a existed related class
	public boolean removeClass(String className){
		return classRelated.remove(className);
	}
	
	// add a non-repetitive related class
	public boolean addClass(String className){
		//already added
		if(hasClass(className)) return false;
		
		classRelated.add(className);
		return true;	
	}
	
	// whether has a specific class name
	public boolean hasClass(String className){
		int i=0;
		while(i<classRelated.size()){
			if(classRelated.get(i).equals(className)){
				return true;
			}
			i++;
		}
		
		return false;
	}
	
	
	// to json object for output
	public JSONObject toJson(){
		JSONObject obj=new JSONObject();
		obj.put("taskId",taskId ).put("title", title)
		.put("type",type).put("stat", stat).put("classRelated", classRelated);
		return obj;
	}
	
	
	
	
	
	
	
	
}
