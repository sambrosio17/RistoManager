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
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.Standardize;

public class KMeansExecutor {

	// I valori di SEED e NUM_CLUSTERS sono stati scelti in base alle prove
	// effettuate in KMeansEvaluator
	private static final int SEED = 0;
	public static final int NUM_CLUSTERS = 5;

	// Dataset
//	private static final String fileName = "./Datasets/menuFinale.arff";
	public static final String fileName = "C:\\Users\\simon\\Desktop\\UNISA\\2° Anno\\2° Semestre\\TSW\\Workspaces\\Workspace Progetto\\RistoManager\\Datasets\\menuFinale.arff";
	// Istanze contenenti solo i dati numerici
	private Instances data;
	// Istanze contenenti anche il nome
	private Instances items;

	private ProdottoDAO dao;

	// Associa ad ogni riga del dataset il numero del cluster
	int[] assignments;
	// Associa ad ogni riga del dataset il numero del cluster
	int[] associations;

	public KMeansExecutor() throws Exception {
		// Istanze del dataset con i valori discreti
		BufferedReader datafile = readDataFile(fileName);
		items = new Instances(datafile);

		// Scelta dell'attributo da rimuovere (0 = categoria; 1 = nome del prodotto; numAttributes - 1 = prezzo)
		int[] attributes = new int[3];
		attributes[0] = 0;
		attributes[1] = 1; 
		attributes[2] = items.numAttributes() - 1;

		// Crea un nuovo set di istanze senza l'attributo nome
		Remove remove = new Remove();
		remove.setAttributeIndicesArray(attributes);
		remove.setInvertSelection(false);
		remove.setInputFormat(items);
		data = Filter.useFilter(items, remove);

		Standardize standardize = new Standardize();
		standardize.setInputFormat(data);
		data = Filter.useFilter(data, standardize);
		
		dao = new ProdottoDAO();
		associations = new int[items.numInstances()];

		getAllProdotti();
	}

	public int[] createClusters() throws Exception {

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

	public ProdottoBean getProdottoByLine(int line) {
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

	public ProdottoBean getProdottoById(int id) {
//		getAssociations();

		int n = associations.length;
		int line = -1;
		for (int i = 0; i < n; i++)
			if (associations[i] == id)
				line = i;

		return getProdottoByLine(line);
	}

	public ArrayList<ProdottoBean> getAllProdotti() {
		ArrayList<ProdottoBean> prodotti = new ArrayList<ProdottoBean>();
		int n = items.numInstances();
		for (int i = 0; i < n; i++) {
			ProdottoBean p = getProdottoByLine(i);
			prodotti.add(p);
		}

		return prodotti;
	}
	
	public ArrayList<ProdottoBean> getProdottiByCategoria(String categoria) {
		ArrayList<ProdottoBean> prodotti = new ArrayList<ProdottoBean>();
		int n = items.numInstances();
		
		for (int i = 0; i < n; i++) {
			//Seleziona solo quelli in cui la categoria coincide
			if (items.instance(i).stringValue(0).equalsIgnoreCase(categoria)) {
				ProdottoBean p = getProdottoByLine(i);
				prodotti.add(p);
			}
		}

		return prodotti;
	}

	public ArrayList<ProdottoBean> getCluster(int clusterNumber) {
		ArrayList<ProdottoBean> cluster = new ArrayList<ProdottoBean>();
		int n = items.numInstances();
		for (int i = 0; i < n; i++) {
			if (assignments[i] == clusterNumber) {
				ProdottoBean p = getProdottoByLine(i);
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

	public int[] getAssociations() {
		getAllProdotti();
		return associations;
	}

	public ProdottoBean createProdotto(Instance itemInstance) throws SQLException {
		// Salva in p le informazioni tracciate dal database e chiama create
		ProdottoBean p = new ProdottoBean();

		p.setNomeprodotto(itemInstance.stringValue(1));
		p.setCategoria(itemInstance.stringValue(0));
		p.setPrezzo((float) itemInstance.value(itemInstance.numAttributes() - 1));

		dao.create(p);

		return updateProdotto(p, itemInstance);
	}

	public ProdottoBean updateProdotto(ProdottoBean p, Instance itemInstance) {
		// Salva in p le informazioni contenute solo nel dataset
		int[] valori = new int[15];
		for (int i = 0; i <= 14; i++)
			valori[i] = (int) itemInstance.value(i + 2);

		p.setValori(valori);
		p.setSize(itemInstance.value(17));
		return p;
	}
}
