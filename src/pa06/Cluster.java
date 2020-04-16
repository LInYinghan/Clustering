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
		 double[] values = {0.0, 0.0};
		 this.clusterPoint = new Sample(values);
		 this.list = new ArrayList<Sample>();
	 }
	 
	 public void addSample(Sample point) {
		 list.add(point);
	 } 
	 
	 public ArrayList<Sample> getList() {
		 return list;
	 }
	 
	 public void setCluster(Sample point) {
		 this.clusterPoint = point;
	 }
	 
	 public Sample PickCluster(ArrayList<Sample> list){
		 Random rand = new Random();
		 int randomNumber = rand.nextInt(list.size());
		 return list.get(randomNumber);
	 }
	 
	 public void PrintCluster() {
		 System.out.println("The Cluster Point: ");
		 System.out.println(clusterPoint.toString());
		 System.out.println("The Points list: ");
		 for(Sample p: list) {
			 System.out.println(p.toString());
		 }
	 }
}	 

