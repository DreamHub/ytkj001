package com.ytkj.ytkj001.UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ytkj.ytkj001.tool.Tool;
import com.ytkj.ytkj001.tool.UIStyleTransform;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		UIStyleTransform.initUI(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 258);
		Tool.showFrameCenter(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(46, 34, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(110, 31, 110, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(46, 78, 54, 15);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 75, 110, 21);
		contentPane.add(passwordField);
		
		JLabel label_2 = new JLabel("登录类型");
		label_2.setBounds(46, 120, 54, 15);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 117, 84, 21);
		comboBox.addItem("普通用户");
		comboBox.addItem("管理员");
		contentPane.add(comboBox);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		button.setBounds(113, 166, 93, 23);
		contentPane.add(button);
	}
}
