package Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class ListSelectAction extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(e.getSource() instanceof JTable) {
			UserListModel.selectedRow=((JTable) e.getSource()).getSelectedRow();
		}
	}
}
