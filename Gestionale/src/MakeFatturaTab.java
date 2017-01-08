import it.sanzari.logica.Cliente;
import it.sanzari.logica.Fattura;
import it.sanzari.logica.Impostazioni;
import it.sanzari.logica.Prestazione;
import it.sanzari.logica.StampaFattura;

import java.awt.Button;
import java.awt.EventQueue;

import javax.security.auth.login.Configuration;
import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Choice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.itextpdf.text.pdf.codec.JBIG2SegmentReader;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JDateChooser;

public class MakeFatturaTab implements ActionListener {

	private JFrame frame;
	private JTextField inNome;
	private JTextField inCognome;
	private JTextField inRagagione;
	private JTextField inVia;
	private JTextField inCap;
	private JTextField inCitta;
	private JTextField inProvincia;
	private JTextField inCivico;
	private JTextField inTelefono;
	private JTextField inPiva;
	private ArrayList<Cliente> clienti;
	private ArrayList<Prestazione> prestazioni;
	private DefaultTableModel model;
	private JTable table;
	private JTextField codice;
	private JTextField quantita;
	private JTextField prezzo;
	private JTextArea descrizione;
	private Choice sceltaCliente;
	private JTextField impIva;
	private JTextField impNumeroFattura;
	private JTextField nrFatt;
	private Impostazioni impostazioni;
	private JTextField inPath;
	private JTextField showIVA;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeFatturaTab window = new MakeFatturaTab();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MakeFatturaTab() {
		impostazioni = new Impostazioni();
		clienti = new ArrayList<Cliente>();
		prestazioni = new ArrayList<Prestazione>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelCliente = new JPanel();
		tabbedPane.addTab("Cliente", null, panelCliente, null);
		panelCliente.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cognome *");
		lblNewLabel.setBounds(70, 101, 70, 16);
		panelCliente.add(lblNewLabel);

		JLabel lblNome = new JLabel("Nome *");
		lblNome.setBounds(92, 60, 58, 16);
		panelCliente.add(lblNome);

		JLabel lblRagioneSociale = new JLabel("Ragione Sociale");
		lblRagioneSociale.setBounds(45, 143, 139, 16);
		panelCliente.add(lblRagioneSociale);

		inNome = new JTextField();
		inNome.setBounds(180, 54, 181, 28);
		panelCliente.add(inNome);
		inNome.setColumns(10);

		inCognome = new JTextField();
		inCognome.setBounds(180, 95, 181, 28);
		panelCliente.add(inCognome);
		inCognome.setColumns(10);

		inRagagione = new JTextField();
		inRagagione.setBounds(180, 137, 181, 28);
		panelCliente.add(inRagagione);
		inRagagione.setColumns(10);

		JLabel lblVia = new JLabel("Via *");
		lblVia.setBounds(516, 61, 61, 16);
		panelCliente.add(lblVia);

		JLabel lblCap = new JLabel("C.a.p. *");
		lblCap.setBounds(499, 101, 46, 16);
		panelCliente.add(lblCap);

		JLabel lblCitt = new JLabel("Città *");
		lblCitt.setBounds(504, 143, 46, 16);
		panelCliente.add(lblCitt);

		JLabel lblProvincia = new JLabel("Provincia *");
		lblProvincia.setBounds(480, 183, 70, 16);
		panelCliente.add(lblProvincia);

		inVia = new JTextField();
		inVia.setBounds(584, 55, 148, 28);
		panelCliente.add(inVia);
		inVia.setColumns(10);

		inCap = new JTextField();
		inCap.setBounds(584, 95, 148, 28);
		panelCliente.add(inCap);
		inCap.setColumns(10);

		inCitta = new JTextField();
		inCitta.setBounds(584, 137, 148, 28);
		panelCliente.add(inCitta);
		inCitta.setColumns(10);

		inProvincia = new JTextField();
		inProvincia.setBounds(584, 177, 148, 28);
		panelCliente.add(inProvincia);
		inProvincia.setColumns(10);

		JLabel lblNr = new JLabel("Nr.");
		lblNr.setBounds(744, 61, 61, 16);
		panelCliente.add(lblNr);

		inCivico = new JTextField();
		inCivico.setBounds(786, 55, 61, 28);
		panelCliente.add(inCivico);
		inCivico.setColumns(10);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(82, 183, 61, 16);
		panelCliente.add(lblTelefono);

		inTelefono = new JTextField();
		inTelefono.setBounds(180, 177, 181, 28);
		panelCliente.add(inTelefono);
		inTelefono.setColumns(10);

		JLabel lblPiva = new JLabel("P.Iva/CF *");
		lblPiva.setBounds(80, 222, 79, 16);
		panelCliente.add(lblPiva);

		inPiva = new JTextField();
		inPiva.setBounds(180, 217, 181, 28);
		panelCliente.add(inPiva);
		inPiva.setColumns(10);

		JButton btnInserisciUtente = new JButton("Inserisci/Modifica Cliente");
		btnInserisciUtente.setBounds(351, 310, 240, 29);
		btnInserisciUtente.addActionListener(this);
		btnInserisciUtente.setActionCommand("addUser");
		panelCliente.add(btnInserisciUtente);

		JLabel lblRs = new JLabel("R.S.");
		lblRs.setBounds(950, 510, 23, 16);
		panelCliente.add(lblRs);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(641, 310, 117, 29);
		btnReset.addActionListener(this);
		btnReset.setActionCommand("Reset");
		panelCliente.add(btnReset);

		JPanel panelFattura = new JPanel();
		tabbedPane.addTab("Fattura", null, panelFattura, null);
		panelFattura.setLayout(null);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(762, 25, 61, 16);
		panelFattura.add(lblData);

		JLabel lblNrFattura = new JLabel("Nr. Fattura");
		lblNrFattura.setBounds(29, 25, 80, 16);
		panelFattura.add(lblNrFattura);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(288, 25, 61, 16);
		panelFattura.add(lblCliente);

		sceltaCliente = new Choice();
		sceltaCliente.setBounds(355, 20, 355, 27);
		panelFattura.add(sceltaCliente);
		visualizzaClienti();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 78, 1019, 264);
		panelFattura.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setBounds(66, 370, 61, 16);
		panelFattura.add(lblCodice);

		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setBounds(340, 370, 85, 16);
		panelFattura.add(lblNewLabel_1);

