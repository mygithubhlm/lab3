package littlemylyn.entities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import littlemylyn.model.Node;
import littlemylyn.model.NodeWrapper;
import littlemylyn.model.RelatedClasses;

/*
 * a bare entity class with no logic
 * */

public class Task extends Node implements Comparable<Task> {
	
	private int taskId; // which is a generated random long numeric sequence
	private String title;
	private int type;// 0:debug, 1:new feature, 2:refactor
	private int stat;// 0: new, 1:activated, 2: finished
	private ArrayList<String> classRelated;

	public final static String[] typeDic={"debug","new feature","refactor"};
	public final static String[] statDic={"new","activated","finished"};
	public final static int[][] notransDic={{},{0},{0}};
	



	
	
	public Task(int taskId, String title, int type, int stat) {
		super();
		// init the actual data
		this.taskId = taskId;
		this.title = title;
		this.type = type;
		this.stat = stat;
		this.classRelated=new ArrayList<String>();
	}
	
	
	// to construct a task instance from the JsonObject
	public Task(JSONObject jobj){
		super();
		this.taskId=jobj.getInt("taskId");
		this.title=jobj.getString("title");
		this.type=jobj.getInt("type");
		this.stat=jobj.getInt("stat");
		JSONArray jaClasses=jobj.getJSONArray("classRelated");
		this.classRelated=new ArrayList<String>();
		for(int i=0;i<jaClasses.length();i++){
			this.classRelated.add(jaClasses.getString(i));
		}
	}

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

	// operation for related classes


	
	public int getClassCount(){
		return this.classRelated.size();
	}
	
	
	// remove a existed related class
	public boolean removeClass(String className) {
		return classRelated.remove(className);
	}

	// add a non-repetitive related class
	public boolean addClass(String className) {
		// already added
		if (hasClass(className)){
			System.out.println("Failure: duplicated class");
			return false;
		}


		classRelated.add(className);
		return true;
	}

	// whether has a specific class name
	public boolean hasClass(String className) {
		int i = 0;
		while (i < classRelated.size()) {
			if (classRelated.get(i).equals(className)) {
				return true;
			}
			i++;
		}

		return false;
	}

	// to json object for output
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("taskId", taskId).put("title", title).put("type", type).put("stat", stat).put("classRelated",
				classRelated);
		return obj;
	}

	@Override
	public ArrayList<Object> getChildren() {
		// TODO Auto-generated method stub
		ArrayList<Object> children=new ArrayList<Object>();
		children.add(new NodeWrapper<String>(Task.typeDic[this.type],this));
		children.add(new NodeWrapper<String>(Task.statDic[this.stat],this));
		ArrayList<NodeWrapper<String>> classRelatedN=new ArrayList<NodeWrapper<String>>();
		for(int i=0;i<this.classRelated.size();i++)
			classRelatedN.add(new NodeWrapper<String>(this.refineClass(this.classRelated.get(i)),classRelatedN));
		
		children.add(new RelatedClasses(classRelatedN,this));
		
		return  children;						
	}

	// a tool method for a beau format class, assume components are more than 3
	private String refineClass(String resname){
		String[] components=resname.split("/");
		String refined=components[1]+"/"+components[2]+"/ ";
		
		for(int i=3;i<components.length-1;i++){
			refined+=components[i]+".";
		}
		
		return refined+components[components.length-1].replace(".java", "");
	}
	
	


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.title;
	}
	
	@Override
	public boolean hasChildren(){
		return true;
	}
	
	public Object getParent(){
		return null;
	}
	
	

	
	
	
	//which only diff them
	@Override
	public int compareTo(Task o) {
		// TODO Auto-generated method stub
		return (o.getStat()==this.getStat()&&o.getClassCount()==this.getClassCount())? 0:1;
	}
	
	
	//a deep clone method for this entity class
	@Override
	public Task clone(){
		Task task=new Task(this.taskId, this.title, this.type, this.stat);
		for(int i=0;i<this.classRelated.size();i++){
			task.addClass(this.classRelated.get(i));
		}
		return task;
	}
	
	
	
	
	//some constant get method
	public static int NEW_NUM(){
		return 0;
	}
	
	public static int ACT_NUM(){
		return 1;
	}
	
	public static int FIN_NUM(){
		return 2;
	}
	
	//whether is an activated task
	public boolean isActivated(){
		return this.getStat()==ACT_NUM();
	}
	
	public boolean isFinished(){
		return this.getStat()==FIN_NUM();
	}
	
	public boolean isNew(){
		return this.getStat()==NEW_NUM();
	}
	
}
