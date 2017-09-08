package model;

public class Human {
	public static enum Gender {
		Male,
		Female
	}
	
	public static enum Enthnicity {
		African,
		Antartican,
		Asian,
		Australian,
		European,
		NorthAmerican,
		SouthAmerican
	}

	String name;
	Gender gender;
	Enthnicity enthnicity;
	int age;
	
	public Human(String name, Gender gender, Enthnicity enthnicity, int age) {
		this.name = name;
		this.gender = gender;
		this.enthnicity = enthnicity;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}
	
	public Enthnicity getEnthnicity() {
		return enthnicity;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", gender=" + gender + ", enthnicity=" + enthnicity + ", age=" + age + "]";
	}
}
