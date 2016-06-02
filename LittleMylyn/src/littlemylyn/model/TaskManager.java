package littlemylyn.model;

import java.util.ArrayList;

import org.json.JSONArray;


import littlemylyn.entities.Task;
import littlemylyn.tools.ITransferer;
import littlemylyn.tools.Transferer;

public class TaskManager implements ITaskManager {
	static private TaskManager taskManager; //singleton
	static private final String dir="littlemylyn/data";
	static private final String filename="temp.json";
	private ITransferer transferer;
	private ArrayList<Task> tasks;
	
	
	private TaskManager(){
		tasks=new ArrayList<Task>();
		transferer=new Transferer();
		if(!this.initialize()){
			System.err.println("Error when initialization");
		}
		
		
	}
	
	public static TaskManager getManager(){
		if(taskManager==null) taskManager=new TaskManager();
		return taskManager;
	}
	

	
	
	// a tool function to init the tasks by the json data from the local file
	private boolean initialize(){
		JSONArray obj=transferer.retrieve(dir,filename);
		
		if(obj==null) return false;
		
		for(int i=0;i<obj.length();i++){
			this.tasks.add(new Task(obj.getJSONObject(i)));
		}
		
		return true;
	}
	
	
	// please refer to the interface
	@Override
	public boolean addTask(Task newTask) {
		int i = 0;
		while(i<tasks.size()){
			if(newTask.getTaskId()==tasks.get(i).getTaskId())
				return false;
		}
		tasks.add(newTask);
		return true;
	}
	
	
	// please refer to the interface
	@Override
	public boolean removeTask(Task parTask) {
		int i = 0;
		while(i<tasks.size()){
			if(parTask.getTaskId()==tasks.get(i).getTaskId()){
				tasks.remove(i);
				return true;
			}
		}
		return false;
	}

	
	// please refer to the interface
	@Override
	public boolean updateTask(Task parTask) {
		int i = 0;
		while(i<tasks.size()){
			if(parTask.getTaskId()==tasks.get(i).getTaskId()){
				tasks.remove(i);
				addTask(parTask);
				return true;
			}
		}
		return false;
	}

	// please refer to the interface
	@Override
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	
	// output the data into a json file
	@Override
	public boolean dump() {
		//to convert the Task Arraylist to a jsonarray 
		JSONArray jarr=new JSONArray();
		for(int i=0;i<tasks.size();i++){
			jarr.put(tasks.get(i).toJson());
		}
		
		return transferer.dumpTo(jarr, dir+"/"+filename);
	}

}
