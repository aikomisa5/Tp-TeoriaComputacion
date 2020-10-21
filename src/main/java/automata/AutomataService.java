package automata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AutomataService {

	public Automata getAutomataFromFile() {

		Scanner myReader = null;

		try {
			File myObj = new File("src/main/resources/automata.txt");
			myReader = new Scanner(myObj);

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Ocurrio un error: " + e.getMessage());
		}
		finally {
			if (myReader != null) {
				myReader.close();
			}
		}

		return new Automata();
	}
}
