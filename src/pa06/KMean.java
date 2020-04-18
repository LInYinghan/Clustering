package pa06;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * The class with the main method...
 * @author Yinghan Lin, Shiyi (Wendy) Mao
 *
 */
public class KMean{
	Cluster[] clusters;
    Cluster originalData;

	public KMean(int k){
		this.clusters = new Cluster[k];
		this.originalData = new Cluster();
	}

	// initialize the cluster with the origin data point by randomly selecting
	// k different data point from origin data set
	public void setClusterPoint(int k) {
		// check for illegal parameter
		if(k > originalData.getList().size()){
			throw new RuntimeException("K should not be larger than number of samples");
		}
		if(k <= 0){
			throw new RuntimeException("K should not be 0");
		}

		// build the clusters
		this.clusters = new Cluster[k];

		// a set of sample to store the random sample point
		Set<Sample> randomSampleSet = new HashSet<>();
		Random random = new Random();

		// select k different sample points from origin data
		while(randomSampleSet.size() != k){
			int randomInt = random.nextInt(originalData.getList().size());
			randomSampleSet.add(originalData.getList().get(randomInt));
		}

		// assign the k sample point to k clusters accordingly
		Iterator<Sample> iterator = randomSampleSet.iterator();
		for(int i = 0; i < k; i++){
			clusters[i] = new Cluster();
			Sample representSample = iterator.next();
			// set cluster point to the randomly picked sample point
			clusters[i].setCluster(representSample);
			// add the randomly picked sample point to the cluster
			clusters[i].addSample(representSample);
			// set the sample point to the cluster it belongs to
			representSample.setClusterId(i);
		}
	}

	// the entrance method which do 100 times iteration to cluster sample points
	public static void main(String[]args) throws FileNotFoundException{

		// read file name from the input
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		String filename = input.nextLine();

		// read the cluster number k from input
		System.out.println("Please enter the number of clusters: ");
		int k = input.nextInt();
		input.close();

		// initialize the kmean instance
		KMean kmean = new KMean(k);

		// read sample data point from the file and store them in origin data
		ReadFile(filename,kmean);//in test
		System.out.println();

		kmean.originalData.PrintCluster();
		System.out.println();

		// initialize k clusters with the sample points
		kmean.setClusterPoint(k);

		// loop 100 times to stabilize the clustering
		for(int i = 0; i < 100; i++){

			// replace the cluster point with the average of all data point in the cluster
			for(Cluster cluster : kmean.clusters){
				Sample avgSample = new Sample(FindAverage(cluster));
				avgSample.setClusterId(cluster.clusterPoint.ClusterId);
				cluster.setCluster(avgSample);

				// clear the data points of the cluster for reclassifying
				cluster.list = new ArrayList<>();
			}
			// reclassify all data points to the clusters
			Reclassify(kmean.originalData.getList(), kmean.clusters);

			// print intermediate results
			System.out.println((i + 1) + " time iteration: ");
			for(Cluster cluster : kmean.clusters){
				// print the cluster and the data points in it
				cluster.PrintCluster();
				System.out.println();
			}
		}
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

    public static int Closest(Sample point, Cluster[]clusters) {
		double min = Distance(point, clusters[0].clusterPoint);
		int count = 0;
		for(int j=0; j<clusters.length;j++) {
			double d = Distance(point, clusters[j].clusterPoint);
			if(d<=min) {
				min = d;

				count = j;
			}
		}
		return count;
	}

    public static void Reclassify(ArrayList<Sample> list, Cluster[]clusters) {
    	for(int i=0; i<list.size(); i++) {
    		int count = Closest(list.get(i), clusters);
    		list.get(i).setClusterId(clusters[count].clusterPoint.ClusterId);
    		clusters[count].addSample(list.get(i));
    	}
    }


	public static double[] FindAverage(Cluster c) {
		double sumX=0;
		double sumY=0;
		ArrayList<Sample> temp = c.getList();
		int numOfPoints = temp.size();
		for(int i=0;i<numOfPoints;i++){
			Sample s = temp.get(i);
			sumX+=s.get(0);
			sumY+=s.get(1);
		}
		double[] toReturn = new double[2];
		toReturn[0] = sumX/numOfPoints;
		toReturn[1] = sumY/numOfPoints;
		return toReturn;
	}

}//end of the class

