package pl.openaim.simplecodegenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Hello world!
 *
 */
public class App {

	private static JSONObject parameters;

	public static void main(String[] args) {
		String templateDir = null;
		if(args.length > 0) {
			templateDir = args[0];
		} else {
			System.out.println("Enter directory name:");

			try {
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				templateDir = bufferRead.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		if (templateDir != null) {
			Object obj = null;
			try {
				obj = JSONValue.parse(new FileReader(templateDir
						+ File.separator + "parameters.json"));
				parameters = (JSONObject) obj;

				CodeGenerator codeGenerator = new CodeGenerator(parameters,
						templateDir);
				codeGenerator.run();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
