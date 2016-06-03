package littlemylyn.controllers;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

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
		System.out.println("________________________");
		System.out.println(node.toString());
		// TODO This is for Niu1234.
	}
}
