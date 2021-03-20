package redes;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import tools.MD5;

public class ElectionClient {

	public static void main(String[] args) throws MalformedURLException, NotBoundException {
		String server = "rmi://localhost/";
		String name = "ElectionService";
		String user = "";
		String senator = "";
		String resultSenator = "";
				
			Election e = null;
			try {
				e = (Election) Naming.lookup(server + name);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				e1.printStackTrace();
			}
			System.out.println("Objeto remoto \'"+ name + "\' encontrado no servidor.");
			
			String entry = "";
			Scanner read = new Scanner(System.in);
						
			while(!entry.equalsIgnoreCase("0")) {
				System.out.println("\n\nOpções: "
						+ "\n0) Sair"
						+ "\n1) Votar"
						+ "\n2) Ver resultados");
				entry = read.nextLine();
				
				if(entry.equals("1")) {			
					System.out.println("Digite o nome: ");
					user = read.nextLine();
					user = MD5.getMd5(user.trim().toUpperCase());
					System.out.println("Digite o número do candidato: ");
					senator = read.nextLine();
					
					LocalTime timeEnd = LocalTime.now().plusSeconds(30);
					boolean check = false;
					while(LocalTime.now().compareTo(timeEnd) < 0 & !check) {
				    	try {
				    		e.vote(user, senator.trim());
				    		check = true;
				    		System.out.println("Voto computado.");		
				    	} catch (RemoteException s) {
				    		try {
								e = (Election) Naming.lookup(server + name);
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (RemoteException e1) {
								e1.printStackTrace();
								e1.printStackTrace();
							} catch (NotBoundException e1) {
								e1.printStackTrace();
							}
							System.out.println("Tentando conectar ao servidor...");							
				    	}
				    	 
				    }
										
				}else if(entry.equals("2")){
					System.out.println("Digite o número do candidato, para ver o resultado: ");
					resultSenator = read.nextLine();
					boolean check = false;
					LocalTime timeEnd = LocalTime.now().plusSeconds(30);
					while(LocalTime.now().compareTo(timeEnd) < 0 & !check) {
				    	try {
				    		System.out.println("Resultados apurados: " + e.result(resultSenator));
				    		check = true;
				    	} catch (RemoteException s) {
				    		try {
								e = (Election) Naming.lookup(server + name);
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (RemoteException e1) {
								e1.printStackTrace();	
							} catch (NotBoundException e1) {
								e1.printStackTrace();
							}
							System.out.println("Tentando conectar ao servidor...");							
				    	}
				    }
				}
			}
			read.close();
			user = "";
			senator = "";
			resultSenator = "";
	}
}
