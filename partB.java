/*
 * Kevin Chen
 * June 20th, 2021
*/

package Assignment1.copy;
import net.datastructures.*;

public class partB {
	
public static void main(String[] args){
	String process = "";
	LinkedQueue<String> queueProcessor = new LinkedQueue<>();	
	
	//generates 20 random processes
	for (int i=1; i< 21; i++){
		String [] ABC = {null, "A", "B", "C"};
		int numPro = (int)((Math.random()*3)+1);
		process += "P"+i+"(";
		
		for (int n=0;n<numPro;n++){
			int x = 0;
			while (ABC[x]== null){
				x = (int)(Math.random()*(ABC.length));
			}
			process += "," + ABC[x];		
			ABC[x] = null;
			x = -1;
		}
		process += ");";
	}
	System.out.println("Initial 20 processes: " + process);
	
	int total = 20;
	String [] elem = process.split(";");
	
	//makes a queue with each P() being an element
	for (int x=0; x< elem.length; x++) {
		queueProcessor.enqueue(elem[x]);
	}
	
	int processes = 1;
	int[]avail = {0,0,0};
	int[]current = {0,0,0};
	
	while (queueProcessor.isEmpty() != true) {
		total = processes+20;
		process = "";

		current[0]=0;
		current[1]=0;
		current[2]=0;

		boolean hasA = queueProcessor.first().contains("A");
		boolean hasB = queueProcessor.first().contains("B");
		boolean hasC = queueProcessor.first().contains("C");
		
		if(hasA)
			current[0]=1;
		if(hasB)
			current[1]=1;
		if(hasC)
			current[2]=1;
		
		//adds current and avail to see if any of the current resources are already being used
		for(int y=0; y<3; y++) {
			avail[y] = avail[y] + current[y];
		}
		//if any of the current resources are already being used processes will increase and the avail values will match current's
		//it will also add a new random processess at the end of every cycle
		if(avail[0]>1 || avail[1]>1 || avail[2]>1) {
			processes++;
			for(int z=0; z<3; z++)
				avail[z] = current[z];
			String [] ABC = {null, "A","B", "C"};
			int numprocesses = (int)((Math.random()*3)+1);
			process += "P"+total+"(";
			
			for (int n =0; n < numprocesses; n++){
			int y = 0;
				while (ABC[y]== null) {
					y = (int)(Math.random()*(ABC.length));
				}
			process += ABC[y]+ ",";						
			ABC[y] = null;
			y = -1;
			}
			
			process += ");";	
			queueProcessor.enqueue(process);
		}
		queueProcessor.dequeue();
		
		//updates every 100 cycles
		if (processes % 100 == 0) {
			System.out.println("Length of processes at cycle " + processes + ": " + queueProcessor.size());
		}
		//stops if it reaches 1000 cycles
		if (processes == 1000) {
			System.out.println("Length of processes at cycle " + processes + ": " + queueProcessor.size());
			break;
		}
	}
	System.out.println("List emptied at " + processes + " cycles");
}
}