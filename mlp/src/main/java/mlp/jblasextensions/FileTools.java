package mlp.jblasextensions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL; 
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays; 


public class FileTools {
	public static ArrayList<ArrayList<String>> loadFromURL(String url_str) throws IOException {
		URL url = new URL(url_str); 
		URLConnection connection = url.openConnection();
		
		InputStreamReader input = new InputStreamReader(connection.getInputStream()); 
		BufferedReader buffer = null;
		String text = ""; 
		String delim = ","; 
		
		ArrayList<ArrayList<String>> csv_data = new ArrayList<ArrayList<String>>();
		
		try {
			buffer = new BufferedReader(input); 
			while( (text = buffer.readLine()) != null) {
				ArrayList<String> split = new ArrayList<String>(Arrays.asList(text.split(delim))); 
				csv_data.add(split);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return csv_data;
	}
	
	public static ArrayList<ArrayList<String>> loadLocalCSV(String path) {
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		String url_text = "https://www.fhfa.gov/HPI_master.csv";
		
		System.out.println(FileTools.loadFromURL(url_text));
		
	}
	

}
