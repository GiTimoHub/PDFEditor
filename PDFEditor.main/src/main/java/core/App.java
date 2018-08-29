package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;


public class App {
	
	private String setStorePath;
	private String setLoadPath;
	private LinkedList<Integer> selectedDocuments;
	private LinkedList<Integer> selectedPages;
	private int selectedFile;
	private PDFStorer storer;
	private PDDocument currentResult;
	
	public App () {
		storer = new PDFStorer();
		selectedDocuments = new LinkedList<Integer>();
		selectedPages = new LinkedList<Integer>();
		selectedFile = -1;
	}
	
	
	public void setStorePath(String storePath) {
		this.setStorePath = storePath;
	}
	
	public void setLoadPath(String loadPath) {
		this.setLoadPath = loadPath;
	}
	
	public void load() {
		try {
			PDDocument loaded = PDDocument.load(new File(setLoadPath));
			storer.addDocument(loaded);
			if (currentResult == null) {
				currentResult = loaded;
			}
			selectedFile++;
			
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Wrong Path");
		}
	}
	
	public void save() {
		if (currentResult != null) {
			try {
				System.out.println("Saved at " + setStorePath);
				File f = new File(setStorePath);
				f.createNewFile();
				currentResult.save(f);
				System.out.println("Saved at " + setStorePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removePages() {
		for (int i = selectedPages.size() - 1 ; i >= 0; i--) {
			storer.removePage(selectedFile, selectedPages.get(i));
		}
		currentResult = storer.getFreshest();
	}
	
	public void removeDocs() {
		LinkedList<Integer> cl = new LinkedList<Integer>();
		cl.addAll(selectedDocuments);
		storer.removeDocument(cl.pollLast());
		selectedFile =- selectedDocuments.size();
	}
	
	public void fuseDocs() {		
		currentResult = storer.fuse(toIntArray(selectedDocuments));
	}
	
	public void fuseAll() {
		currentResult = storer.fuse();
	}
	
	private int[] toIntArray(List<Integer> list){
		  int[] ret = new int[list.size()];
		  for(int i = 0;i < ret.length;i++)
		    ret[i] = list.get(i);
		  return ret;
	}
	
	public void updateSelectedPages(int [] selected) {
		selectedPages.clear();
		for (int i : selected) {
			selectedPages.add(i);
			System.out.println(i);
		}
	}
	
    public void updateSelectedDocs(int [] selected) {
		selectedDocuments.clear();
		for (int i : selected) {
			selectedDocuments.add(i);
		}
	}
}