		codice = new JTextField();
		codice.setBounds(29, 398, 134, 28);
		panelFattura.add(codice);
		codice.setColumns(10);

		descrizione = new JTextArea();
		descrizione.setBounds(207, 398, 355, 87);
		descrizione.setLineWrap(true);
		panelFattura.add(descrizione);

		quantita = new JTextField();
		quantita.setBounds(595, 398, 80, 28);
		panelFattura.add(quantita);
		quantita.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Quantità");
		lblNewLabel_2.setBounds(607, 370, 61, 16);
		panelFattura.add(lblNewLabel_2);

		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setBounds(727, 370, 61, 16);
		panelFattura.add(lblPrezzo);

		prezzo = new JTextField();
		prezzo.setBounds(700, 398, 101, 28);
		panelFattura.add(prezzo);
		prezzo.setColumns(10);

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(931, 399, 117, 29);
		btnAggiungi.addActionListener(this);
		btnAggiungi.setActionCommand("addPrestazione");
		panelFattura.add(btnAggiungi);

		JButton btnStampa = new JButton("Genera");
		btnStampa.setBounds(419, 497, 117, 29);
		btnStampa.addActionListener(this);
		btnStampa.setActionCommand("Stampa");
		panelFattura.add(btnStampa);

		nrFatt = new JTextField();
		nrFatt.setEditable(false);
		nrFatt.setBounds(121, 19, 134, 28);
		panelFattura.add(nrFatt);
		nrFatt.setColumns(10);

		JLabel lblRs_1 = new JLabel("R.S.");
		lblRs_1.setBounds(948, 510, 25, 16);
		panelFattura.add(lblRs_1);

		JLabel lblIva_1 = new JLabel("Iva");
		lblIva_1.setBounds(849, 370, 26, 16);
		panelFattura.add(lblIva_1);

		showIVA = new JTextField();
		showIVA.setBounds(825, 398, 85, 28);
		panelFattura.add(showIVA);
		showIVA.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(814, 20, 182, 28);
		Date data= new Date();
		dateChooser.setDate(data);
		panelFattura.add(dateChooser);
		
