/*
 * Kevin Chen
 * June 20th, 2021
*/

package Assignment1.copy;

import java.io.File;
import java.util.*;
import net.datastructures.*;

public class partA {
	
public static void main(String[] args) throws Exception{
	
	File file = new File("/Users/kevinchen/Desktop/testcases.txt");

	Scanner sc = new Scanner (file);

	//Scans through the given text file
	while(sc.hasNextLine()){
		String input = sc.nextLine();
		String [] elem = input.split(";");
		LinkedQueue<String> queueProcessor = new LinkedQueue<>();
		
		//makes a queue with each P() being an element
		for(int x=0;x< elem.length;x++) {
				queueProcessor.enqueue(elem[x]);
		}
		
		//avail is used to keep track of available resources per cycle
		//current is used to keep track of which resources the current cycle needs
		int[]avail = {0,0,0};
		int[]current = {0,0,0};
		
		//processes is the amount of cycles needed per line
		int processes = 1;
		
		while (queueProcessor.isEmpty() != true){

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
			if(avail[0]>1 || avail[1]>1 || avail[2]>1) {
				processes++;
				for(int z=0; z<3; z++)
					avail[z] = current[z];
			}
			
			queueProcessor.dequeue();

		}
		System.out.println(processes);
	}
}
}