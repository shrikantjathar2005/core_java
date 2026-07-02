package streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapStreamDemo {
	public static void main(String[] args) {
		
		Map<String, String> people = new HashMap<>();
		people.put("Polo", "Pune");
		people.put("Mili", "Mumbai");
		people.put("Deny", "Delhi");
		
		people.values().stream().forEach(System.out::println);
		
		List<String> cities = people.values().stream().map(c -> c.toUpperCase())
				.sorted().collect(Collectors.toList());
		
		cities.forEach(System.out::println);
		
		Map<String, List<String>> contacts = new HashMap<>();
		contacts.put("Frudo", Arrays.asList("1212-3434","5656-7878"));
		contacts.put("Sean", Arrays.asList("2212-3534", "5666-7888", "8989-5050"));
		contacts.put("Ben", Arrays.asList("2112-3334", "5633-1188", "8090-2321"));
		
		contacts.values().stream().flatMap(Collection::stream).forEach(System.out::println);
		
		// Print all phone numbers containing 8
		Set<String> lucky = contacts.values().stream().flatMap(Collection::stream)
			.filter(n -> n.contains("8")).collect(Collectors.toSet());
		
		lucky.forEach(System.out::println);
		
		// Print all phone numbers removing "-"
		contacts.values().stream().flatMap(Collection::stream)
			.map(s -> s.replace("-", "")).forEach(System.out::println);
	}
}


