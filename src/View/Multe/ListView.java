package View.Multe;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Control.ListSelectAction;
import Control.MVC_Connect;
import Control.UserListModel;
import Serversynchronization.TotalJsonObject;
import View.BaseClass.BasicPanel;

public class ListView extends BasicPanel{
	/**
	 * 대전이 가능한 유저들의 정보를 보여주는 패널으로
	 * 이 패널은 JTable을 기반으로 해서 만들어 졌다
	 */
	private static final long serialVersionUID = -4165956493948698794L;
	String header[] = { "번호", "이름", "ID" };
	String data[][];

	JTable table;
	JScrollPane listJs;

	public ListView() {
		UserListModel model = new UserListModel(header, 0);
		
		table = new JTable(model);
		table.getTableHeader().setBackground(Color.BLACK);
		table.getTableHeader().setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		table.setOpaque(false);
		table.setForeground(Color.WHITE);
		table.addMouseListener(new ListSelectAction());
		
		listJs = new JScrollPane(table);
		listJs.setOpaque(false);
		listJs.getViewport().setOpaque(false);
		add(listJs);

		setOpaque(false);
		
		TotalJsonObject json=new TotalJsonObject();
		json.addProperty("method", "initList");
		MVC_Connect.ViewToControl.callEvent(UserListModel.class, json.toString());
	}

}
