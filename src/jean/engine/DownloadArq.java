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

public class DownloadArq {
	
	public static void main(String[] args) {
		System.out.println("Download iniciado com sucesso");
		if (downloadArquivo()) {
			System.out.println("Download concluído com sucesso");
		} else {
			System.out.println("Download não concluído");
		}
	}

	private static boolean downloadArquivo() {
		
		BufferedInputStream buf = null;
		FileOutputStream out = null;
		
		String pagina = Util.openURLtoString("http://dados.gov.br/dataset/filiados-partidos-politicos");
		Document doc = Jsoup.parse(pagina);
		Elements links = doc.select("a");

		for (int i = 0; i < links.size(); i++) {
			String href = links.get(i).attr("href");
			System.out.println("href " + href);

			
			String FilePath = "C:\\Users\\jeans\\git\\FarejadorAssunto\\baixados\\";
			
			if (href.contains(".zip")) {
				URL url;
				try {
					url = new URL(href);
					URLConnection con = url.openConnection();
					buf = new BufferedInputStream(con.getInputStream());
					String zipFile = "file_out_"+ i + ".zip";
					String zip = FilePath + zipFile;
					
					out = new FileOutputStream(zip);
					int j = 0;
					byte[] bytesIn = new byte[1024];
					while ((j = buf.read(bytesIn)) >= 0) {
						out.write(bytesIn, 0, j);
					}
					UnzipFiles.unzip(zip, FilePath);
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return false;
	}
}
