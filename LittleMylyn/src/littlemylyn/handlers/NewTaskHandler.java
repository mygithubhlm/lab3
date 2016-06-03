package littlemylyn.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import littlemylyn.entities.Task;
import littlemylyn.model.TaskManager;
import littlemylyn.views.LittleMylynView;
import littlemylyn.views.NewTaskDialog;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

public class NewTaskHandler extends AbstractHandler {

	public NewTaskHandler() {
		
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		NewTaskDialog dialog = new NewTaskDialog(window.getShell());
		if (dialog.open() != InputDialog.OK) return null;
		TaskManager.getManager().addTask(this.constructTask(dialog));
		//ask to set updated input
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				LittleMylynView.getViewer().setInput(TaskManager.getManager());
			}
		});
		return null;
	}
	
	
	public Task constructTask(NewTaskDialog dialog){
		return new Task(TaskManager.getManager().getTaskCount()+1,dialog.getTaskName(),dialog.getType(),Task.NEW_NUM());
	}
}
