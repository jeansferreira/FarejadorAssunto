package jean.engine;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DownloaPages {
	
	public static void main(String[] args) {
		System.out.println("Download iniciado com sucesso");
		if (downloadPages()) {
			System.out.println("Download concluído com sucesso");
		} else {
			System.out.println("Download não concluído");
		}
	}

	private static boolean downloadPages() {
		
		BufferedInputStream buf = null;
		FileOutputStream out = null;
		
		//Document doc = Jsoup.parse(pagina);
		//Elements links = doc.select("a");

		String FilePath = "C:\\Users\\jeans\\eclipse\\jee-neon\\workspace\\BaixarArquivos\\paginas\\";
		
		for (int i = 0; i < 1000; i++) {
			
			URL url;
				try {
					//url = new URL("http://www.portaltransparencia.gov.br/ceis/?pagina="+i);
					url = new URL("https://www.google.com.br/search?q=rela%C3%A7%C3%A3o+de+todos+os+hospitais+de+floripa&spell=1&sa=X&ved=0ahUKEwjLsJDEhdjVAhUK4SYKHcqfBJcQvwUIJCgA&biw=1920&bih=960");
					//System.out.println("Paginas baixadas: "+url);
					URLConnection con = url.openConnection();
							
					buf = new BufferedInputStream(con.getInputStream());
					String html = FilePath+ "pagina_"+ i + ".html";
					
					out = new FileOutputStream(html);
					int j = 0;
					byte[] bytesIn = new byte[1024];
					while ((j = buf.read(bytesIn)) >= 0) {
						out.write(bytesIn, 0, j);
					}
					
					SearchWord words = new SearchWord();
					words.search(html);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return false;
	}
}
