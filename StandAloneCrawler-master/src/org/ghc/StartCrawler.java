package org.ghc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

public class StartCrawler {
	private static Properties properties = null;

	static {

		try {
			// this is the logger file location
			FileOutputStream fileOutputStream = new FileOutputStream("./loggerFile.txt", true);

			PrintStream printStream = new PrintStream(fileOutputStream);
			System.setOut(printStream);

			// right now we are not using the logger file ,in future we can use
			// the logger file.

			properties = new Properties();
			properties.load(new FileInputStream(
					/* "C:/Users/pritishk/Downloads/StandAloneCrawler-master/StandAloneCrawler-master/src/org/ghc/config.properties" */
					"./src/org/ghc/config.properties"));

		} catch (Exception e) {
			throw new RuntimeException(
					"Config file is not loaded successfully,make sure the config file should be in src folder");
		}
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			throw new RuntimeException(
					"It must have the four argument, first argument contains the folder details ,2nd  argument contains searchContent and 3rd argument contains the replaceContent, 4th argument contains extension of the file");
		}
		FileUtility fileUtility = new FileUtility();
		File dirFolder = new File(args[0]);

		if (dirFolder.exists() && dirFolder.isDirectory()) {
			List<File> files = FileUtility.getAllFilesFromRootDirectory(args[0]);
			fileUtility.processAllFiles(files, args[1], args[2], args[3]);

		} else {
			throw new RuntimeException("The given directory is not present and given dirName=" + args[0]);
		}

	}

}
