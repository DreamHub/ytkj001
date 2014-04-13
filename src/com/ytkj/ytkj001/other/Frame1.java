package com.ytkj.ytkj001.other;

import java.awt.*;   
import javax.swing.tree.DefaultMutableTreeNode;   
import javax.swing.tree.DefaultTreeModel;   
import javax.swing.tree.TreePath;   
import javax.swing.*;   
import java.awt.BorderLayout;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
import java.awt.dnd.DragSource;   
import java.awt.dnd.DropTarget;   
import java.awt.dnd.DnDConstants;   
import javax.swing.event.TreeSelectionListener;   
import javax.swing.event.TreeSelectionEvent;   
import java.awt.dnd.DragGestureListener;   
import java.awt.dnd.DragGestureEvent;   
import java.awt.dnd.DragSourceContext;   
import java.awt.dnd.DragSourceListener;   
import java.awt.dnd.DragSourceEvent;   
import java.awt.dnd.DragSourceDragEvent;   
import java.awt.dnd.DragSourceDropEvent;   
import java.util.ArrayList;   
  
public class Frame1 extends JFrame implements TreeSelectionListener{   
    JPanel contentPane;   
    BorderLayout borderLayout1 = new BorderLayout();   
    DefaultMutableTreeNode node = new DefaultMutableTreeNode("根目录 ");   
    DefaultTreeModel model = new DefaultTreeModel(node);   
    JTree tree = new JTree(model);   
    JButton btDel = new JButton();   
    JButton btAdd = new JButton();   
    JPanel jPanel1 = new JPanel();   
    GridBagLayout gridBagLayout1 = new GridBagLayout();   
    //拖动的树节点   
    DefaultMutableTreeNode dragTreeNode = null;   
    //树的所有树节点名称   
    ArrayList allTreeNodeNameList = new ArrayList();   
  
    public Frame1() {   
        try {   
            setDefaultCloseOperation(EXIT_ON_CLOSE);   
            jbInit();   
        } catch (Exception exception) {   
            exception.printStackTrace();   
        }   
    }   
  
