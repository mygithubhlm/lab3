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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class ChangeStatDialog extends Dialog {

	private int stat;// 0: new, 1:activated, 2: finished
	Button button_1, button_2, button_3;

	public ChangeStatDialog(Shell parentShell, int stat) {
		super(parentShell);
		this.stat = stat;
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
		typeGroup.setText("Stat: ");

		final Button button_1 = new Button(typeGroup, SWT.RADIO);
		this.button_1 = button_1;
		button_1.setText("New");
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button_1.getSelection()) {
					stat = 0;
				}
			}
		});

		final Button button_2 = new Button(typeGroup, SWT.RADIO);
		this.button_2 = button_2;
		button_2.setText("Activated");
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button_2.getSelection()) {
					stat = 1;
				}
			}
		});

		final Button button_3 = new Button(typeGroup, SWT.RADIO);
		this.button_3 = button_3;
		button_3.setText("Finished");
		button_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button_3.getSelection()) {
					stat = 2;
				}
			}
		});

	}

	private void initContent() {
		button_1.setSelection(false);
		button_2.setSelection(false);
		button_3.setSelection(false);
		switch (stat) {
		case 0:
			button_1.setSelection(true);
			break;
		case 1:
			button_2.setSelection(true);
			button_1.setEnabled(false);
			break;
		case 2:
			button_3.setSelection(true);
			button_1.setEnabled(false);
			break;
		}
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Change State");
	}

	public int getStat() {
		return this.stat;
	}
}
