package analysis;

import java.util.List;

import model.DataGenerator;
import model.Human;


// 1. Print all humans using foreach
// 2. Pretty print in a thread executor in parallel
// 3. Print all female human beings. Print all Africans.
// 4. Calculate the average age of all Asians (standard approach).
// 5. Calculate the sum of age of all North Americans using reduction. 
// 6. Calculate the average of age of all North Americans using collector.
public class Analyzer {
	public static void main(String[] args) {
		List<Human> humans = DataGenerator.generate(1000);
		
		Task task = new Task6();
		task.execute(humans);
		
	}
}
