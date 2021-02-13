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

		int[] clusterIndexOfInstance = new int[data.numInstances()];
		for (int i = 0; i < data.numInstances(); i++) {
			clusterIndexOfInstance[i] = kmeans.clusterInstance(data.instance(i));
		}

		double sumSilhouetteCoefficients = 0;
		for (int i = 0; i < data.numInstances(); i++) {

			// Compute average distance of current instance to each cluster, including its
			// own cluster
			double[] averageDistancePerCluster = new double[kmeans.numberOfClusters()];
			int[] numberOfInstancesPerCluster = new int[kmeans.numberOfClusters()];
			for (int j = 0; j < data.numInstances(); j++) {
				averageDistancePerCluster[clusterIndexOfInstance[j]] += distance.distance(data.instance(i),
						data.instance(j));
				numberOfInstancesPerCluster[clusterIndexOfInstance[j]]++; // Should the current instance be skipped
																			// though?
			}
			for (int k = 0; k < averageDistancePerCluster.length; k++) {
				averageDistancePerCluster[k] /= numberOfInstancesPerCluster[k];
			}

			// Average distance to instance's own cluster
			double a = averageDistancePerCluster[clusterIndexOfInstance[i]];

			// Find the distance of the "closest" other cluster
			averageDistancePerCluster[clusterIndexOfInstance[i]] = Double.MAX_VALUE;
			double b = Arrays.stream(averageDistancePerCluster).min().getAsDouble();

//		  System.out.println("Number of Clusters = "  +kmeans.numberOfClusters() +  "\na =" + a +  "\nb =" + b);
			// Compute silhouette coefficient for current instance
//		  System.out.println("INCREMENT = " + ((b - a) / Math.max(a, b)));
			sumSilhouetteCoefficients += kmeans.numberOfClusters() > 1 ? (b - a) / Math.max(a, b) : 0;
		}

//		System.out.println("SUM = " + sumSilhouetteCoefficients + " INSTANCES = " + data.numInstances());
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
