package com.ytkj.ytkj001.module;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import com.ytkj.ytkj001.data.Material;

/**
 * 
 * @author vearn
 */
public class TestTreeTableModel extends DefaultTreeTableModel {

	private String[] _names = { "料号", "寻号", "优先级", "描述", "替代项目组" };
	private Class[] _types = { String.class, String.class, String.class,
			String.class, String.class };

	public TestTreeTableModel(TreeTableNode node) {
		super(node);
	}

	/**
	 * 列的类型
	 */
	@Override
	public Class getColumnClass(int col) {
		return _types[col];
	}

	/**
	 * 列的数量
	 */
	@Override
	public int getColumnCount() {
		return _names.length;
	}

	/**
	 * 表头显示的内容
	 */
	@Override
	public String getColumnName(int column) {
		return _names[column];
	}

	/**
	 * 返回在单元格中显示的Object
	 */
	@Override
	public Object getValueAt(Object node, int column) {
		Object value = null;
		if (node instanceof DefaultMutableTreeTableNode) {
			DefaultMutableTreeTableNode mutableNode = (DefaultMutableTreeTableNode) node;
			Object o = mutableNode.getUserObject();
			if (o != null && o instanceof Material) {
				Material bean = (Material) o;
				switch (column) {
				case 0:
					value = bean.getLiaohao();
					break;
				case 1:
					value = bean.getXunhao();
					break;
				case 2:
					value = bean.getYouxianji();
					break;
				case 3:
					value = bean.getMiaoshu();
					break;
				case 4:
					value = bean.getTidaixiangmuzu();
					break;
				}
			}
		}
		return value;
	}

	/**
	 * 设置所有单元格都不能编辑
	 * 
	 * @param the
	 *            node (i.e. row) for which editing is to be determined
	 * @param the
	 *            column for which editing is to be determined
	 * @return false
	 */
	@Override
	public boolean isCellEditable(Object node, int column) {
		return false;
	}
}
