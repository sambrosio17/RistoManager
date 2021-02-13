package it.RistoManager.FIA;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;
import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.PrincipalComponents;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.Standardize;

public class KMeansExecutor {

	// I valori di SEED e NUM_CLUSTERS sono stati scelti in base alle prove
	// effettuate in KMeansEvaluator
	private static final int SEED = 3;
	public static final int NUM_CLUSTERS = 6;

	// Dataset
	public static final String fileName = "C:\\Users\\simon\\Desktop\\UNISA\\2° Anno\\2° Semestre\\TSW\\Workspaces\\Workspace Progetto\\RistoManager\\Datasets\\menuFinale.arff";

	// Istanze contenenti solo i dati numerici
	private static Instances data;
	// Istanze contenenti anche il nome
	private static Instances items;

	private static ProdottoDAO dao;
	// Lista di Prodotti
	private static ArrayList<ProdottoBean> prodotti;
	//l'incremento della probabilità che i prodotti di un cluster possano essere consigliati ad un cliente
	public final static int increment = (NUM_CLUSTERS - 1) * 5;
	
	// Associa ad ogni riga del dataset il numero del cluster
	private static int[] assignments;
	// Associa ad ogni riga del dataset l'id del prodotto nel database
	private static int[] associations;

	
	static {
		try {
			// Istanze del dataset con i valori discreti
			BufferedReader datafile = readDataFile(fileName);
			items = new Instances(datafile);

			// Scelta dell'attributo da rimuovere (0 = categoria; 1 = nome del prodotto;
			// numAttributes - 1 = prezzo)
			int[] attributes = new int[3];
			attributes[0] = 0;
			attributes[1] = 1;
			attributes[2] = items.numAttributes() - 1;

			// Filtro di rimozione degli attributi sopra indicati
			Remove remove = new Remove();
			remove.setAttributeIndicesArray(attributes);
			remove.setInvertSelection(false);
			remove.setInputFormat(items);
			data = Filter.useFilter(items, remove);

			//Filtro di standardizzazione dei dati
			Standardize standardize = new Standardize();
			standardize.setInputFormat(data);
			data = Filter.useFilter(data, standardize);

			// Filtro PCA (Gli attributi da 16 passano ad 8)
			PrincipalComponents pca = new PrincipalComponents();
			pca.setMaximumAttributeNames(5);
			pca.setInputFormat(data);
			data = Filter.useFilter(data, pca);
			
			prodotti = new ArrayList<ProdottoBean>();
			dao = new ProdottoDAO();
			associations = new int[items.numInstances()];

			getAllProdotti();
			
			createClusters();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private KMeansExecutor() {
		
	}
	
	public static int[] createClusters() throws Exception {

		EuclideanDistance distance = new EuclideanDistance();
		distance.setDontNormalize(true);

		SimpleKMeans kmeans = new SimpleKMeans();
		kmeans.setDistanceFunction(distance);
		kmeans.setSeed(SEED);
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(NUM_CLUSTERS);

		kmeans.buildClusterer(data);

		// Associa ad ogni prodotto il numero del cluster
		// L'indice dell'array verrà utilizzato come identificativo del prodotto
		assignments = kmeans.getAssignments();
		return assignments;
	}

	public static ProdottoBean getProdottoByLine(int line) {
		if (line >= items.numInstances() || line < 0)
			return null;

		Instance itemInstance = items.instance(line);
		String nome = itemInstance.stringValue(1);
		ProdottoBean p;
		try {
			p = dao.retrieveByNome(nome);

			if (p == null)
				p = createProdotto(itemInstance);
			else
				p = updateProdotto(p, itemInstance);

			p.setLine(line);
			associations[line] = p.getId();

			return p;
		} catch (SQLException e) {
			System.err.println("SQL Error");
			p = new ProdottoBean();
			p.setNomeprodotto(nome);
			p.setLine(line);
			return p;
		}
	}

	public static ProdottoBean getProdottoById(int id) {
//		getAssociations();

		int n = associations.length;
		int line = -1;
		for (int i = 0; i < n; i++)
			if (associations[i] == id)
				line = i;

		return prodotti.get(line);
	}
	
	public static int getLineById(int id) {
		for(int line = 0; line < associations.length; line++)
			if(id == associations[line])
				return line;
		
		return -1;
	}

	public static ArrayList<ProdottoBean> getAllProdotti() {
		if (prodotti.size() == 0) {
	
			int n = items.numInstances();
			for (int i = 0; i < n; i++) {
				ProdottoBean p = getProdottoByLine(i);
				prodotti.add(p);
			}

		}
		return prodotti;
	}

	public static ArrayList<ProdottoBean> getProdottiByCategoria(String categoria) {
		if (prodotti.size() == 0) {
			getAllProdotti();
		}
		
		ArrayList<ProdottoBean> prodottiCategoria = new ArrayList<ProdottoBean>();
		int n = prodotti.size();

		for (ProdottoBean p : prodotti) {
			// Seleziona solo quelli in cui la categoria coincide
			if (p.getCategoria().equalsIgnoreCase(categoria)) {
				prodottiCategoria.add(p);
			}
		}

		return prodottiCategoria;
	}

	public static ArrayList<ProdottoBean> getCluster(int clusterNumber) {
		ArrayList<ProdottoBean> cluster = new ArrayList<ProdottoBean>();
		int n = items.numInstances();
		for (int i = 0; i < n; i++) {
			if (assignments[i] == clusterNumber) {
				ProdottoBean p = prodotti.get(i);
				cluster.add(p);
			}
		}

		return cluster;
	}

	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;

		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}

		return inputReader;
	}

	public static int[] getAssociations() {
		getAllProdotti();
		return associations;
	}

	public static int[] getAssignments() {
		getAllProdotti();
		return assignments;
	}
	
	public static ProdottoBean createProdotto(Instance itemInstance) throws SQLException {
		// Salva in p le informazioni tracciate dal database e chiama create
		ProdottoBean p = new ProdottoBean();

		p.setNomeprodotto(itemInstance.stringValue(1));
		p.setCategoria(itemInstance.stringValue(0));
		p.setPrezzo((float) itemInstance.value(itemInstance.numAttributes() - 1));

		dao.create(p);

		return updateProdotto(p, itemInstance);
	}

	public static ProdottoBean updateProdotto(ProdottoBean p, Instance itemInstance) {
		// Salva in p le informazioni contenute solo nel dataset
		int[] valori = new int[15];
		for (int i = 0; i <= 14; i++)
			valori[i] = (int) itemInstance.value(i + 2);

		p.setValori(valori);
		p.setSize(itemInstance.value(17));
		return p;
	}
}