				JPanel panelImpostazioni = new JPanel();
				tabbedPane.addTab("Impostazioni", null, panelImpostazioni, null);
				panelImpostazioni.setLayout(null);
				
						JLabel lblIva = new JLabel("Iva (%)");
						lblIva.setBounds(94, 45, 39, 16);
						panelImpostazioni.add(lblIva);
						
								impIva = new JTextField();
								impIva.setBounds(145, 39, 134, 28);
								panelImpostazioni.add(impIva);
								impIva.setColumns(10);
								
										JLabel lblNumeroFattura = new JLabel("Numero Fattura");
										lblNumeroFattura.setBounds(35, 102, 98, 16);
										panelImpostazioni.add(lblNumeroFattura);
										
												impNumeroFattura = new JTextField();
												impNumeroFattura.setBounds(145, 96, 134, 28);
												panelImpostazioni.add(impNumeroFattura);
												impNumeroFattura.setColumns(10);
												
														JButton btnSalva = new JButton("Salva");
														btnSalva.setBounds(436, 478, 117, 29);
														btnSalva.addActionListener(this);
														btnSalva.setActionCommand("Salva");
														panelImpostazioni.add(btnSalva);
														
																JLabel lblPercorsoSalvataggio = new JLabel("Percorso Salvataggio");
																lblPercorsoSalvataggio.setBounds(6, 164, 144, 16);
																panelImpostazioni.add(lblPercorsoSalvataggio);
																
																		inPath = new JTextField();
																		inPath.setEditable(false);
																		inPath.setBounds(145, 158, 545, 28);
																		panelImpostazioni.add(inPath);
																		inPath.setColumns(10);
																		JButton btnModifica = new JButton("Modifica");
																		btnModifica.setBounds(702, 159, 117, 29);
																		panelImpostazioni.add(btnModifica);
																		btnModifica.setActionCommand("selectDir");
																		
																				JLabel lblRs_2 = new JLabel("R.S.");
																				lblRs_2.setBounds(943, 510, 30, 16);
																				panelImpostazioni.add(lblRs_2);
																				
																				Choice choiceIva = new Choice();
																				choiceIva.setBounds(196, 40, 83, 27);
																				panelImpostazioni.add(choiceIva);
																				btnModifica.addActionListener(this);

		model = (DefaultTableModel) table.getModel();
		model.addColumn("Codice");
		model.addColumn("Descrizione");
		model.addColumn("Q.tà");
		model.addColumn("Iva %");
		model.addColumn("Prezzo");
		model.addColumn("Importo");

