package analysis;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Human;

public class Task2 implements Task {
	@Override
	public void execute(List<Human> humans) {
		ExecutorService service = Executors.newFixedThreadPool(4);

		humans.stream().forEach(h -> {
			Runnable runnable = () -> {
				System.out.println(h);
			};
			service.execute(runnable);
		});

		service.shutdown();
		while (!service.isTerminated()) {
			try {
				service.awaitTermination(100, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
			}
		}
	}
}
