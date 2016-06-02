package littlemylyn.controllers;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import littlemylyn.model.TaskManager;

public class ResMonitor implements IResourceChangeListener {
	// still a singleton
	static private ResMonitor monitor;

	// It's a bridge between model and view. Thus they need them.
	// private ITaskManager manager=TaskManager.getManager();

	private ResMonitor() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		System.out.println("Resource Monitor has been constructed");
	}

	public static ResMonitor getMonitor() {
		if (monitor == null) {
			monitor = new ResMonitor();
		}

		return monitor;
	}

	public static void shutdown() {
		if (monitor != null) {
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(monitor);
			if (!TaskManager.getManager().dump()) {
				System.err.println("Dump Fail");
			}
			monitor = null; // destruct it
		}

	}

	@Override
	public void resourceChanged(IResourceChangeEvent ev) {
		// TODO Auto-generated method stub
		try {
			ev.getDelta().accept(new IResourceDeltaVisitor(){
				public boolean visit(IResourceDelta delta){
					String resname=delta.getResource().toString();
					if(filterClass(resname)!=null){
						//add 
					}
					return true;
				}
			});
		} catch (CoreException e) {
			System.err.println("Error when visit delta file");
		}
	}
	
	
	
	//some tool methods only for this class to use
	
	//filter out the java class name otherwise return null
	public String filterClass(String resourceName){
		return (resourceName.endsWith("java"))? resourceName:null;
	}


}
