package littlemylyn.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import littlemylyn.entities.Task;
import littlemylyn.model.ITaskManager;
import littlemylyn.model.Node;
import littlemylyn.model.TaskManager;

import java.util.List;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.SWT;

public class LittleMylynView extends ViewPart {

	ITaskManager f = TaskManager.getManager();
	List<Task> nodesList = f.getTasks();
	Task[] nodesArray = nodesList.toArray(new Task[nodesList.size()]);

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "littlemylyn.views.DefaultView";

	private TreeViewer viewer;
	// private DrillDownAdapter drillDownAdapter;
	// private Action action1;
	// private Action action2;
	// private Action doubleClickAction;

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
			return((Node)arg0).getChildren().toArray();
		}

		@Override
		public Object getParent(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object arg0) {
			return ((Node)arg0).hasChildren();
		}

		@Override
		public Object[] getElements(Object arg0) {
			return nodesArray;
		}
	}

	private final String type2Str(int t) {
		if (t == 0)
			return "debug";
		else if (t == 1)
			return "new feature";
		else if (t == 2)
			return "refactor";
		return "";
	}

	private final String stat2Str(int t) {
		if (t == 0)
			return "new";
		else if (t == 1)
			return "activated";
		else if (t == 2)
			return "finished";
		return "";
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

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		// drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		viewer.setInput(nodesArray);

		// Create the help context id for the viewer's control
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
		// "LittleMylyn.viewer");
		// getSite().setSelectionProvider(viewer);
		// makeActions();
		// hookContextMenu();
		// hookDoubleClickAction();
		// contributeToActionBars();
	}
	//
	// private void hookContextMenu() {
	// MenuManager menuMgr = new MenuManager("#PopupMenu");
	// menuMgr.setRemoveAllWhenShown(true);
	// menuMgr.addMenuListener(new IMenuListener() {
	// public void menuAboutToShow(IMenuManager manager) {
	// LittleMylynView.this.fillContextMenu(manager);
	// }
	// });
	// Menu menu = menuMgr.createContextMenu(viewer.getControl());
	// viewer.getControl().setMenu(menu);
	// getSite().registerContextMenu(menuMgr, viewer);
	// }
	//
	// private void contributeToActionBars() {
	// IActionBars bars = getViewSite().getActionBars();
	// fillLocalPullDown(bars.getMenuManager());
	// fillLocalToolBar(bars.getToolBarManager());
	// }
	//
	// private void fillLocalPullDown(IMenuManager manager) {
	// manager.add(action1);
	// manager.add(new Separator());
	// manager.add(action2);
	// }
	//
	// private void fillContextMenu(IMenuManager manager) {
	// manager.add(action1);
	// manager.add(action2);
	// manager.add(new Separator());
	// drillDownAdapter.addNavigationActions(manager);
	// // Other plug-ins can contribute there actions here
	// manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	// }
	//
	// private void fillLocalToolBar(IToolBarManager manager) {
	// manager.add(action1);
	// manager.add(action2);
	// manager.add(new Separator());
	// drillDownAdapter.addNavigationActions(manager);
	// }
	//
	// private void makeActions() {
	// action1 = new Action() {
	// public void run() {
	// showMessage("Action 1 executed");
	// }
	// };
	// action1.setText("Action 1");
	// action1.setToolTipText("Action 1 tooltip");
	// action1.setImageDescriptor(
	// PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	//
	// action2 = new Action() {
	// public void run() {
	// showMessage("Action 2 executed");
	// }
	// };
	// action2.setText("Action 2");
	// action2.setToolTipText("Action 2 tooltip");
	// action2.setImageDescriptor(
	// PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	// doubleClickAction = new Action() {
	// public void run() {
	// ISelection selection = viewer.getSelection();
	// Object obj = ((IStructuredSelection) selection).getFirstElement();
	// showMessage("Double-click detected on " + obj.toString());
	// }
	// };
	// }
	//
	// private void hookDoubleClickAction() {
	// viewer.addDoubleClickListener(new IDoubleClickListener() {
	// public void doubleClick(DoubleClickEvent event) {
	// doubleClickAction.run();
	// }
	// });
	// }
	//
	// private void showMessage(String message) {
	// MessageDialog.openInformation(viewer.getControl().getShell(),
	// "DefaultView", message);
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
