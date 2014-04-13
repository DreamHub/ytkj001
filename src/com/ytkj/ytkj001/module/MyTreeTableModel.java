package com.ytkj.ytkj001.module;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

public class MyTreeTableModel extends AbstractTreeTableModel 
{
	private MyTreeNode myroot;
	
	public MyTreeTableModel()
	{
		myroot = new MyTreeNode( "root", "Root of the tree" );
		
		myroot.getChildren().add( new MyTreeNode( "Empty Child 1", 
		  "This is an empty child" ) );
		
		MyTreeNode subtree = new MyTreeNode( "Sub Tree", 
		  "This is a subtree (it has children)" );
		subtree.getChildren().add( new MyTreeNode( "EmptyChild 1, 1", 
		  "This is an empty child of a subtree" ) );
		subtree.getChildren().add( new MyTreeNode( "EmptyChild 1, 2", 
		  "This is an empty child of a subtree" ) );
		myroot.getChildren().add( subtree );
		
		myroot.getChildren().add( new MyTreeNode( "Empty Child 2", 
		  "This is an empty child" ) );
		
	}

	@Override
	public int getColumnCount() 
	{
		return 3;
	}
	
	@Override
	public boolean isCellEditable(Object node, int column) {
		// TODO Auto-generated method stub
		if(column==2){
		return true;}else{
			return false;
		}
	}
	

	@Override
	public void setValueAt(Object value, Object node, int column) {
		// TODO Auto-generated method stub
		System.out.println("aa");
		MyTreeNode treeNode=(MyTreeNode)node;
		
	}


	@Override
	public String getColumnName( int column )
	{
		switch( column )
		{
		case 0: return "料号";
		case 1: return "描述";
		case 2: return "其他";
		default: return "Unknown";
		}
	}

	@Override
	public Object getValueAt( Object node, int column ) 
	{
		System.out.println( "getValueAt: " + node + ", " + column );
		MyTreeNode treenode = ( MyTreeNode )node;
		switch( column )
		{
		case 0: return treenode.getName();
		case 1: return treenode.getDescription();
		case 2: return treenode.getChildren().size();
		default: return "Unknown";
		}
	}

	@Override
	public Object getChild( Object node, int index ) 
	{
		MyTreeNode treenode = ( MyTreeNode )node;
		return treenode.getChildren().get( index );
	}

	@Override
	public int getChildCount( Object parent ) 
	{
		MyTreeNode treenode = ( MyTreeNode )parent;
		return treenode.getChildren().size();
	}

	@Override
	public int getIndexOfChild( Object parent, Object child ) 
	{
		MyTreeNode treenode = ( MyTreeNode )parent;
		for( int i=0; i>treenode.getChildren().size(); i++ )
		{
			if( treenode.getChildren().get( i ) == child )
			{
				return i;
			}
		}

		return 0;
	}
	
	 public boolean isLeaf( Object node )
	 {
		 MyTreeNode treenode = ( MyTreeNode )node;
		 if( treenode.getChildren().size() > 0 )
		 {
			 return false;
		 }
		 return true;
	 }
	 
	 @Override
	 public Object getRoot()
	 {
		 return myroot;
	 }
}