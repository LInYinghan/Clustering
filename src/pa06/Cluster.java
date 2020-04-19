package pa06;

import java.util.*;

/**
 * A Cluster is a cluster point (which is itself a sample)
 * and a list of Samples (the one's closest to that sample point, ideally).
 * @author Yinghan Lin
 *
 */

public class Cluster {
	 //an ArrayList (rows) of Samples and a single Sample (the cluster point)
	 Sample clusterPoint;
	 ArrayList<Sample> list;
	 
	 //constructor, set the single Sample at {0.0,0.0}
	 public Cluster() {
		 double[] values = {0.0, 0.0};
		 this.clusterPoint = new Sample(values);
		 this.list = new ArrayList<Sample>();
	 }
	 
	 //add a new point to the cluster
	 public void addSample(Sample point) {
		 list.add(point);
	 } 
	 
	 //get the cluster list
	 public ArrayList<Sample> getList() {
		 return list;
	 }
	 
	 //set the clusterPoint
	 public void setCluster(Sample point) {
		 this.clusterPoint = point;
	 }
	 
	 //pick a random element of a cluster 
	 public Sample PickCluster(ArrayList<Sample> list){
		 Random rand = new Random();
		 int randomNumber = rand.nextInt(list.size());
		 return list.get(randomNumber);
	 }
	 
	 //print cluster
	 public void PrintCluster() {
		 System.out.println("The Cluster Point: ");
		 System.out.println(clusterPoint.toString());
		 System.out.println("The Points list: ");
		 for(Sample p: list) {
			 System.out.println(p.toString());
		 }
	 }
}	 