		leggiImpostazioni();
	}

	private void leggiImpostazioni() {

		Properties prop = new Properties();
		InputStream input = null;
		String userHome=System.getProperty("user.home")+File.separator+"Gestionale";
		
		try {
			 File directory = new File(String.valueOf(userHome));
			    if (! directory.exists()){
			        directory.mkdir();
			        FileOutputStream fout = new FileOutputStream(userHome+File.separator+"config.properties");
			        prop.setProperty("pathFile", "");
			        prop.setProperty("numeroFattura", "1");
			        prop.setProperty("iva", "22.0");
			        prop.store(fout, "Configurazioni di DEFAULT settate il");
			    }
			FileInputStream f= new FileInputStream(userHome+File.separator+"config.properties");
			
			prop.load(f);
			System.out.println(prop.getProperty("pathFile"));
			System.out.println(prop.getProperty("numeroFattura"));
			System.out.println(prop.getProperty("iva"));

			impostazioni.setPathSalvataggio(prop.getProperty("pathFile"));
			impostazioni.setNumeroFattura(Integer.parseInt(prop
					.getProperty("numeroFattura")));
			impostazioni.setIva(Double.parseDouble(prop.getProperty("iva")));

			impIva.setText(impostazioni.getIva() + "");
			impNumeroFattura.setText(impostazioni.getNumeroFattura() + "");
			nrFatt.setText(impostazioni.getNumeroFattura() + "");
			showIVA.setText(impostazioni.getIva() + "");

			inPath.setText(impostazioni.getPathSalvataggio());

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void aggiornaImpostazioni() {
		nrFatt.setText(impNumeroFattura.getText());
		impostazioni.setIva(Double.parseDouble(impIva.getText()));
		impostazioni.setNumeroFattura(Integer.parseInt(impNumeroFattura
				.getText()));
		impostazioni.setPathSalvataggio(inPath.getText());

		Properties prop = new Properties();
		
		String userHome=System.getProperty("user.home")+File.separator+"Gestionale";

		try {
	        FileOutputStream fout = new FileOutputStream(userHome+File.separator+"config.properties");

			prop.setProperty("pathFile", impostazioni.getPathSalvataggio());
			prop.setProperty("numeroFattura", impostazioni.getNumeroFattura()
					+ "");
			prop.setProperty("iva", impostazioni.getIva() + "");

			prop.store(fout, "Configurazioni utente salvate il");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		impIva.setText(impostazioni.getIva() + "");
		impNumeroFattura.setText(impostazioni.getNumeroFattura() + "");
		nrFatt.setText(impostazioni.getNumeroFattura() + "");
		showIVA.setText(impostazioni.getIva()+"");
		inPath.setText(impostazioni.getPathSalvataggio());

	}
	
	private void avanzaNrFattura() {
		nrFatt.setText(impNumeroFattura.getText());
		impostazioni.setIva(Double.parseDouble(impIva.getText()));
		impostazioni.setPathSalvataggio(inPath.getText());

		Properties prop = new Properties();
		
		String userHome=System.getProperty("user.home")+File.separator+"Gestionale";

		try {
	        FileOutputStream fout = new FileOutputStream(userHome+File.separator+"config.properties");

			prop.setProperty("pathFile", impostazioni.getPathSalvataggio());
			prop.setProperty("numeroFattura", impostazioni.getNumeroFattura()
					+ "");
			prop.setProperty("iva", impostazioni.getIva() + "");

			prop.store(fout, "Configurazioni utente salvate il");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		impIva.setText(impostazioni.getIva() + "");
		impNumeroFattura.setText(impostazioni.getNumeroFattura() + "");
		nrFatt.setText(impostazioni.getNumeroFattura() + "");
		showIVA.setText(impostazioni.getIva()+"");
		inPath.setText(impostazioni.getPathSalvataggio());

	}

	private void inserisciCliente(Cliente c) {
		clienti.clear();
		clienti.add(c);
		visualizzaClienti();
	}

	private void visualizzaClienti() {
		sceltaCliente.removeAll();
		for (Cliente c : clienti) {
			sceltaCliente.add(c.getCognome() + " " + c.getNome() + " - "
					+ c.getRagioneSociale());
		}

	}

	private void addPrestazione(Prestazione p) {
		if(!verificaPrestazioneAggiunta(p)){
		prestazioni.add(p);
		aggiornaTabella();
		codice.setText("");
		descrizione.setText("");
		quantita.setText("");
		prezzo.setText("");
		showIVA.setText(impostazioni.getIva()+"");
		}
		else{
			JOptionPane.showMessageDialog(null,
					"Codice già presente!!! Nessuna modifica effettuata.");
		}
	}
	
	private boolean verificaPrestazioneAggiunta(Prestazione p){
		boolean trovato=false;
		for(Prestazione prestazione:prestazioni)
			if(prestazione.getCodice()==p.getCodice() && !trovato)
			{
				trovato=true;
			}
		return trovato;
	}

	private void aggiornaTabella() {
		int i = 0;
		for (Prestazione p : prestazioni) {
			model.addRow(new Object[0]);
			model.setValueAt(p.getCodice(), i, 0);
			model.setValueAt(p.getDescrizione(), i, 1);
			model.setValueAt(p.getQuantita(), i, 2);
			model.setValueAt(p.getIva(), i, 3);
			model.setValueAt(p.getPrezzo(), i, 4);
			model.setValueAt(p.getImporto(), i, 5);
			i++;
		}
	}

	public boolean verificaCampiUserObbligatori() {
		if (!inNome.getText().isEmpty() && !inCognome.getText().isEmpty()
				&& !inCap.getText().isEmpty() && !inCitta.getText().isEmpty()
				&& !inPiva.getText().isEmpty()
				&& !inProvincia.getText().isEmpty() && !inVia.getText().isEmpty())
			return true;
		else
			return false;
	}

	public void aggiornaPath(String path) {
		inPath.setText(path);
		impostazioni.setPathSalvataggio(path);

	}
	
	public void resetDatiNextFattura(){
		prestazioni.clear();
		for(int i=0; i<model.getRowCount();i++){
			model.removeRow(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "addUser") {
			System.out.println("Aggiungo Cliente");
			if (verificaCampiUserObbligatori()){
				try{
				Cliente c= new Cliente(
						inNome.getText(),
						inCognome.getText(),
						inRagagione.getText(),
						inVia.getText(), inCivico.getText(),
						inCitta.getText(), inProvincia.getText(),
						inTelefono.getText(),inPiva.getText(), inCap.getText());
				
				inserisciCliente(c);
				}
				catch(Exception exc){
				}
				
			JOptionPane.showMessageDialog(null,"Intestazione fattura inserita correttamente");}
			else
				JOptionPane.showMessageDialog(null,"Inserisci tutti i campi con *");
			
			
		} else if (e.getActionCommand() == "addPrestazione") {
			System.out.println("Aggiungo prestazione");
			try {
				if(!codice.getText().isEmpty() && !descrizione.getText().isEmpty() && !quantita.getText().isEmpty() && !showIVA.getText().isEmpty()
						&& !prezzo.getText().isEmpty()){
				Prestazione p = new Prestazione(Integer.parseInt(codice
						.getText()), descrizione.getText(),
						Double.parseDouble(quantita.getText()),
						Double.parseDouble(showIVA.getText()),
						Double.parseDouble(prezzo.getText()),
						Double.parseDouble(prezzo.getText())
								* Double.parseDouble(quantita.getText()));
				addPrestazione(p);}
				else{
					JOptionPane.showMessageDialog(null,
							"Compilate tutti i campi!");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Attenzione inseriti valori non numerici");
			}
		} else if (e.getActionCommand() == "Stampa") {
			System.out.println("Stampo Fattura");
			boolean clienteOk=false;
			boolean prestazioniOk=false;
			if(clienti.size()>0){
				clienteOk=true;
			}
			else
				JOptionPane.showMessageDialog(null,"Stampa ANNULLATA. Selezionare intestatario fattura");
			if(prestazioni.size()>0){
				prestazioniOk=true;
			}
			else
				JOptionPane.showMessageDialog(null,"Stampa ANNULLATA. Inserire elementi da fatturare");
			
			if(prestazioniOk && clienteOk){
				Cliente c=clienti.get(0);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Fattura f = new Fattura(c, prestazioni,Integer.parseInt(nrFatt.getText()), sdf.format(dateChooser.getDate()));
				Date data= new Date();
				String nomeFile=c.getCognome()+"_"+c.getNome()+"_"+data.toString();
				nomeFile=nomeFile.replaceAll(" ", "_");
			StampaFattura stampa = new StampaFattura(nomeFile.replaceAll(":", "_"), f,impostazioni.getPathSalvataggio());
			stampa.createPDF();
			JOptionPane.showMessageDialog(null,"Fattura generata correttamente. Nuovo numero progressivo: "+(impostazioni.getNumeroFattura()+1));
			impostazioni.setNumeroFattura((impostazioni.getNumeroFattura()+1));
			avanzaNrFattura();
			resetDatiNextFattura();
			}
		} else if (e.getActionCommand() == "Salva") {
			System.out.println("Salvo Impostazioni");
			aggiornaImpostazioni();
			JOptionPane.showMessageDialog(null,"Configurazioni aggiornate correttamente.");
		} else if (e.getActionCommand() == "selectDir") {
			System.out.println("Seleziono Dir");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Percorso di salvataggio Fatture");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int n = fileChooser.showOpenDialog(fileChooser);
			if (n == JFileChooser.APPROVE_OPTION) {
				File path = fileChooser.getCurrentDirectory();
				String percorso = path.getPath();
				aggiornaPath(percorso);

			}
		}
		else if(e.getActionCommand()== "Reset"){
			clienti.clear();
			inNome.setText("");
			inCognome.setText("");
			inRagagione.setText("");
			inVia.setText("");
			inCivico.setText("");
			inCitta.setText("");
			inProvincia.setText("");
			inTelefono.setText("");
			inPiva.setText("");
			inCap.setText("");
			visualizzaClienti();
		}
		

	}
}
