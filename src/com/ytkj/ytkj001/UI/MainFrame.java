package com.ytkj.ytkj001.UI;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.jdesktop.swingx.ux.CheckTreeSelectionModel;
import org.jdesktop.swingx.ux.CheckTreeTableManager;

import com.ytkj.ytkj001.Service.MaterialService;
import com.ytkj.ytkj001.dao.MaterialDao;
import com.ytkj.ytkj001.data.Material;
import com.ytkj.ytkj001.data.MaterialRecord;
import com.ytkj.ytkj001.module.TestTreeTableModel;
import com.ytkj.ytkj001.other.DragDropRowTableUI;
import com.ytkj.ytkj001.tool.FileSaveUtil;
import com.ytkj.ytkj001.tool.Tool;
import com.ytkj.ytkj001.tool.UIStyleTransform;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	// private MyTreeTableModel treeTableModel = new MyTreeTableModel();

	private JXTreeTable treeTable = new JXTreeTable(new TestTreeTableModel(
			createDummyData()));
	CheckTreeTableManager manager = null;

	private DefaultMutableTreeTableNode createDummyData() {
		List<Material> materials = new MaterialDao().findAll();
		DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode(
				new Material());
		return root;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("当前用户：002");
		UIStyleTransform.initUI(this);
		manager = new CheckTreeTableManager(treeTable);
		treeTable.setRootVisible(false); // 显示根结点
		// treeTable.setCollapsedIcon(new
		// ImageIcon(MainFrame.class.getResource("/image/20140407155217.jpg")));

		treeTable.setUI(new DragDropRowTableUI(treeTable.getTreeTableModel(),
				treeTable.getTreeSelectionModel()));

		treeTable.setLeafIcon(new ImageIcon(MainFrame.class
				.getResource("/image/20140407155217.jpg")));
		treeTable.setOpenIcon(new ImageIcon(MainFrame.class
				.getResource("/image/20140407155217.jpg")));
		treeTable.setClosedIcon(new ImageIcon(MainFrame.class
				.getResource("/image/20140407155217.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		Tool.showFrameCenter(this);
		// setSize(width, height)
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("菜单");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("打开任务");
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("保存当前任务");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("管理扩展列");
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("退出");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu menu = new JMenu("其他");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("帮助");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new HelpDialog().setVisible(true);
			}
		});

		menu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		// panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		textField = new JTextField();
		panel_1.add(textField);

		textField.setColumns(30);

		JButton btnNewButton_1 = new JButton("搜索");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String xunhao = textField.getText().trim();
				if (xunhao == null || "".equals(xunhao)) {
					// System.out.println("不能为空");
					errorLabel.setText("寻号不能为空");
					errorLabel.setForeground(Color.RED);
					return;
				}
				DefaultMutableTreeTableNode root = (DefaultMutableTreeTableNode) treeTable
						.getTreeTableModel().getRoot();
				DefaultTreeTableModel model = (DefaultTreeTableModel) treeTable
						.getTreeTableModel();
				int size = model.getChildCount(root);// 得到孩子个数

				for (int i = 0; i < root.getChildCount(); i++) {
					DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) root
							.getChildAt(i);
					Material material = (Material) node.getUserObject();
					if (xunhao.equals(material.getXunhao())) {
						errorLabel.setText("寻号【" + xunhao + "】已经在检索结果中");
						errorLabel.setForeground(new Color(25, 168, 8));// 19A808
						return;
					}

				}
				MaterialService materialService = new MaterialService();
				MaterialDao materialDao = new MaterialDao();
				materialService.setMaterialDao(materialDao);
				List<MaterialRecord> materialRecords = materialService
						.match(xunhao);
				if (materialRecords.size() == 0) {
					errorLabel.setText("未发现数据");
					errorLabel.setForeground(new Color(25, 168, 8));// 19A808
					return;
				}
				for (MaterialRecord materialRecord : materialRecords) {
					DefaultMutableTreeTableNode yuanshinode = new DefaultMutableTreeTableNode(
							materialRecord.getMaterial());
					// root.add(yuanshinode);

					model.insertNodeInto(yuanshinode, root, size);

					size++;

					int size2 = model.getChildCount(yuanshinode);
					for (Material material : materialRecord.getMatchMaterials()) {
						model.insertNodeInto(new DefaultMutableTreeTableNode(
								material), yuanshinode, size2);
						size2++;
					}
				}
				// treeTable.getTreeTableModel().
				// treeTable.getTreeTableModel().
				// treeTable.get
				// DefaultTreeTableModel
				// model=(DefaultTreeTableModel)treeTable.getTreeTableModel();
				// model.
				// model.setRoot(root);
			}
		});
		panel_1.add(btnNewButton_1);

		errorLabel = new JLabel("");
		panel_1.add(errorLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_4);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("从数据库新增");
		panel_4.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("当前结果中查找");
		panel_4.add(rdbtnNewRadioButton_1);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_2.add(panel_5);

		JCheckBox chckbxNewCheckBox = new JCheckBox("自动匹配");
		panel_5.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("隐藏未勾选项");
		panel_5.add(chckbxNewCheckBox_1);

		JScrollPane scrollPane = new JScrollPane(treeTable);
		// scrollPane.add(treeTable);
		scrollPane.setBorder(UIManager.getBorder("ScrollPane.border"));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("当前共有物料109个，原始数据50条，匹配数据59条");
		panel_3.add(lblNewLabel);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_6);

		JButton btnNewButton_3 = new JButton("展开");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				treeTable.expandAll(); // 展开全部节点
			}
		});
		panel_6.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("收缩");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				treeTable.collapseAll();
			}
		});
		panel_6.add(btnNewButton_2);

		JButton btnNewButton = new JButton("导出Excel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cout = treeTable.getColumnCount();
				if (cout < 0) {
					return;
				}
				String[] columnname = new String[cout];
				for (int i = 0; i < cout; i++) {
					columnname[i] = treeTable.getColumnName(i);
				}
				DefaultMutableTreeTableNode root = (DefaultMutableTreeTableNode) treeTable
						.getTreeTableModel().getRoot();
				DefaultTreeTableModel model = (DefaultTreeTableModel) treeTable
						.getTreeTableModel();
				int size = model.getChildCount(root);// 得到孩子个数
				int[] counts = new int[root.getChildCount()];
				counts[0] = 0;
				for (int i = 1; i < root.getChildCount(); i++) {
					DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) root
							.getChildAt(i);
					counts[i] += node.getChildCount() + i;
				}
				Boolean flag = false;
				Boolean[] bflag = new Boolean[treeTable.getRowCount()];
				for (int i = 0; i < treeTable.getRowCount(); i++) {
					TreePath path = treeTable.getPathForRow(i);
					for (int j = 0; j < root.getChildCount(); j++) {
						if (i == counts[j]) {
							flag = true;
							break;
						} else {
							flag = false;
						}
					}
					if (flag) {
						bflag[i] = true;
					} else {
						bflag[i] = manager.getSelectionModel().isPathSelected(
								path, true);
					}
				}
				MaterialService service = new MaterialService();
				FileSaveUtil.save(UIStyleTransform.getframe(), columnname,
						service.productdata(treeTable, bflag));
			}
		});
		panel_6.add(btnNewButton);
	}

	private static class SupportedLaF {

		private final String name;
		private final LookAndFeel laf;

		SupportedLaF(String name, LookAndFeel laf) {
			this.name = name;
			this.laf = laf;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private JTextField textField;
	private JLabel errorLabel;
}
