package it.RistoManager.FIA;

import java.io.BufferedReader;
import java.util.Arrays;

import it.RistoManager.Model.DAO.ProdottoDAO;
import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.Standardize;

//Originale
public class KMeansEvaluator {

	private static Instances data;

	public static void main(String args[]) throws Exception {
		createData();

		double max = -1.1, tmp;
		int bestNumClusters = 0, bestSeed = 0;

		// Valuta il miglior numero di Clusters e Seed
		for (int i = 1; i <= 20; i++) {
			for (int j = 0; j <= 200; j++) {
				tmp = evaluate(i, j);
//				System.out.println("clusters = " + i + " seed = " + j + " tmp = " + tmp);
				if (tmp > max) {
					max = tmp;
					bestNumClusters = i;
					bestSeed = j;
				}
			}
		}

		System.out.println("\nMAX = " + max + "; BEST CLUSTERS = " + bestNumClusters + "; BEST SEED = " + bestSeed);

		// Risultato:
		// MAX = 0.3733685131882255; BEST CLUSTERS = 6; BEST SEED = 3
	}

	// Calcola il valore di Silhouette eseguendo un KMeans, con numero di cluster e
	// seed in input
	public static double evaluate(int numClusters, int seed) throws Exception {

		EuclideanDistance distance = new EuclideanDistance();
		distance.setDontNormalize(true);

		SimpleKMeans kmeans = new SimpleKMeans();

		kmeans.setDistanceFunction(distance);
		if (seed >= 0)
			kmeans.setSeed(seed);
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(numClusters);

		kmeans.buildClusterer(data);

		/////////////////////////////////////////////////////////////

		// Associa ad ogni istanza il numero del cluster
		int[] clusterIndexOfInstance = new int[data.numInstances()];
		for (int i = 0; i < data.numInstances(); i++) {
			clusterIndexOfInstance[i] = kmeans.clusterInstance(data.instance(i));
		}

		double sumSilhouetteCoefficients = 0;
		for (int i = 0; i < data.numInstances(); i++) {

			//Calcola la distanza media ell'instanza corrente da ciascun cluster
			double[] averageDistancePerCluster = new double[kmeans.numberOfClusters()];
			int[] numberOfInstancesPerCluster = new int[kmeans.numberOfClusters()];
			for (int j = 0; j < data.numInstances(); j++) {
				averageDistancePerCluster[clusterIndexOfInstance[j]] += distance.distance(data.instance(i),
						data.instance(j));
				numberOfInstancesPerCluster[clusterIndexOfInstance[j]]++; 
			}
			for (int k = 0; k < averageDistancePerCluster.length; k++) {
				averageDistancePerCluster[k] /= numberOfInstancesPerCluster[k];
			}

			// distanza media dal cluster dell'istanza
			double a = averageDistancePerCluster[clusterIndexOfInstance[i]];

			// Distanza del cluster più vicino
			averageDistancePerCluster[clusterIndexOfInstance[i]] = Double.MAX_VALUE;
			double b = Arrays.stream(averageDistancePerCluster).min().getAsDouble();

			// Calcola il coefficiente di silhouette per l'istanza corrente
			sumSilhouetteCoefficients += kmeans.numberOfClusters() > 1 ? (b - a) / Math.max(a, b) : 0;
		}

		return sumSilhouetteCoefficients / data.numInstances();
	}

	public static void createData() throws Exception {
		BufferedReader datafile = KMeansExecutor.readDataFile(KMeansExecutor.fileName);
		Instances items = new Instances(datafile);

		// Scelta dell'attributo da rimuovere (0 = categoria; 1 = nome del prodotto;
		// numAttributes - 1 = prezzo)
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
	}
}
