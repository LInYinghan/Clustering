package pa06;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The class with the main method...
 * @author Yinghan Lin
 *
 */
public class KMean {
	Cluster[]clusters;
    Cluster originalData;
	
	public KMean(int k){
		clusters = new Cluster[k]; 
		originalData = new Cluster();
	}
			
	public static void main(String[]args) throws FileNotFoundException{
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		String filename = input.nextLine();
		System.out.println("Please enter the number of clusters: ");
		int k = input.nextInt();
		input.close(); 
		
		KMean kmean = new KMean(k);
		ReadFile(filename, kmean);
	}
	
	public static void ReadFile(String filename, KMean kmean) throws FileNotFoundException {
		File textfile = new File(filename);
		Scanner sc = new Scanner(textfile);
		while(sc.hasNextLine()) {
	        String line = sc.nextLine();
			Sample point = new Sample(process(line)); 
			kmean.originalData.addSample(point);
		}
        sc.close();
        
	}
		
	public static double[] process(String line){
		Scanner data = new Scanner(line);
        double[]values = new double[2];  //The length of the array values depends on the data set and can be modified.
		for(int i =0; i<values.length; i++) {
			while(data.hasNext()){
				values[i] = data.nextDouble();
			}
		}	
		data.close();
		return values; 
	}
}
