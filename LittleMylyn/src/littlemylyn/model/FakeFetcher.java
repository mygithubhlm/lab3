package littlemylyn.model;

import java.util.ArrayList;

import littlemylyn.entities.Task;

public class FakeFetcher implements ITaskManager {

	@Override
	public boolean addTask(Task newTask) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeTask(Task parTask) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTask(Task parTask) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Task> getTasks() {
		// TODO Auto-generated method stub
		Task taskA=new Task(123123,"Panda",0,0);
		taskA.addClass("classA");
		taskA.addClass("classB");
		
		
		Task taskB=new Task(123,"Numpy",1,0);
		
		
		ArrayList<Task> outcome=new ArrayList<Task>();
		outcome.add(taskA);
		outcome.add(taskB);
		return outcome;
	}

	@Override
	public boolean dump() {
		// TODO Auto-generated method stub
		return false;
	}

}
