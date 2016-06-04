package littlemylyn.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import littlemylyn.entities.Task;
import littlemylyn.model.TaskManager;

public class ChangeStatDialog extends Dialog {


	private Task task;
	private Button[] options;
	private BatchListener blistener;

	public ChangeStatDialog(Shell parentShell,Task _task) {
		super(parentShell);
		this.task=_task;
		this.options=new Button[3];
		blistener=new BatchListener(task);
		
	}
	
	public Task getTask(){
		return task;
	}
	
	

	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));

		final Composite typeRadioGroupComposite = new Composite(container, SWT.NONE);
		final GridData gridData_1 = new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1);
		typeRadioGroupComposite.setLayoutData(gridData_1);
		typeRadioGroupComposite.setLayout(new GridLayout());

		createTypeRadioGroup(typeRadioGroupComposite);
		initContent();
		return container;

	}

	

	
	private void createTypeRadioGroup(Composite parent) {

		final Group typeGroup = new Group(parent, SWT.NONE);
		typeGroup.setLayout(new GridLayout(3, true));
		typeGroup.setText("State");
		
		for(int i=0;i<this.options.length;i++){
			options[i]=new Button(typeGroup,SWT.RADIO);
			options[i].setText(Task.statDic[i]);
			options[i].addSelectionListener(this.blistener);
		}
		
		

	}
	
	
	
	//internal listener class
	class BatchListener extends SelectionAdapter{
		private Task task;
		public BatchListener(Task _task){
			this.task=_task;
		}
		public void widgetSelected(SelectionEvent e) {
			Button curOption=(Button)(e.getSource());
			//update the task here
			task.setStat(getType(curOption));
		}
	}
	
	
	
	// init the selected with some contraints
	private void initContent(){
		// add transition contraints
		for(int i=0;i<Task.notransDic[task.getStat()].length;i++){
			options[i].setEnabled(false);
		}

		
		// add activated contraints
		if(TaskManager.getManager().getActivatedTask()!=null){
			options[Task.ACT_NUM()].setEnabled(false);
		}
		
		// init the current state
		options[task.getStat()].setEnabled(true);
		options[task.getStat()].setSelection(true);
	}


	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Change State");
	}


	
	// to get the current selected option
	public int getType(Button option){
		for(int i=0;i<options.length;i++){
			if(options[i]==option) return i;
		}
		
		return -1;
	}
}
