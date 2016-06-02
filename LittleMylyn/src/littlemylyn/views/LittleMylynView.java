package littlemylyn.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import littlemylyn.entities.Task;
import littlemylyn.model.ITaskManager;
import littlemylyn.model.Node;
import littlemylyn.model.TaskManager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.SWT;

public class LittleMylynView extends ViewPart {
	static LittleMylynView instance;

	ITaskManager f = TaskManager.getManager();
	List<Task> nodesList = f.getTasks();
	Task[] nodesArray = nodesList.toArray(new Task[nodesList.size()]);

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "littlemylyn.views.DefaultView";

	private TreeViewer viewer;
	// private DrillDownAdapter drillDownAdapter;

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
			// String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			// if (obj instanceof TreeParent)
			// imageKey = ISharedImages.IMG_OBJ_FOLDER;
			// return
			// PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
			return null;
		}
	}

	// class NameSorter extends ViewerSorter { }

	public LittleMylynView() {
	}

	// public static synchronized LittleMylynView getInstance() {
	// if (instance == null) {
	// instance = new LittleMylynView();
	// }
	// return instance;
	// }

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		// drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		viewer.setInput(nodesArray);
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
