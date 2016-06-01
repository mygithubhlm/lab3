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
	
	
	public TaskManager(){
		tasks=new ArrayList<Task>();
		transferer=new Transferer();
		this.initialize();
		
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
	
	
	
	@Override
	public boolean addTask(Task newTask) {
		// TODO Auto-generated method stub
		int i = 0;
		while(i<tasks.size()){
			if(newTask.getTaskId()==tasks.get(i).getTaskId())
				return false;
		}
		tasks.add(newTask);
		return true;
	}

	@Override
	public boolean removeTask(Task parTask) {
		// TODO Auto-generated method stub
		int i = 0;
		while(i<tasks.size()){
			if(parTask.getTaskId()==tasks.get(i).getTaskId()){
				tasks.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateTask(Task parTask) {
		// TODO Auto-generated method stub
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

	@Override
	public ArrayList<Task> getTasks() {
		// TODO Auto-generated method stub
		return tasks;
	}

	@Override
	public boolean dump() {
		// TODO Auto-generated method stub
		return false;
	}

}
