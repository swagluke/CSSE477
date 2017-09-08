package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Human.Enthnicity;
import model.Human.Gender;

public class DataGenerator {
	public static List<Human> generate(int size) {
		List<Human> humans = new ArrayList<>();
		
		Random random = new Random();
		for(int i = 1; i <= size; ++i) {
			String name = "Name " + (random.nextInt(size) + 1);
			Gender gender = Gender.values()[random.nextInt(2)];
			Enthnicity ethnicity = Enthnicity.values()[random.nextInt(7)];
			int age = random.nextInt(101);
			
			humans.add(new Human(name, gender, ethnicity, age));
		}
		
		return humans;
	}
}
