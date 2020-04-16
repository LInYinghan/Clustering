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
	Cluster[] clusters;
    Cluster originalData;
	
	public KMean(int k){
		this.clusters = new Cluster[k]; 
		this.originalData = new Cluster(); 
	}
	
	public void setClusterPoint(int k) {
		this.clusters = new Cluster[k];
		
		for(int i=0; i<k; i++) {
			clusters[i] = new Cluster();
			clusters[i].addSample(originalData.PickCluster(this.originalData.list));		
		}
	}
	
    public void Reclassify(){
		
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
		kmean.setClusterPoint(k);
		for(int i=0; i<k; i++) {
			kmean.clusters[i].PrintCluster();
		}
		kmean.originalData.PrintCluster();
	}
	
	public static void ReadFile(String filename, KMean kmean) throws FileNotFoundException {
		File textfile = new File(filename);
		Scanner sc = new Scanner(textfile);
		int i = 0;
		while(sc.hasNextLine()) {
	        String line = sc.nextLine();
			Sample point = new Sample(process(line));
			point.setClusterId(i);
			kmean.originalData.addSample(point);
			i++; 
		}
        sc.close();
       
	}
		
	public static double[] process(String line){
		Scanner data = new Scanner(line);
        double[]values = new double[2];  //The length of the array values depends on the data set and can be modified.
		int counter=0;
		while(data.hasNextDouble()){
			values[counter] = data.nextDouble();
			counter++;
		}			
		data.close();
		return values; 
	}
	
	public static double Distance(Sample s1, Sample s2) {
		double sum = 0;
		for(int i=0; i<s1.size(); i++) {
			sum = sum + Math.pow((s1.get(i)-s2.get(i)),2);   
		}
		return Math.sqrt(sum); 
	}
	
    /*8public static int Closest() {
		
	}
	
	public static double FindAverage() {
		
	}
	*/
	

	
}
