package it.sanzari.logica;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class StampaFattura {

	private String nomeFile;
	private Fattura fattura;
	private BaseFont bfBold;
	private BaseFont bf;
	private int pageNumber = 0;
	private String path;
	private String pathFolder;

	public StampaFattura(String nomeFile, Fattura fattura,String pathFolder) {
		this.nomeFile = nomeFile;
		this.fattura=fattura;
		this.pathFolder=pathFolder;

	}

	public void createPDF() {

		Document doc = new Document();
		PdfWriter docWriter = null;
		initializeFonts();

		try {
		    path = pathFolder+ "/" + nomeFile.trim()+".pdf";
			docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.addAuthor("Raffaele Sanzari");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("Gestionale Raffaele Sanzari");
			doc.addTitle("Fattura");
			doc.setPageSize(PageSize.LETTER);

			doc.open();
			PdfContentByte cb = docWriter.getDirectContent();

			boolean beginPage = true;
			int y = 0;
			int i = 0;
			
			for(Prestazione p:fattura.getPrestazioni()){
				if (beginPage) {
					beginPage = false;
					generateLayout(doc, cb);
					generateHeader(doc, cb);
					y = 615;
				}
				y=generateDetail(doc, cb, i, y, p); // ritorna la posizione del nuovo righo
				y = y - 15;
				if (y < 50) {
					printPageNumber(cb);
					doc.newPage();
					beginPage = true;
				}
				i++;
			}

//			for (int i = 0; i < 100; i++) {
//				if (beginPage) {
//					beginPage = false;
//					generateLayout(doc, cb);
//					generateHeader(doc, cb);
//					y = 615;
//				}
//				generateDetail(doc, cb, i, y);
//				y = y - 15;
//				if (y < 50) {
//					printPageNumber(cb);
//					doc.newPage();
//					beginPage = true;
//				}
//			}
			printPageNumber(cb);

		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
			File myFile = new File(path);
	        try {
				Desktop.getDesktop().open(myFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void generateLayout(Document doc, PdfContentByte cb) {

		try {

			cb.setLineWidth(1f);

			// Invoice Header box layout  x-y; Larghezza-Altezza
			cb.roundRectangle(30, 655, 240, 20,10);	// rettagolo data e numero
			
			cb.stroke();
			cb.setLineWidth(3f);
			cb.moveTo(440, 655);
			cb.lineTo(30, 655);
			cb.stroke();
			cb.setLineWidth(1f);
			cb.moveTo(440, 651);
			cb.lineTo(30, 651);
			
			createHeadings(cb, 453, 651, "FATTURA");
			cb.stroke();
			cb.setLineWidth(3f);
			cb.moveTo(580, 655);
			cb.lineTo(500, 655);
			cb.stroke();
			cb.setLineWidth(1f);
			cb.moveTo(580, 651);
			cb.lineTo(500, 651);
			
			
			
//			cb.stroke();
//			cb.setLineWidth(1f);
			
			fattura.calcolaTotali();
			cb.rectangle(525, 130, 55, 20);	// rettagolo totali 
			cb.rectangle(465, 110, 115, 20);	// rettagolo totali 
			createHeadings(cb, 485, 116, "IVA");
			createHeadings(cb, 529, 116, "€ "+fattura.getTotaleIva());
			cb.rectangle(465, 90, 115, 20);	// rettagolo totali 
			createHeadings(cb, 469, 96, "Totale Fattura");
			createHeadings(cb, 529, 96, "€ "+(fattura.getTotaleFattura()+fattura.getTotaleIva()));
			
			cb.roundRectangle(50, 60, 220, 75, 10); // rettangolo modalita pagamenti
			createHeadings(cb, 60, 125, "Modalità di pagamento");
			

			// Invoice Header box Text Headings
			createHeadings(cb, 35, 660, "Fattura Nr.");
			createHeadings(cb, 142, 660, "Data Fattura");

			// Invoice Detail box layout
			cb.rectangle(30, 150, 550, 495);
			
			cb.moveTo(30, 630);
			cb.lineTo(580, 630);
			
			cb.moveTo(70, 150);
			cb.lineTo(70, 645);
			
			cb.moveTo(390, 150);
			cb.lineTo(390, 645); //posizione - lunghezza
			
			cb.moveTo(430, 150);
			cb.lineTo(430, 645);
			
			cb.moveTo(465, 150);
			cb.lineTo(465, 645);
			
			cb.moveTo(525, 90);
			cb.lineTo(525, 645);
			
			cb.stroke();

			// Invoice Detail box Text Headings
			createHeadings(cb, 35, 633, "Codice");
			createHeadings(cb, 200, 633, "Descrizione");
			createHeadings(cb, 400, 633, "Q.ta");
			createHeadings(cb, 440, 633, "Iva");
			createHeadings(cb, 482, 633, "Prezzo");
			createHeadings(cb, 532, 633, "Importo");

			// add the images
			Image companyLogo = Image.getInstance("images/logo.png");
			companyLogo.setAbsolutePosition(25, 700);
			companyLogo.scalePercent(15);
			doc.add(companyLogo);

		}

		catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void generateHeader(Document doc, PdfContentByte cb) {

		try {
			
			createHeadings(cb, 300, 750, "Nome: ");
			compilaHeadings(cb, 355, 750, fattura.getCliente().getIntestazione());
			createHeadings(cb, 300, 735, "Indirizzo: ");
			compilaHeadings(cb, 355, 735, fattura.getCliente().getIndirizzo());
			createHeadings(cb, 300, 705, "P.Iva / C.F.");
			compilaHeadings(cb, 355, 705, fattura.getCliente().getpIva());
			createHeadings(cb, 300, 720, "Città - CAP: ");
			compilaHeadings(cb, 355, 720, fattura.getCliente().getCitta()+" "+fattura.getCliente().getCap());
			createHeadings(cb, 480, 720, "Prov.");
			compilaHeadings(cb, 510, 720, fattura.getCliente().getProvincia());
			createHeadings(cb, 300, 690, "Telefono");
			compilaHeadings(cb, 355, 690, fattura.getCliente().getTelefono());

			createHeadings(cb, 90, 660, fattura.getNumero()+"");
			createHeadings(cb, 200, 660, fattura.getData());

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private ArrayList<String> suddividiDescrizione(String descrizione, int lunghezza){
		ArrayList<String> subDescrizioni= new ArrayList<String>();
		int tmpEnd=lunghezza;
		for(int i=0;i<descrizione.length();){
			String temp="";
			tmpEnd=lunghezza;
			boolean lunghezzaTmpOK=false;
			while(!lunghezzaTmpOK){
				int controlloSpazio;
				if((i+tmpEnd)>descrizione.length()){
					controlloSpazio=descrizione.length()-1;
					tmpEnd=descrizione.codePointCount(i, descrizione.length()-1);
					}
				else
					controlloSpazio=i+tmpEnd;
			if(descrizione.charAt(controlloSpazio)!=' ')
				tmpEnd--;
			else
				lunghezzaTmpOK=true;
				
			}
			
			temp=descrizione.subSequence(i, i+tmpEnd)+"";
			subDescrizioni.add(temp);
			i=i+tmpEnd+1;
		}
		return subDescrizioni;
	}

	private int generateDetail(Document doc, PdfContentByte cb, int index,
			int y, Prestazione p) {
		DecimalFormat df = new DecimalFormat("0.00");

		try {

			createContent(cb, 48, y, String.valueOf(index + 1),
					PdfContentByte.ALIGN_RIGHT);
			createContent(cb, 395, y, p.getQuantita()+"",
					PdfContentByte.ALIGN_LEFT);
			
			createContent(cb, 438, y,p.getIva()+" % ",PdfContentByte.ALIGN_LEFT);

			
			createContent(cb, 498, y, df.format(p.getPrezzo()),PdfContentByte.ALIGN_RIGHT);
			createContent(cb, 568, y, df.format((p.getPrezzo()*p.getQuantita())),PdfContentByte.ALIGN_RIGHT);

			
			if(p.getDescrizione().length()>20){
				ArrayList<String> subDescrizione=suddividiDescrizione(p.getDescrizione()+" ", 75);
				for(String desc:subDescrizione){
			createContent(cb, 88, y,desc,PdfContentByte.ALIGN_LEFT);
			y=y-15;
			}
			}
			else{
				createContent(cb, 88, y,p.getDescrizione() ,PdfContentByte.ALIGN_LEFT);

			}
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return y;
	}

	private void createHeadings(PdfContentByte cb, float x, float y, String text) {

		cb.beginText();
		cb.setFontAndSize(bfBold, 8);
		cb.setTextMatrix(x, y);
		cb.showText(text.trim());
		cb.endText();

	}
	
	private void compilaHeadings(PdfContentByte cb, float x, float y, String text) {

		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(x, y);
		cb.showText(text.trim());
		cb.endText();

	}

	private void printPageNumber(PdfContentByte cb) {

		cb.beginText();
		cb.setFontAndSize(bfBold, 8);
		cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Pag Nr. "
				+ (pageNumber + 1), 570, 25, 0);
		cb.endText();

		pageNumber++;

	}

	private void createContent(PdfContentByte cb, float x, float y,
			String text, int align) {

		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.showTextAligned(align, text.trim(), x, y, 0);
		cb.endText();

	}

	private void initializeFonts() {

		try {
			bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD,
					BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
					BaseFont.NOT_EMBEDDED);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
