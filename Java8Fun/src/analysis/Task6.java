package analysis;

import java.util.List;
import java.util.function.IntConsumer;

import model.Human;

public class Task6 implements Task {
	class Averager implements IntConsumer {
		private int total = 0;
		private int count = 0;

		public double average() {
			return count > 0 ? ((double) total) / count : 0;
		}

		@Override
		public void accept(int value) {
			total += value;
			count++;

		}
		
		public void combine(Averager other){
			total += other.total;
			count += other.count;
		}

	}

	@Override
	public void execute(List<Human> humans) {
		double average = humans.stream().filter(h -> h.getEnthnicity() == Human.Enthnicity.NorthAmerican)
				.map(Human::getAge)
				.collect(Averager:: new, Averager::accept, Averager::combine)
				.average();

		System.out.println("Average age of North American: " + average);

	}

}
