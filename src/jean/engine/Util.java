package jean.engine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;


public class Util{
	
	public static String openURLtoString(String endereco){
		String retorno = "";
		try {
				URL url = new URL(endereco);
	
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        
		        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
		        
		        BufferedReader in = new BufferedReader(
		                                new InputStreamReader(
		                                		connection.getInputStream()));
		        String inputLine;
		
		        while ((inputLine = in.readLine()) != null){ 
		        	retorno +=inputLine+"\n";
		        }
		        in.close();
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static boolean LoadImageUrlB(String endereco){
		URL url = null;
		File filename = new File((new File(endereco)).getName());
		
		if(filename.exists()){
			return false;
		}
		
		try {
			url = new URL(endereco);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		if (url != null) {
			try{
				InputStream in = (InputStream) url.openStream();

				FileOutputStream fout = new FileOutputStream(filename);

				byte bb[] = new byte[3472];
				int size = -1;

				while ((size = in.read(bb)) != -1) {
					fout.write(bb, 0, size);
				}

				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	
	public static boolean LoadFileUrl(File file,String endereco){
		URL url = null;
		File filename = file;
		
		if(filename.exists()){
			return false;
		}
		
		try {
			url = new URL(endereco);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		if (url != null) {
			try {
				InputStream in = (InputStream) url.openStream();

				FileOutputStream fout = new FileOutputStream(filename);

				byte bb[] = new byte[3472];
				int size = -1;

				while ((size = in.read(bb)) != -1) {
					fout.write(bb, 0, size);
				}

				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
