package analysis;

import java.util.List;

import model.Human;

public class Task4 implements Task {

	@Override
	public void execute(List<Human> humans) {
		double average = humans.stream()
			.filter(h -> h.getEnthnicity() == Human.Enthnicity.Asian)
			.mapToInt(Human::getAge)
			.average()
			.getAsDouble();
		
		System.out.println("Average age of Asian: " + average);
			

	}

}
