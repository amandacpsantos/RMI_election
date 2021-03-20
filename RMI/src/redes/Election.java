package redes;
public interface Election extends java.rmi.Remote {

	public void vote(String elector, String codeSenator) throws java.rmi.RemoteException;

	public Integer result(String elector) throws java.rmi.RemoteException;

}
