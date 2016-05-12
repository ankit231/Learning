package aero;

import utility.Person;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.RecordExistsAction;

public class AerospikeDao {

	private static final String userName = "ankitUser";
	private static final String password = "ankitpwd";
	private static final String nameSpace = "ankitdb";
	private static final String keySet = "person";
	static AerospikeClient client = null;
	static Host host = new Host("localhost", 3000);

	public static AerospikeClient getClient() {
		if (client != null) {
			return client;
		}
		try {
			ClientPolicy policy = getClientPolicy();
			client = new AerospikeClient(policy, host);
		} catch (Exception e) {
			System.out.println("Exception while initializing aerospike client" + e);
		}
		return client;
	}

	private static ClientPolicy getClientPolicy() {
		ClientPolicy policy = new ClientPolicy();
		/*policy.user = userName;
		policy.password = password;*/
		policy.failIfNotConnected = true;
		policy.timeout = -1;
		policy.readPolicyDefault.timeout = 50;
		policy.readPolicyDefault.maxRetries = 1;
		policy.readPolicyDefault.sleepBetweenRetries = 10;
		policy.writePolicyDefault.timeout = 200;
		policy.writePolicyDefault.maxRetries = 1;
		policy.writePolicyDefault.sleepBetweenRetries = 50;
		policy.writePolicyDefault.recordExistsAction = RecordExistsAction.UPDATE;
		policy.writePolicyDefault.expiration = 5184000;
		policy.writePolicyDefault.sendKey = true;
		policy.writePolicyDefault.timeout = 50;
		return policy;
	}

	public void savePerson(Person p) {
		AerospikeClient client = AerospikeDao.getClient();
		try {
			Key key = new Key(nameSpace, keySet, p.getIndex());
			Bin isActiveBin = new Bin("isActive", p.isActive());
			Bin balanceBin = new Bin("balance", p.getBalance());
			Bin ageBin = new Bin("age", p.getAge());
			Bin fNameBin = new Bin("firstName", p.getName().getFirstName());
			Bin lNameBin = new Bin("lastName", p.getName().getLastName());
			client.put(null, key, isActiveBin, balanceBin, ageBin, fNameBin, lNameBin);
		} catch (Exception e) {
			System.out.println("Exception while adding bin to cartkey :" + e);
		}
	}

	public void getPerson(String index) {
		AerospikeClient client = AerospikeDao.getClient();
		try {
			Key key = new Key(nameSpace, keySet, index);
			Record record = client.get(null, key);
			System.out.println("Here");
		} catch (Exception e) {
			System.out.println("Exception while adding bin to cartkey :" + e);
		}
	}

}
