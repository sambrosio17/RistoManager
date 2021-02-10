package it.RistoManager.FIA;

import java.io.BufferedReader;
import java.util.Scanner;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSink;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Add;
import weka.filters.unsupervised.attribute.Remove;

public class DatasetManipulator {

	private static final String originalFile = "./Datasets/menu.arff";
	private static final String servingSizeFile = "./Datasets/menuServingSizeConvertito.arff";
	private static final String finalFile = "./Datasets/menuConPrezzi.arff";

	private static int lastIndex = 24;

	public static void main(String[] args) throws Exception {

		servingSizeConverter();
		priceGenerator();

	}

	// Converte ServingSize da Stringa a double
	// Aggiunge una colonna per il double ottenuto
	// Rimuove ServingSize(Stringa)
	public static void servingSizeConverter() throws Exception {

		// Legge il file da convertire
		BufferedReader datafile = KMeansExecutor.readDataFile(originalFile);
		Instances data = new Instances(datafile);

		// Aggiunge un nuovo attributo alle Instances
		Add filter = new Add();
		filter.setAttributeIndex("last");
		filter.setAttributeName("Size");
		filter.setInputFormat(data);
		data = Filter.useFilter(data, filter);

		for (int i = 0; i < data.numInstances(); i++) {
			Instance instance = data.instance(i);

			// Legge la Stringe
			String peso = instance.stringValue(2);
			Scanner s = new Scanner(peso);
			peso = s.next();

			// Converte in double la prima parte della Stringa (Il valore in oz)
			Double p = Double.parseDouble(peso);

			// Salva il valore ottenuto nella colonna creata precedentemente (Size)
			instance.setValue(24, p);
			s.close();
		}

		// Scelta dell'attributo da rimuovere (2 = Serving Size)
		int[] attributes = new int[1];
		attributes[0] = 2;

		// Rimuove l'attributo
		Remove remove = new Remove();
		remove.setAttributeIndicesArray(attributes);
		remove.setInvertSelection(false);
		remove.setInputFormat(data);
		data = Filter.useFilter(data, remove);

		// Salva le Instances così ottenute in un nuovo file
		DataSink.write(servingSizeFile, data);

	}

	// Assegna un prezzo a ciascuna istanza del dataset
	public static void priceGenerator() throws Exception {

		// Legge il file da aggiornare
		BufferedReader datafile = KMeansExecutor.readDataFile(servingSizeFile);
		Instances data = new Instances(datafile);

		// Aggiunge un nuovo attributo alle Instances
		Add filter = new Add();
		filter.setAttributeIndex("last");
		filter.setAttributeName("Price");
		filter.setInputFormat(data);
		data = Filter.useFilter(data, filter);

		for (int i = 0; i < data.numInstances(); i++) {
			Instance instance = data.instance(i);
			// Recupera il peso dell'istanza
			Double peso = instance.value(lastIndex - 1);
			Double prezzo, p;

			// In base alla categoria, assegna un prezzo in funzione del peso
			switch (instance.stringValue(0)) {
			case "Breakfast":
				p = peso / 2 + 0.5;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Beef & Pork":
				p = peso - 0.5;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Chicken & Fish":
				p = peso / 1.2;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Salads":
				p = peso / 4 + 0.5;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Snacks & Sides":
				p = peso / 1.5;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Desserts":
				p = peso;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Beverages":
				p = peso / 10 + 0.2;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Coffee & Tea":
				p = peso / 8 + 0.5;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			case "Smoothies & Shakes":
				p = peso / 5 + 0.8;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			default:
				p = peso + 1;
				prezzo = (double) (Math.round(p * 10)) / 10;
				instance.setValue(lastIndex, prezzo);
				break;
			}

		}

		// Salva le Instances così ottenute in un nuovo file
		DataSink.write(finalFile, data);
	}

}
