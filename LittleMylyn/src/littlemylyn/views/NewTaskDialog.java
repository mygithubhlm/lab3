package littlemylyn.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewTaskDialog extends Dialog {
	private String taskName;
	private int type;// 0:debug, 1:new feature, 2:refactor
	private Text nameField;

	public NewTaskDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));

		final Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
		nameLabel.setText("Name: ");

		nameField = new Text(container, SWT.BORDER);
		nameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		final Composite typeRadioGroupComposite = new Composite(container, SWT.NONE);
		final GridData gridData_1 = new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1);
		// gridData_1.horizontalIndent = 20;
		typeRadioGroupComposite.setLayoutData(gridData_1);
		typeRadioGroupComposite.setLayout(new GridLayout());

		createTypeRadioGroup(typeRadioGroupComposite);
		initContent();
		return container;

	}

	private void createTypeRadioGroup(Composite parent) {

		final Group typeGroup = new Group(parent, SWT.NONE);
		typeGroup.setLayout(new GridLayout(3, true));
		typeGroup.setText("Type: ");

		final Button button_1 = new Button(typeGroup, SWT.RADIO);
		button_1.setSelection(true);
		button_1.setText("debug");
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button_1.getSelection())
					type = 0;
			}
		});

		final Button button_2 = new Button(typeGroup, SWT.RADIO);
		button_2.setText("new feature");
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button_1.getSelection())
					type = 1;
			}
		});

		final Button button_3 = new Button(typeGroup, SWT.RADIO);
		button_3.setText("refactor");
		button_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button_1.getSelection())
					type = 2;
			}
		});

	}

	private void initContent() {
		nameField.setText(taskName != null ? taskName : "");
		nameField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				taskName = nameField.getText();
			}
		});
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Task");
	}

	public String getTaskName() {
		return taskName;
	}

	public int getType() {
		return type;
	}

}