    /**  
     *   Component   initialization.  
     *  
     *   @throws   java.lang.Exception  
     */  
    private void jbInit() throws Exception {   
        contentPane = (JPanel) getContentPane();   
        contentPane.setLayout(borderLayout1);   
        setSize(new Dimension(400, 300));   
        setTitle("Frame   Title ");   
        btDel.setText("删除 ");   
        btDel.addActionListener(new Frame1_jButton1_actionAdapter(this));   
        btAdd.setText("增加 ");   
        btDel.setEnabled(false);   
        btAdd.setEnabled(false);   
        initalTree();   
        btAdd.addActionListener(new Frame1_jButton2_actionAdapter(this));   
        jPanel1.setLayout(gridBagLayout1);   
        contentPane.add(tree, java.awt.BorderLayout.CENTER);   
        contentPane.add(jPanel1, java.awt.BorderLayout.SOUTH);   
  
        jPanel1.add(btDel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0  
                                                  , GridBagConstraints.EAST,   
                                                  GridBagConstraints.NONE,   
                                                  new Insets(5, 5, 5, 5), 0, 0));   
        jPanel1.add(btAdd, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0  
                                                  , GridBagConstraints.EAST,   
                                                  GridBagConstraints.NONE,   
                                                  new Insets(5, 5, 5, 5), 0, 0));   
        //JTree不能设置setDragEnabled(true)，如果设置了会出现   
        //java.awt.dnd.InvalidDnDOperationException: Drag and drop in progress 的异常   
        //        tree.setDragEnabled(true);   
        //拖动源是单一的实例   
        DragSource dragSource = DragSource.getDefaultDragSource();   
        //设置drap(拖)数据源对应的对象jtr，并且添加监听器   
        dragSource.createDefaultDragGestureRecognizer(tree,   
                DnDConstants.ACTION_MOVE, new DragAndDropDragGestureListener());   
        //设置放置目标jtr，并且添加监听器   
        DropTarget dropTarget = new DropTarget(tree,   
                                               new  
                                               DragAndDropDropTargetListener());   
  
    }   
  
    /**  
     * 初始化树  
     */  
    private void initalTree() {   
        allTreeNodeNameList.add("根目录");   
        //设置可以看见句柄   
        tree.setShowsRootHandles(true);   
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("jsp ");   
        allTreeNodeNameList.add("jsp");   
        node.add(parent1);   
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode("书籍 ");   
        allTreeNodeNameList.add("书籍");   
        node.add(parent);   
        DefaultMutableTreeNode java = new DefaultMutableTreeNode("java ");   
        allTreeNodeNameList.add("java");   
  
        parent.add(java);   
        DefaultMutableTreeNode java1 = new DefaultMutableTreeNode("完整参考资料 ");   
        allTreeNodeNameList.add("完整参考资料");   
        parent.add(java1);   
        DefaultMutableTreeNode java2 = new DefaultMutableTreeNode("java编程");   
        allTreeNodeNameList.add("java编程");   
        parent.add(java2);   
        DefaultMutableTreeNode jsp = new DefaultMutableTreeNode("学习jsp");   
        allTreeNodeNameList.add("学习jsp");   
        parent1.add(jsp);   
        //添加树的选择监听器   
        tree.addTreeSelectionListener(this);   
    }   
  
    public void jButton1_actionPerformed(ActionEvent e) {   
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.   
                                          getLastSelectedPathComponent();   
        if (selected != null) {   
            model.removeNodeFromParent(selected);   
        }   
    }   
  
    public void jButton2_actionPerformed(ActionEvent e) {   
        String name = javax.swing.JOptionPane.showInputDialog(this, "输入节点名字: ");   
        if (name == null || name.equals(" ")) {   
            return;   
        }   
        if (allTreeNodeNameList.contains(name.trim())) {//节点名称不能有重复的   
                // 数据库操作失败，无法验证用户! 系统错误   
         JOptionPane.showMessageDialog(this, "节点名称已经存在，添加失败！","错误",   
                                       JOptionPane.ERROR_MESSAGE);   
         return;   
  
        }   
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.   
                                          getLastSelectedPathComponent();   
        if (selected == null) {   
            return;   
        }   
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);   
        //添加到树节点   
        model.insertNodeInto(node, selected, 0);   
        TreePath path = new TreePath(node.getPath());   
        tree.makeVisible(path);   
    }   
  
      
  
    public void valueChanged(TreeSelectionEvent e) {   
        TreePath path = e.getPath();   
        if (path == null) {   
            btAdd.setEnabled(false);   
            btDel.setEnabled(false);   
            return;   
        }   
        if (((DefaultMutableTreeNode) path.getLastPathComponent()).isRoot()) {   
            btAdd.setEnabled(false);   
            btDel.setEnabled(false);   
        }   
        btAdd.setEnabled(true);   
        btDel.setEnabled(true);   
  
    }   
  
    /**  
     * 获取拖动的树节点  
     * @return DefaultMutableTreeNode  
     */  
    public DefaultMutableTreeNode getDragTreeNode(){   
        return dragTreeNode;   
    }   
       
    /**  
     * 设置拖动的树节点  
     */  
    public void setDragTreeNode(DefaultMutableTreeNode dragTreeNode){   
        this.dragTreeNode = dragTreeNode;   
    }   
       
    /**  
     * 获取树  
     * @return JTree  
     */  
    public JTree getTree(){   
       return tree;    
    }   
  
    //因为要JTreee既为拖动源又是放置目标，所以把DragGestureListener作为一个内部类比较好   
    class DragAndDropDragGestureListener implements DragGestureListener {   
        public void dragGestureRecognized(DragGestureEvent dge) {   
            JTree tree = (JTree) dge.getComponent();   
            TreePath path = tree.getSelectionPath();   
            if (path != null) {   
                dragTreeNode = (DefaultMutableTreeNode)   
                        path.getLastPathComponent();   
               if (dragTreeNode!= null) {   
                   DragAndDropTransferable dragAndDropTransferable = new  
                           DragAndDropTransferable(dragTreeNode);   
                   //启动拖动操作，dragAndDropTransferable为封装移动、复制或连接的数据   
                   //DragAndDropDragSourceListener实例十跟踪操作进程和完成操作启动者的任务   
                   dge.startDrag(DragSource.DefaultMoveDrop,   
                                 dragAndDropTransferable,   
                                 new DragAndDropDragSourceListener());   
               }   
            }   
        }   
    }   
       
       
    //因为要JTreee既为拖动源又是放置目标，所以把DragGestureListener作为一个内部类比较好   
    class DragAndDropDragSourceListener implements DragSourceListener {   
  
    //dragEnter(),dragExit(),dragOver(),dropActionChanged()这几个方法只有在调用放置目标监听器中   
    //的对应方法并且防止目标不拒绝操作后，才调用这个拖动源的方法。   
    /**  
     * 在光标进入放置组件的显示区时调用  
     * @param e DragSourceDragEvent  
     */  
    public void dragEnter(DragSourceDragEvent e) {   
        //设置光标   
        DragSourceContext context = e.getDragSourceContext();   
        int dropAction = e.getDropAction();   
        if ((dropAction & DnDConstants.ACTION_COPY) != 0) {   
            context.setCursor(DragSource.DefaultCopyDrop);   
        } else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {   
            context.setCursor(DragSource.DefaultMoveDrop);   
        } else {   
            context.setCursor(DragSource.DefaultCopyNoDrop);   
        }   
    }   
  
    /**  
     * 在光标退出放置组件的显示区时发生  
     * @param e DragSourceEvent  
     */  
    public void dragExit(DragSourceEvent e) {}   
  
    /**  
     * 在光标进入放置组件显示区之后移动时调用  
     * @param e DragSourceDragEvent  
     */  
    public void dragOver(DragSourceDragEvent e) {}   
  
    /**  
     * 选择放置操作的修饰键的状态变化  
     * @param e DragSourceDragEvent  
     */  
    public void dropActionChanged(DragSourceDragEvent e) {}   
  
    /**  
     *放置发生并调用DropTargetListener的drop()方法后，调用dragDropEnd()方法，  
     * 告诉拖动源放置已经完成  
     * @param e DragSourceDropEvent  
     */  
    public void dragDropEnd(DragSourceDropEvent e) {   
        //getDropSuccess()对应DropTargetListener的drop()方法调用dropcomplete()时指目标指定的值   
        //getDropAction()对应DropTargetListener的drop()方法调用acceptDrop()时指定的操作   
        if (!e.getDropSuccess()||e.getDropAction()!= DnDConstants.ACTION_MOVE) {   
            return;   
        }   
  
        DragSourceContext context = e.getDragSourceContext();   
        Object comp = context.getComponent();   
        if (comp==null||!(comp instanceof JTree)) {   
            return ;   
        }   
        DefaultMutableTreeNode dragTreeNode = getDragTreeNode();   
           
        if (dragTreeNode!=null) {   
            ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(dragTreeNode);   
            //设置拖动的树节点为空   
            setDragTreeNode(null);   
        }   
  
    }   
}   
  
  
  
    public static void main(String[] args) {   
       new Frame1().setVisible(true);   
   }   
}   
  
  
  
class Frame1_jButton2_actionAdapter implements ActionListener {   
    private Frame1 adaptee;   
    Frame1_jButton2_actionAdapter(Frame1 adaptee) {   
        this.adaptee = adaptee;   
    }   
  
    public void actionPerformed(ActionEvent e) {   
        adaptee.jButton2_actionPerformed(e);   
    }   
}   
  
  
class Frame1_jButton1_actionAdapter implements ActionListener {   
    private Frame1 adaptee;   
    Frame1_jButton1_actionAdapter(Frame1 adaptee) {   
        this.adaptee = adaptee;   
    }   
  
    public void actionPerformed(ActionEvent e) {   
        adaptee.jButton1_actionPerformed(e);   
    }   
}  