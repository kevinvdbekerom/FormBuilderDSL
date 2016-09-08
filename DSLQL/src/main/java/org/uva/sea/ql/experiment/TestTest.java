package org.uva.sea.ql.experiment;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestTest {

	private JFrame frame;
	private JTextField txtDjfdskjfjdf;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestTest window = new TestTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblBlabal = new JLabel("Blabal");
		horizontalBox.add(lblBlabal);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnDfdfd = new JButton("dfdfd");
		btnDfdfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		horizontalBox.add(btnDfdfd);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea);
		
		txtDjfdskjfjdf = new JTextField(20);
		txtDjfdskjfjdf.setText("djfdskjfjdf");
		horizontalBox.add(txtDjfdskjfjdf);
		txtDjfdskjfjdf.setColumns(10);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		horizontalBox_1.add(lblNewLabel);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_1);
		
		textField = new JTextField(20);
		textField.setMaximumSize(new Dimension(500,20));
		horizontalBox_1.add(textField);
		textField.setColumns(5);
	}

}
