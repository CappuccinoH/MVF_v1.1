package mvf.controller.utility;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
	private String filePath;

	public FileIO() {
		filePath = "";
	}

	public FileIO(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void write(String str) {
		try {
			PrintWriter pw = new PrintWriter(filePath);
			pw.print(str);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fr = new FileReader(filePath);
			Scanner console = new Scanner(fr);
			while (console.hasNextLine()) {
				sb.append(console.nextLine());
				if (console.hasNextLine()) {
					sb.append("\r\n");
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
