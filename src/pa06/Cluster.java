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
	 
	 public Cluster(Sample clusterPoint, ArrayList<Sample> list) {
		 this.clusterPoint = clusterPoint;
		 this.list = list;
	 }
	 
	 public Sample PickCluster(ArrayList<Sample> list){
		 Random rand = new Random();
		 int randomNumber = rand.nextInt(list.size());
		 return list.get(randomNumber);
	 }
}
	 

