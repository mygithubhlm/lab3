package littlemylyn.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.*;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.SWT;

import littlemylyn.controllers.DBAction;
import littlemylyn.entities.Task;
import littlemylyn.model.ITaskManager;
import littlemylyn.model.Node;
import littlemylyn.model.NodeWrapper;
import littlemylyn.model.TaskManager;

import java.util.List;

public class LittleMylynView extends ViewPart {

	private Action dbAction;
	private ITaskManager manager ;

	

	public LittleMylynView() {
		this.manager = TaskManager.getManager();
	}
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "littlemylyn.views.DefaultView";

	private static TreeViewer viewer;

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
		@Override
		public void dispose() {
			// TODO Auto-generated method stub
		}

		@Override
		public void inputChanged(final Viewer viewer, Object oldobj, final Object newobj) {
			// TODO Auto-generated method stub
			System.err.println("Input Changed");
			
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					((TreeViewer)viewer).getTree().setRedraw(true);
				}
			});

		}
		

		@Override
		public Object[] getChildren(Object node) {
			return ((Node)node).getChildren().toArray();
		}

		@Override
		public Object getParent(Object node) {
			// TODO Auto-generated method stub
			return ((Node)node).getParent();
		}

		@Override
		public boolean hasChildren(Object node) {
			return ((Node) node).hasChildren();
		}
	
		@Override
		public Object[] getElements(Object _manager) {
			return ((ITaskManager)_manager).getTasks().toArray();
		}
		

	
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof Task){
				if(((Task) obj).isActivated()){
					imageKey=ISharedImages.IMG_OPEN_MARKER;
				}else if(((Task)obj).isFinished()){
					imageKey=ISharedImages.IMG_TOOL_DELETE_DISABLED;
				}else{
					imageKey = ISharedImages.IMG_OBJ_FOLDER;
				}
			}
	
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}



	public void createPartControl(Composite parent) {
		//only init once
		if(viewer==null) {
			viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
			viewer.setContentProvider(new ViewContentProvider());
			viewer.setLabelProvider(new ViewLabelProvider());
			viewer.setInput(this.manager);
		}
		makeActions();
		hookDoubleClickAction();
	}

	private void makeActions() {
		dbAction = new DBAction(this);
	}
	
	
	//getter setter
	public static TreeViewer getViewer(){
		return viewer;
	}
	
	
	

	
	
 	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				dbAction.run();
			}
		});
	}


	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
