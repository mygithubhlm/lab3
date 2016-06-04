package littlemylyn.model;

import java.util.ArrayList;

import org.json.JSONArray;


import littlemylyn.entities.Task;
import littlemylyn.tools.ITransferer;
import littlemylyn.tools.KoLogger;
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
	public void addTask(Task newTask) {
		tasks.add(newTask);
		KoLogger.logErr(this.dump(), "Dump");
	}
	
	

	
	// please refer to the interface
	@Override
	public boolean updateTask(Task parTask) {
		int i = 0;
		while(i<tasks.size()){
			if(parTask.getTaskId()==tasks.get(i).getTaskId()){
				tasks.remove(i);
				addTask(parTask);
				KoLogger.logErr(this.dump(), "Dump"); //dump to the file instantly
				return true;
			}
			i++;
		}
		return false;
	}

	// please refer to the interface
	@Override
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	
	// add related class to the unique activated class if not exist return false;
	@Override
	public boolean addRelatedClass(String resname){
		Task actiTask=this.getActivatedTask();
		if(actiTask==null){
			System.err.println("Failure no activated class");
			return false;
		}
			
		boolean outcome=actiTask.addClass(resname);
		KoLogger.logErr(this.dump(), "Dump");
		return outcome;
	}
	
	
	
	
	//return a reference to the uinque activated clas
	public Task getActivatedTask(){
		for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).isActivated()){
				return tasks.get(i);
			}
		}
		
		return null;
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
	
	
	@Override 
	public int getTaskCount(){
		return tasks.size();
	}

	//an overrided compareTo for invoking inputchanged in different situation
	
	@Override
	public int compareTo(ITaskManager _manager) {
		// TODO Auto-generated method stub
		if(this.getTaskCount()!=_manager.getTaskCount()){
			return 1;
		}else{
			for(int i=0;i<this.tasks.size();i++){
				if(this.tasks.get(i).compareTo(_manager.getTasks().get(i))!=0){
					return 1;
				}
			}
			return 0;
		}
	}

}
