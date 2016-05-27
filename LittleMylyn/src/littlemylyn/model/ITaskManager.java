package littlemylyn.model;

import java.util.ArrayList;

import littlemylyn.entities.Task;

// a class plays the role of model in MVC pattern, it holds entity and do some logical things

// hlm is responsible for this part
// please use Transferer as your component.


public interface ITaskManager {
	/* add a new task to the model 
	 * input: a task instance from handler
	 */
	public boolean addTask(Task newTask);
	
	/*
	 * remove a task from the model
	 * input: a partial completed task instance, only field taskId is filled up
	 * */
	public boolean removeTask(Task parTask);
	
	
	/*
	 * update an existed task in the model
	 * input: a partial completed task instance, only field taskId won't be changed, use it as a primary key
	 * */
	public boolean updateTask(Task parTask);
	
	
	/*
	 * return the whole list of task to the handler 
	 * */
	public ArrayList<Task> getTasks();
	
		
	/*
	 *  dump the data model into a JSON file
	 *  please use the ITransferer to do the IO
	 * */
	public boolean dump();  
	
	
	// other advanced things should be done later, like filter and search(may be not)
	
	
	
	
	
	
	
}
