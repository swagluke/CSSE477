package analysis;

import java.util.List;

import model.Human;

public class Task5 implements Task {

	@Override
	public void execute(List<Human> humans) {
		double sum = humans.stream().filter(h -> h.getEnthnicity() == Human.Enthnicity.NorthAmerican)
				.map(Human::getAge)
				.reduce(0, (accumulator, age) -> {
					return accumulator = age;
				});

		System.out.println("Sum age of North American: " + sum);

	}

}
