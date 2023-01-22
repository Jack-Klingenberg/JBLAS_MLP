package mlp.jblasextensions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL; 
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.jblas.DoubleMatrix; 


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
	
	public static ArrayList<ArrayList<String>> loadLocalCSV(String path, String delim_regex, boolean hasHeader) throws FileNotFoundException {
		Scanner console = new Scanner(new File(path));
		console.useDelimiter(",");

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>(); 

		if(hasHeader){
			console.nextLine();
		}

		while(console.hasNext()) {
			ArrayList<String> line = new ArrayList<String>(); 
			String[] tokens = console.nextLine().split(delim_regex); 
			for(String token : tokens) {
				line.add(token); 
			}
			data.add(line); 
		}


		return data;
	}


	public static DoubleMatrix parseCSV(ArrayList<ArrayList<String>> csv) {
		DoubleMatrix parsed = DoubleMatrix.zeros(csv.size(),1); 

		for(int c=0; c < csv.get(0).size(); c++) {
			String[] col = new String[csv.size()]; 
			for(int r = 0; r < csv.size(); r++) {
				col[r] = csv.get(r).get(c); 
			}

			int numNumeric = 0; 
			
			for(String token : col) {
				if(isNumeric(token)) {
					numNumeric++; 
				}
			}
			
			double[][] augmented_column = new double[col.length][1];

			if(numNumeric == col.length) {
				for(int i = 0; i < col.length; i++) {
					augmented_column[i][0] = Double.parseDouble(col[i]);
				}
			} else {
				ArrayList<String> previous_labels = new ArrayList<String>(); 

				for(int i = 0; i < col.length; i++) {
					int indx = previous_labels.indexOf(col[i]); 
					if( indx != -1 ) {
						augmented_column[i][0] = (double)(indx);  
					} else {

						previous_labels.add( col[i] );
						augmented_column[i][0] = (double)(previous_labels.indexOf(col[i]));  
					}
				}
			}
			parsed = DoubleMatrix.concatHorizontally(parsed, new DoubleMatrix(augmented_column));
		}

		return parsed; 
	}



	private static boolean isNumeric(String strNum) {
		if (strNum == null || strNum == "") {
			return true;
		}
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static String toCSV(DoubleMatrix data) {
		String r = ""; 
		for(int i = 0; i < data.rows; i++) {
			for(int j = 0; j < data.columns; j++) {
				r+= data.get(i,j) + ","; 
			}
			r+="\n"; 
		}

		return r; 
	}
	
	public static void main(String[] args) throws IOException {
		String url_text = "/Users/jackklingenberg/Downloads/bank/bank.csv";
		
		ArrayList<ArrayList<String>> csv = FileTools.loadLocalCSV(url_text, ";", true);
		
		//System.out.println(csv.get(2).get(0));
		//System.out.println((FileTools.parseCSV(csv)));
		DoubleMatrix parsed = FileTools.parseCSV(csv);
		String text = FileTools.toCSV(parsed); 

		BufferedWriter writer = new BufferedWriter(new FileWriter("./output.csv")); 
		writer.write(text);
		writer.close(); 
	}
}
