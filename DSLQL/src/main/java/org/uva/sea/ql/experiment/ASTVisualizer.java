package org.uva.sea.ql.experiment;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.uva.sea.ql.ast.QuestionPainter;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.expr.Type;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;

public class ASTVisualizer extends JFrame {

	public JPanel contentPane;
	private JTextField textField;
	private int gridRow = 0;
	private int gridColumn = 0;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ASTVisualizer frame = new ASTVisualizer();
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
	public ASTVisualizer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		/*
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 0;
		contentPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		contentPane.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 1;
		gbc_rdbtnNewRadioButton_2.gridy = 2;
		contentPane.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblDidYouSell = new JLabel("did you sell a house in 2020?");
		GridBagConstraints gbc_lblDidYouSell = new GridBagConstraints();
		gbc_lblDidYouSell.insets = new Insets(0, 0, 5, 5);
		gbc_lblDidYouSell.gridx = 0;
		gbc_lblDidYouSell.gridy = 0;
		contentPane.add(lblDidYouSell, gbc_lblDidYouSell);
		
		JRadioButton rdbtnMarkIfTrue = new JRadioButton("mark if true");
		GridBagConstraints gbc_rdbtnMarkIfTrue = new GridBagConstraints();
		gbc_rdbtnMarkIfTrue.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMarkIfTrue.gridx = 1;
		gbc_rdbtnMarkIfTrue.gridy = 0;
		contentPane.add(rdbtnMarkIfTrue, gbc_rdbtnMarkIfTrue);
		*/
	
	}
	
	public void drawQuestions(Form f, JPanel contentPane) {
		QuestionPainter qp = new QuestionPainter();
		f.getMainBlock().accept(qp);
		
		int gridRow = 0;
		int gridColumn;
		
		for (Question q : qp.getQuestions()) {
			gridColumn = 0; // use ++ construction to increase the column value for each question component.
			
			//Question Label
			System.out.println("new label on grid r/c " + gridRow + " " + gridColumn);
			JLabel label = new JLabel(q.getLabel());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 0, 5, 5);
			gbc.gridx = gridColumn;
			gbc.gridy = gridRow;
			contentPane.add(label, gbc);
			gridColumn ++;
			
			//Question Input Field
			switch(q.getType()) {
				case BOOLEAN : {
					// build radio button
					System.out.println("new radio buitton on grid r/c " + gridRow + " " + gridColumn);
					JRadioButton rb = new JRadioButton("Mark if true");
					GridBagConstraints gbc_rb = new GridBagConstraints();
					gbc_rb.insets = new Insets(0, 0, 5, 5);
					gbc_rb.gridx = gridColumn;
					gbc_rb.gridy = gridRow;
					contentPane.add(rb, gbc_rb);
					gridColumn ++;
					break;
				}
				default : {
					// build text field
					System.out.println("new text field on grid r/c " + gridRow + " " + gridColumn);
					JTextField textField = new JTextField();
					GridBagConstraints gbc_tf = new GridBagConstraints();
					gbc_tf.insets = new Insets(0, 0, 5, 5);
					gbc_tf.fill = GridBagConstraints.HORIZONTAL;
					gbc_tf.gridx = gridColumn;
					gbc_tf.gridy = gridRow;
					contentPane.add(textField, gbc_tf);
					textField.setColumns(10);
					gridColumn ++;
				}
			}
			gridRow++;
		}
	}
	
	
	
	
}
