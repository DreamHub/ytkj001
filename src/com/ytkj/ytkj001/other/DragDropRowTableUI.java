package com.ytkj.ytkj001.other;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableModel;

import com.ytkj.ytkj001.data.Material;

public class DragDropRowTableUI extends BasicTableUI {

	private boolean draggingRow = false;
	private int startDragPoint;
	private int dyOffset;
	private TreeTableModel treeTableModel;
	private TreeSelectionModel treeSelectionModel;
	

	public DragDropRowTableUI(TreeTableModel treeTableModel,TreeSelectionModel treeSelectionModel) {
		super();
		this.treeTableModel = treeTableModel;
		this.treeSelectionModel=treeSelectionModel;
	}

	protected MouseInputListener createMouseInputListener() {
		return new DragDropRowMouseInputHandler();
	}

	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);

		if (draggingRow) {
			g.setColor(table.getParent().getBackground());
			Rectangle cellRect = table.getCellRect(table.getSelectedRow(), 0,
					false);
			g.copyArea(cellRect.x, cellRect.y, table.getWidth(),
					table.getRowHeight(), cellRect.x, dyOffset);

			if (dyOffset < 0) {
				g.fillRect(cellRect.x, cellRect.y
						+ (table.getRowHeight() + dyOffset), table.getWidth(),
						(dyOffset * -1));
			} else {
				g.fillRect(cellRect.x, cellRect.y, table.getWidth(), dyOffset);
			}
		}
	}

	class DragDropRowMouseInputHandler extends MouseInputHandler {

		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			startDragPoint = (int) e.getPoint().getY();
		}

		public void mouseDragged(MouseEvent e) {
			int fromRow = table.getSelectedRow();
			//treeTableModel.ge
			TreePath treepath=treeSelectionModel.getSelectionPath();
			
			//Object obj=treepath.getLastPathComponent();
			//treeSelectionModel.get
			if(treepath==null||treepath.getPathCount()!=3){
				return;
			}
			
			DefaultTreeTableModel defaultmodel = (DefaultTreeTableModel)treeTableModel;
			//defaultmodel.getValueAt(node, column)
			//defaultmodel.setValueAt(value, node, column)
			///treeTableModel.isLeaf(treepath);
			//defaultmodel.valueForPathChanged(path, newValue)
			//System.out.println(treeTableModel.isLeaf(treepath));
			//treeTableModel.get
			//model.getPathToRoot(aNode);
			//defaultmodel.valueForPathChanged(path, newValue)
			
			if (fromRow >= 0) {
				draggingRow = true;
				int rowHeight = table.getRowHeight();
				int middleOfSelectedRow = (rowHeight * fromRow)
						+ (rowHeight / 2);
				int toRow = -1;
				int yMousePoint = (int) e.getPoint().getY();

				if (yMousePoint < (middleOfSelectedRow - rowHeight)) {
					// Move row up
					toRow = fromRow - 1;
				} else if (yMousePoint > (middleOfSelectedRow + rowHeight)) {
					// Move row down
					toRow = fromRow + 1;
				}
				JXTreeTable treeTable =(JXTreeTable)table;

				if (toRow >= 0 && toRow < table.getRowCount()) {
					TableModel model = table.getModel();
					
					Material fromObj=new Material();
					Material toObj=new Material();
					for (int i = 0; i < treeTable.getColumnCount(); i++) {
						
						Object fromValue = treeTable.getValueAt(fromRow, i);
						System.out.println("fromValue:"+fromValue);
						Object toValue = treeTable.getValueAt(toRow, i);
						System.out.println("toValue:"+toValue);
						//treeTable.setValueAt(toValue, fromRow, i);
						//treeTable.setValueAt(fromValue, toRow, i);
						switch (i) {
						case 0:
							fromObj.setLiaohao((String)(treeTable.getValueAt(fromRow, i)));
							toObj.setLiaohao((String)(treeTable.getValueAt(toRow, i)));
							break;
						case 1:
							fromObj.setXunhao((String)(treeTable.getValueAt(fromRow, i)));
							toObj.setXunhao((String)(treeTable.getValueAt(toRow, i)));
							break;
						case 2:
							fromObj.setYouxianji((String)(treeTable.getValueAt(fromRow, i)));
							toObj.setYouxianji((String)(treeTable.getValueAt(toRow, i)));
							break;
						case 3:
							fromObj.setMiaoshu((String)(treeTable.getValueAt(fromRow, i)));
							toObj.setMiaoshu((String)(treeTable.getValueAt(toRow, i)));
							break;
						case 4:
							fromObj.setTidaixiangmuzu((String)(treeTable.getValueAt(fromRow, i)));
							toObj.setTidaixiangmuzu((String)(treeTable.getValueAt(toRow, i)));
							break;

						default:
							break;
						}
						
						//model.setValueAt(toValue, fromRow, i);
						//model.setValueAt(fromValue, toRow, i);
					}
					defaultmodel.valueForPathChanged(treeTable.getPathForRow(fromRow), toObj);
					defaultmodel.valueForPathChanged(treeTable.getPathForRow(toRow), fromObj);
					table.setRowSelectionInterval(toRow, toRow);
					startDragPoint = yMousePoint;
				}
				dyOffset = (startDragPoint - yMousePoint) * -1;
				System.out.println("dyOffset:" + dyOffset);
				table.repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			draggingRow = false;
			table.repaint();
		}
	}
}