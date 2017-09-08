package analysis;

import java.util.List;

import model.Human;

public class Task1 implements Task {

	@Override
	public void execute(List<Human> humans) {
		humans.forEach(h -> {
			System.out.println(h);
		});
	}

}
