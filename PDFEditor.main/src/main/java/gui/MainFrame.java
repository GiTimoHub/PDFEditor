package gui;

import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import core.App;
import interpreter.IntervalInterpreter;

public class MainFrame extends JFrame {
	
	private App a;
	private LayoutManager layout;
	private PageSelectionPanel pageSelection;
	private PDFSelectionPanel pdfSelection;
	private JButton loadButton;
	private JButton saveButton;
	private JButton mergeButton;
	private JButton cutButton;
	private JButton removeDocButton;
	private JButton removePageButton;
	private JTextField selectedDocs;
	private JTextField selectedPages;
	
	
	
	
	
	public MainFrame(App a) {
		this.a = a;
		this.layout = new GridLayout();
		this.setLayout(layout);
		loadButton = new JButton("load");
		loadButton.addActionListener(e -> {
			FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
			fd.setDirectory("C:\\");
			fd.setFile("*.pdf");
			fd.setVisible(true);
			String filename = fd.getFiles()[0].getPath();
			System.out.println("You chose " + filename);
			a.setLoadPath(filename);
			a.load();
		});
		this.add(loadButton);
		/////////////////////////////////////////////////////////////////////////////////
		removeDocButton = new JButton("removeDoc");
		removeDocButton.addActionListener(e -> {
			a.removeDocs();
		});		
		this.add(removeDocButton);
		////////////////////////////////////////////////////////////////////////////////		
		selectedDocs = new JTextField("");
		selectedDocs.addActionListener(e -> {
			try {
				a.updateSelectedDocs(new IntervalInterpreter(selectedDocs.getText()).interpret());
			} catch (Exception e1) {
				System.out.println("Wrong input");
			}
		});
		this.add(selectedDocs);
		////////////////////////////////////////////////////////////////////////////////
		mergeButton = new JButton("merge");
		
		mergeButton.addActionListener(e -> {
				a.fuseAll();
		});
		this.add(mergeButton);
		////////////////////////////////////////////////////////////////////////
		saveButton = new JButton("save");
		saveButton.addActionListener(e -> {
			FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
			fd.setDirectory("C:\\");
			fd.setFile("*.pdf");
			fd.setVisible(true);
			String filename = fd.getDirectory() + fd.getFile();
			  System.out.println("You chose " + filename);
			a.setStorePath(filename);
			a.save();
		});
		this.add(saveButton);
		///////////////////////////////////////////////////////////////////////////////////////////
		cutButton = new JButton("cut");
		cutButton.addActionListener(e -> {
			a.removePages();
		});
		this.add(cutButton);
		//////////////////////////////////////////////////////////////////////////////////////////
		selectedPages = new JTextField("");
		selectedPages.addActionListener(e -> {
			try {
				a.updateSelectedPages(new IntervalInterpreter(selectedPages.getText()).interpret());
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Wrong input");
			}
		});
		this.add(selectedPages);
		////////////////////////////////////////////////////////////////////////////////////////////
		removePageButton = new JButton("removePage");
		removePageButton.addActionListener(e -> {
			System.out.println("Test");
			a.removePages();
			
		});
		this.add(removePageButton);
		
		
		this.pack();
	}
	
}
