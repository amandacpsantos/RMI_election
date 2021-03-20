package redes;
import java.rmi.Naming;

public class ElectionServer {

	public ElectionServer() {
		try {
			Election e = new ElectionServant();
			Naming.rebind("rmi://localhost/ElectionService", e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new ElectionServer();
		System.out.println("Servidor Election em execução.");
	}
}
