package org.uva.sea.ql.gui;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.uva.sea.ql.ExportToXML;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.widget.BooleanWidget;
import org.uva.sea.ql.gui.widget.ComputedQuestionWidget;
import org.uva.sea.ql.gui.widget.FieldWidget;
import org.uva.sea.ql.gui.widget.Widget;
import org.uva.sea.ql.type.BoolType;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class QlGUI extends JFrame {
	private JPanel contentPane;
	private List<Widget> widgets;
	private FormDataManager dataManager;
	private Form form;
	
	static String FA = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt";
	
	public QlGUI(Form form, FormDataManager dataManager) {
		this.form = form;
		this.widgets = new ArrayList<Widget>();
		this.dataManager = dataManager;
		initializeGUI(form.getQuestions());
		
	}
	
	private Box initializeFormComponents(List<Question> components) {
		// add the components
		Box questionsContainer = Box.createVerticalBox();
	
		for (Question question : components) {
			if (question instanceof ComputedQuestion) { //TODO: get rid of this uglyness!!!!!!!
				Widget w = new ComputedQuestionWidget((ComputedQuestion) question, questionsContainer, dataManager);
				widgets.add(w);
				dataManager.attach(w);
			} else if (question.getType().equals(new BoolType())) {
				Widget w = new BooleanWidget(question, questionsContainer, dataManager);
				widgets.add(w);
				dataManager.attach(w);
			} else {
				Widget w = new FieldWidget(question, questionsContainer, dataManager);
				widgets.add(w);
				dataManager.attach(w);
			}
		}
		
		return questionsContainer;
	}
	
	private void initializeGUI(List<Question> components) {
		initializeFrame();
		contentPane.add(initializeFormComponents(components));
		contentPane.add(Box.createRigidArea(new Dimension(20,contentPane.getWidth())));
		contentPane.add(initializeControls());
		// set visibility
		dataManager.notifyAllObserversOnVisibility();
	}
	
	private void initializeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	private Box initializeControls() {
		Box controlBox = Box.createHorizontalBox();
		JButton exportButton = new JButton("Export to XML");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    // disable the "All files" option.
				fileChooser.setAcceptAllFileFilterUsed(false);
				int returnVal = fileChooser.showOpenDialog(contentPane);
				
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	File curDir = fileChooser.getCurrentDirectory();
	                ExportToXML xmlExporter = new ExportToXML(form, dataManager.getValueState());
	                xmlExporter.outputToXML(curDir);     
	            } else {
	                
	            }
			}
		});
		
		JButton importButton = new JButton("Import file");
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// import a file.
			}
		});
		
		controlBox.add(importButton);
		controlBox.add(exportButton);
		return controlBox;
	}
	
	
}
