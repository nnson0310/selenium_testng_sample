package webdriver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.ajbrown.namemachine.Gender;
import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;

public class Test {

	public static void main(String[] args) {
		NameGenerator nameGenerator = new NameGenerator();
		List<Name> randomName = nameGenerator.generateNames(1);
		String name = randomName.stream().map(Name::toString).collect(Collectors.joining(","));
		String[] nameCompo = name.split(" ");
		System.out.println(nameCompo[0]);
	}

}
