package jean.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFiles {
	
	public static void unzip(final String zipFilePath, final String destDir) {
		
		File dir = new File(destDir);
		// Criar o diretório se ele não existe
		if (!dir.exists())
			dir.mkdirs();
		
		FileInputStream fis;
		// Buffer de leitura e escrita do arquivo
		byte[] buffer = new byte[1024];
		
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to " + newFile.getAbsolutePath());
				
				// Criar os Subdiretórios
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				
				fos.close();
				// Liberar o zip
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// Fechar o zip
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
