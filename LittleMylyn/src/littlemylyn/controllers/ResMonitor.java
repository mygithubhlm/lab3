package littlemylyn.controllers;


import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;

import littlemylyn.Activator;
import littlemylyn.model.ITaskManager;
import littlemylyn.model.TaskManager;

public class ResMonitor implements IResourceChangeListener {
	//still a singleton
	static private ResMonitor monitor;
	
	//It's a bridge between model and view. Thus they need them.
//	private ITaskManager manager=TaskManager.getManager(); 

	
	private ResMonitor(){
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,IResourceChangeEvent.POST_CHANGE);
		System.out.println("Resource Monitor has been constructed");
	}
	
	
	
	
	public static ResMonitor getMonitor(){
		if(monitor==null){
			monitor=new ResMonitor();
		}
		
		return monitor;
	}
	
	
	
	public static void shutdown(){
		if(monitor!=null){
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(monitor);
			if(!TaskManager.getManager().dump()){
				System.err.println("Dump Fail");
			}		
			monitor=null; // destruct it 
		}
		

	}

		
	@Override
	public void resourceChanged(IResourceChangeEvent ev) {
		// TODO Auto-generated method stub
		System.out.println("_______________________________");
		System.err.println(ev.getDelta().getResource().toString());
	}

}
