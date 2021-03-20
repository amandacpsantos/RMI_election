package redes;
import tools.CSV;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ElectionServant extends java.rmi.server.UnicastRemoteObject implements Election {

	private static final long serialVersionUID = 1L;
	private Map<String, String> senators = new HashMap<String, String>(); 
	private Map<String, String> votes = new HashMap<String, String>(); 

	public ElectionServant() throws java.rmi.RemoteException {
		super();
		try {
			this.senators = CSV.getMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void vote(String elector, String codeSenator) throws RemoteException {
		if (!this.votes.containsKey(elector)){
			if(this.senators.containsKey(codeSenator)) {
				this.votes.put(elector, codeSenator);
			}
		}
	}

	@Override
	public Integer result(String codeSenator) throws RemoteException {
		int result = Collections.frequency(this.votes.values(), codeSenator.trim());
		return result;
	}
}
