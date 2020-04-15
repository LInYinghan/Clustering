package pa06;

import java.util.*;

/**
 * A Cluster is a cluster point (which is itself a sample)
 * and a list of Samples (the one's closest to that sample point, ideally).
 * @author Yinghan Lin
 *
 */

public class Cluster {

	 Sample clusterPoint;
	 ArrayList<Sample> list; 
	 
	 public Cluster() {
		 double[] initial = new double [2]; //The length of the array values depends on the data set and can be modified.
		 this.clusterPoint = new Sample(initial);
		 this.list = new ArrayList<Sample>();
	 }
	 
	 public void addSample(Sample point) {
		 list.add(point);
	 } 
	 
	 public ArrayList<Sample> getList() {
		 return list;
	 }
	 
	 public void setCluster(Sample point) {
		 clusterPoint = point;
	 }
	 
	 public Sample PickCluster(ArrayList<Sample> list){
		 Random rand = new Random();
		 int randomNumber = rand.nextInt(list.size());
		 return list.get(randomNumber);
	 }
}
	 

