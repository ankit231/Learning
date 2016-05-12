package aero;

import java.util.ArrayList;

import utility.DataReader;
import utility.Person;

public class AerospikeImpl {

	public static void main(String a[]) {
		AerospikeImpl aeroImpl = new AerospikeImpl();

		// loading DB
		aeroImpl.loadDB();

		// Fetch Record from db with given index
		aeroImpl.getRecordFromIndex("10");

	}

	private void getRecordFromIndex(String i) {
		AerospikeDao adao = new AerospikeDao();
		adao.getPerson(i);
	}

	private void loadDB() {
		DataReader dr = new DataReader();
		ArrayList<Person> persons = dr.parseJsonFile();
		AerospikeDao adao = new AerospikeDao();
		for (Person p : persons) {
			adao.savePerson(p);
		}
	}
}
