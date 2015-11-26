import java.awt.print.Printable;
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
		int numOfDoubles = Math.abs(r.nextInt(1000000));
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
//				System.out.println("--ERROR--");
			}
		}
	}
	
	public static void main(String[] args){
		PriorityQueue q1 = new BinaryHeap();
		PriorityQueue q2 = new ThreeHeap();
		PriorityQueue q3 = new MyPQ();
//		testPriorityQueue(q1);
		testPriorityQueue(q2);
//		testPriorityQueue(q3);
		for(int i = 0; i < 100; i++){
//			randomTest(new BinaryHeap());
			randomTest(new ThreeHeap());
//			randomTest(new MyPQ());
		}
	}

}
