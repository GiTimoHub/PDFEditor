package core;

import java.util.LinkedList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFStorer {
	
	private LinkedList<PDDocument> pdflist;
	
	public PDFStorer() {
		pdflist = new LinkedList<PDDocument>();
	}
	
	public void addDocument(PDDocument doc) {
		pdflist.addLast(doc);
	}
	
	public PDDocument getFreshest() {
		return pdflist.getLast();
	}
	
	public void exchange(int first, int second) {
		PDDocument firstDoc = pdflist.get(first);
		PDDocument secondDoc = pdflist.get(second);
		pdflist.add(first + 1, secondDoc);
		pdflist.remove(first);
		pdflist.remove(second);
		if (second <= pdflist.size()) {
			pdflist.add(second, firstDoc);
		} else {
			pdflist.addLast(secondDoc);
		}				
	}
	
	public void removeDocument(int number) {
		pdflist.remove(number);
	}
	
	public void removePage(int number, int page) {
		pdflist.get(number).removePage(page);
	}
	
	public void cutDocument(int number, int firstPage, int lastPage) {
		PDDocument doc = pdflist.get(number);		
		for(int i = lastPage + 1; i < doc.getNumberOfPages(); i++) {
			doc.removePage(i);
		}
		for(int i = 0; i < firstPage; i++) {
			doc.removePage(i);
		}		
	}
	
	public void cutDocument(int number, int [] pages) {
		PDDocument doc = pdflist.get(number);	
		int c = pages.length - 1;
		for (int i = doc.getNumberOfPages(); i >= 0; i--) {
			if(pages[c] != i || c < 0) {
				doc.removePage(i);
			} else {
				c--;
			}
		}
	}
	
	public PDDocument fuse(int first, int last) {
		PDDocument fused = new PDDocument();
		for (int i = first; i <= last; i++) {
			for(PDPage page : pdflist.get(i).getPages()) {
				fused.addPage(page);
				System.out.println("Fused " + i);
			}
		}
		return fused;
	}
	
	public PDDocument fuse(int [] documents) {
		return new PDDocument();
	}
	
	public PDDocument fuse() {
		return this.fuse(0, pdflist.size() - 1);
	}
}
