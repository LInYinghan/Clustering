package pa06;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * The class with the main method...
 * @author Yinghan Lin, Shiyi(Wendy) Mao
 *
 */
public class KMean{
	Cluster[] clusters;
    Cluster originalData;

	public KMean(int k){
		this.clusters = new Cluster[k];
		this.originalData = new Cluster();
	}

	public void setClusterPoint(int k) {
		if(k > originalData.getList().size()){
			throw new RuntimeException("K should not be larger than number of samples");
		}
		if(k <= 0){
			throw new RuntimeException("K should not be 0");
		}
		this.clusters = new Cluster[k];
		Set<Sample> randomSampleSet = new HashSet<>();
		Random random = new Random();
		while(randomSampleSet.size() != k){
			int randomInt = random.nextInt(originalData.getList().size());
			randomSampleSet.add(originalData.getList().get(randomInt));
		}
		Iterator<Sample> iterator = randomSampleSet.iterator();
		for(int i = 0; i < k; i++){
			clusters[i] = new Cluster();
			Sample representSample = iterator.next();
			clusters[i].setCluster(representSample);
			clusters[i].addSample(representSample);
			representSample.setClusterId(i);
		}
	}

	public static void main(String[]args) throws FileNotFoundException{
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		String filename = input.nextLine();
		System.out.println("Please enter the number of clusters: ");
		int k = input.nextInt();
		input.close();

		KMean kmean = new KMean(k);

		ReadFile(filename,kmean);//in test
		System.out.println();

		kmean.originalData.PrintCluster();
		System.out.println();
		kmean.setClusterPoint(k);
		for(int i = 0; i < 100; i++){
			for(Cluster cluster : kmean.clusters){
				Sample avgSample = new Sample(FindAverage(cluster));
				avgSample.setClusterId(cluster.clusterPoint.ClusterId);
				cluster.setCluster(avgSample);
				cluster.list = new ArrayList<>();
			}
			Reclassify(kmean.originalData.getList(), kmean.clusters);

			// print intermediate results
			System.out.println((i + 1) + " time iteration: ");
			for(Cluster cluster : kmean.clusters){
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
		ArrayList<Sample> temp = c.getList();//getting the list of points in a cluster
		int numOfPoints = temp.size();
		for(int i=0;i<numOfPoints;i++){
			Sample s = temp.get(i);
			sumX+=s.get(0);
			sumY+=s.get(1);
		}//getting the sum of all x coordinates and y coordinates
		double[] toReturn = new double[2];
		toReturn[0] = sumX/numOfPoints;
		toReturn[1] = sumY/numOfPoints;
		return toReturn;
	}

}//end of the class
