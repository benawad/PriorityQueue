import java.util.Arrays;
import java.util.Random;

public class PriorityQueueTest {
	
	public static void testPriorityQueue(PriorityQueue q){
		double[] values = {0.0, 99.99, -123.032, 17, 1233, 19, 13, 0.0, 0.0, 0.001, 0.0001};
		try{
			q.deleteMin();
			System.out.println("Expected error");
		} catch (EmptyPQException e){
			
		}
		try{
			q.findMin();
			System.out.println("Expeected error");
		} catch (EmptyPQException e){
			
		}
		if(!q.isEmpty()){
			new Exception("Should be empty");
		}
		for(double d : values){
			q.insert(d);
		}
		if(q.isEmpty()){
			new Exception("Should not be empty");
		}
		if (values.length != q.size()) {
			new Exception("Size is off");
		}
		if(values[2] != q.findMin()){
			new Exception("Not right min");
		}
		if(values[2] != q.deleteMin()){
			new Exception("Not right min");
		}
		if(values[0] != q.findMin()){
			new Exception("Did not delete min");
		}
		q.makeEmpty();
		if(!q.isEmpty()){
			new Exception("Queue did not empty");
		}
	}
	
	public static void randomTest(PriorityQueue q){
		Random r = new Random();
		int numOfDoubles = Math.abs(r.nextInt(100000));
		double[] d = new double[numOfDoubles];
		for(int i = 0; i < numOfDoubles; i++){
			d[i] = r.nextDouble();
			q.insert(d[i]);
		}
		Arrays.sort(d);
		for(int i = 0; i < d.length; i++){
			double dq = q.deleteMin();
			if(d[i] != dq){
				System.out.println(d[i] + " != " + dq);
			}
		}
	}
	
	public static void timeTest(PriorityQueue q1, PriorityQueue q2, PriorityQueue q3, int n){
		Random r = new Random();
		double[] d = new double[n];
		for(int i = 0; i < n; i++){
			d[i] = r.nextDouble();
		}
		
		System.out.println("PriorityQueue #1");
		timeQ(q1, n, d);
		System.out.println("PriorityQueue #2");
		timeQ(q2, n, d);
		System.out.println("PriorityQueue #3");
		timeQ(q3, n, d);
	}
	
	private static void timeQ(PriorityQueue q, int n, double[] d){
		long startTime = System.currentTimeMillis();

		for(int i = 0; i < n; i++){
			q.insert(d[i]);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Execution time for inserting " + n + " doubles: " + (endTime - startTime) );
		
		long startDeleteTime = System.currentTimeMillis();
		
		for(int i = 0; i < n; i++){
			q.deleteMin();
		}
		
		long endDeleteTime = System.currentTimeMillis();
		
		System.out.println("Execution time for deleting " + n + " doubles: " + (endDeleteTime - startDeleteTime) );	
		
		System.out.println("Total execution time for both inserting and deleting " + n + " doubles is: " + ((endTime-startTime)+(endDeleteTime-startDeleteTime)));
	}
	
	public static void main(String[] args){
		PriorityQueue q1 = new BinaryHeap();
		PriorityQueue q2 = new ThreeHeap();
		PriorityQueue q3 = new MyPQ();
		testPriorityQueue(q1);
		testPriorityQueue(q2);
		testPriorityQueue(q3);
		for(int i = 0; i < 100; i++){
			randomTest(new BinaryHeap());
			randomTest(new ThreeHeap());
			randomTest(new MyPQ());
			System.out.println("Success");
		}
		
		timeTest(q1, q2, q3, 10000);
		System.out.println("--------------");
		timeTest(q1, q2, q3, 20000);
		System.out.println("--------------");
		timeTest(q1, q2, q3, 50000);
		System.out.println("--------------");
		timeTest(q1, q2, q3, 75000);
		
	}

}
