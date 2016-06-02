package littlemylyn.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.*;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.SWT;
import littlemylyn.entities.Task;
import littlemylyn.model.ITaskManager;
import littlemylyn.model.Node;
import littlemylyn.model.NodeWrapper;
import littlemylyn.model.TaskManager;

import java.util.List;

public class LittleMylynView extends ViewPart {

	private Action doubleClickAction;

	ITaskManager f = TaskManager.getManager();
	List<Task> nodesList = f.getTasks();
	Task[] nodesArray = nodesList.toArray(new Task[nodesList.size()]);

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "littlemylyn.views.DefaultView";

	private TreeViewer viewer;

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
		}

		@Override
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public Object[] getChildren(Object arg0) {
			return ((Node) arg0).getChildren().toArray();
		}

		@Override
		public Object getParent(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object arg0) {
			return ((Node) arg0).hasChildren();
		}

		@Override
		public Object[] getElements(Object arg0) {
			return nodesArray;
		}
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof Task)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

	// class NameSorter extends ViewerSorter { }

	public LittleMylynView() {
	}

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		// drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		viewer.setInput(nodesArray);
		makeActions();
		hookDoubleClickAction();
	}

	private void makeActions() {
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof Task) {
					changeTask((Task) obj);
				} else if ((obj instanceof NodeWrapper) && (((NodeWrapper) obj).getParent() instanceof List)) {
					// TODO This is for Niu1234.
				}
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void changeTask(Task t) {
		ChangeStatDialog dialog = new ChangeStatDialog(this.getViewSite().getShell(), t.getStat());
		if (dialog.open() != InputDialog.OK)
			return;
		System.out.println(dialog.getStat());
		t.setStat(dialog.getStat());
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
