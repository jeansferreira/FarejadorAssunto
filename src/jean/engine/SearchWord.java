package jean.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchWord {

	public void search(String file) {

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = "";

			while ((line = in.readLine()) != null) {
				
				String[] splitLine = line.split(" ");
				String regexcpf = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
				String regexcnpj = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}";
				
				Pattern pat;
				
				for (String expr : splitLine) {
					
					// Verifica se a token é um CPF ou CNPJ
					if (expr.length() > 14){
						pat = Pattern.compile(regexcnpj);
					}else{
						pat = Pattern.compile(regexcpf);
					}
					
					// Valida a expressão
					Matcher mat = pat.matcher(expr);
					if (mat.matches())
						System.out.printf("%s -> %s%n", expr, mat.matches());
				}
				/*for (int i = 0; i < splitLine.length; i++) {
					String word = splitLine[i];
					if (word != null)
						System.out.println(word.trim());
						
				}*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
