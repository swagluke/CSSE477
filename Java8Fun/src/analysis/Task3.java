package analysis;

import java.util.List;

import model.Human;

public class Task3 implements Task {

	@Override
	public void execute(List<Human> humans) {
//Print all female human beings.
//		humans.stream()
//			.filter(h -> h.getGender() == Human.Gender.Female)
//			.forEach(h -> System.out.println(h));
//		
		humans.stream()
		.filter(h -> h.getEnthnicity() == Human.Enthnicity.African)
		.forEach(h -> System.out.println(h));
	}

}
