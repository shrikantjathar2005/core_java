package streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightStreamDemo {

	public static void main(String[] args) throws Exception {
		
		Stream<String> lines = Files.lines(Paths.get("src/flights.txt"));
		
		List<Flight> flights = lines.map(line -> {
			String[] record = line.split(",");
			return new Flight(Integer.parseInt(record[0]), record[1], record[2], record[3]);
		}).collect(Collectors.toList());
		
		flights.forEach(System.out::println);
		
		// Print details of flight no 123
		System.out.println(flights.stream().filter(f -> f.getCode()==123).findFirst().get());
		
		// Print all flights by Jet carrier
		flights.stream().filter(f -> f.getCarrier().equals("Jet")).forEach(System.out::println);
		
		// Print flight going pune to delhi
		flights.stream().filter(f -> f.getSource().equals("Pune") && 
				f.getDestination().equals("Delhi")).forEach(System.out::println);
		
		// Remove a flight no 920
		flights.removeIf(f -> f.getCode()==920);
	}
}


