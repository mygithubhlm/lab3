package littlemylyn.controllers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;

import littlemylyn.model.TaskManager;
import littlemylyn.tools.KoLogger;
import littlemylyn.views.LittleMylynView;

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
			System.out.println("Dump file");
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(monitor);
			KoLogger.logErr(TaskManager.getManager().dump(),"dump file");
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
						//add it to the activated task
						KoLogger.logErr(TaskManager.getManager().addRelatedClass(resname),"add class");
						//ask to set updated manager, asyn to do it
						Display.getDefault().asyncExec(new Runnable(){
							public void run(){
								LittleMylynView.getViewer().setInput(TaskManager.getManager());
							}
						});
						
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
	
	
	// a more stilll
	public boolean filterDelta(IResourceDelta delta){
		return delta.getKind()==IResourceDelta.ADDED||delta.getKind()==IResourceDelta.CHANGED||delta.getKind()==IResourceDelta.REMOVED;
	}
	

}
