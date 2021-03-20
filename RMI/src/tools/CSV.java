package tools;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CSV {
	
	public static Map<String, String> getMap() throws Exception{
		String line = "";
		Map<String, String> table = new HashMap<String, String>();
		Path f = Paths.get(System.getProperty("user.dir"));		
		String url = f.getParent() + "\\src\\senadores.csv";
		BufferedReader br = new BufferedReader(new FileReader(url));
		br.readLine();
		while ((line = br.readLine()) != null){  
			String[] senator = line.split(";");
			table.put(senator[0], senator[1] + " - " + senator[2]);			
		}
		br.close();
		return table;
	}
}
