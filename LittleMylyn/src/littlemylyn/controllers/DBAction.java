package littlemylyn.controllers;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import littlemylyn.entities.Task;
import littlemylyn.model.NodeWrapper;
import littlemylyn.model.TaskManager;
import littlemylyn.views.ChangeStatDialog;
import littlemylyn.views.LittleMylynView;;


public class DBAction extends Action  {
	private LittleMylynView viewpart;
	private TreeViewer viewer;

	
	


	public DBAction(LittleMylynView _viewpart){
		this.viewpart=_viewpart;
		this.viewer=LittleMylynView.getViewer();
	}
	
	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		if (this.isTask(obj)) {
			//dbclick on task
			changeTask((Task) obj);
		} else if(this.isClass(obj)) {
			//dbclick on related class
			openInEditor((NodeWrapper<String>)obj);
		}
	}
	
	public boolean isClass(Object node){
		return (node instanceof NodeWrapper) && (((NodeWrapper) node).getParent() instanceof List);
	}
	
	public boolean isTask(Object node){
		return node instanceof Task;
	}

	
	//to open the change task dialog mae
	private void changeTask(Task t) {
		ChangeStatDialog dialog = new ChangeStatDialog(viewpart.getViewSite().getShell(),t.clone());
		if (dialog.open() != InputDialog.OK)
			return;
		t.setStat(dialog.getTask().getStat());
		TaskManager.getManager().updateTask(t);
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				LittleMylynView.getViewer().setInput(TaskManager.getManager());
			}
		});	
		
	}
	
	//open sepcific file in the eclipse editor
	private void openInEditor(NodeWrapper<String> node){
		//System.out.println("________________________");
		//System.out.println(node.toString());
		String temp1[]=node.toString().split("\\.");
		String fName="";
		String rfName=temp1[1];
		String temp2[]=temp1[0].split("/");
		String lfName="";
		for(int i=1;i<temp2.length;i++){
			lfName+="/"+temp2[i];
		}
		String projectname=temp2[0];
		fName=lfName+"/"+rfName+".java";
		fName=fName.replaceAll("\\s*", "");
		// TODO This is for Niu1234.
		if (fName != null) {
			//System.out.println(fName);
			IWorkbenchPage wbPage = PlatformUI.getWorkbench()  
			        .getActiveWorkbenchWindow().getActivePage();
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectname);
			IFile file = project.getFile(fName);  
			try {  
			    if (file != null) {  
			    IDE.openEditor(wbPage, file);  
			    }  
			 } catch (PartInitException e) {  
			 e.printStackTrace();  
			 }  
		return;  
	  } 
	} 
}
