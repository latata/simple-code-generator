package pl.openaim.simplecodegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.simple.JSONObject;

public class CodeGenerator {
	private JSONObject parameters;
	private String rootDirName;
	private VelocityEngine ve;
	private VelocityContext context;

	public CodeGenerator(JSONObject parameters, String rootDirName) {
		super();
		this.parameters = parameters;
		this.rootDirName = rootDirName;
		Properties p = new Properties();
		p.setProperty("resource.loader", "file");
		p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		p.setProperty("file.resource.loader.path", "/");
		ve = new VelocityEngine(p);
		ve.init();
		context = new VelocityContext();
		context.put("p", parameters);
	}

	public void run() {
		System.out.println("Start generating...");
		File rootDir = new File(rootDirName);
		String resultDirName = rootDirName + "_result";
		File resultDir = new File(resultDirName);
		resultDir.mkdirs();
		generateForDir(rootDir, resultDirName);
		System.out.println("Code has been generated successfully in '" + resultDirName + "' directory");
	}

	private void generateForDir(File rootDir, String path) {
		File[] files = rootDir.listFiles();
		if (files != null)
			for (File f : files) {
				String newName = fillWithParameters(f.getName());
				if (f.isDirectory()) {
					String newPath = path + File.separator + newName;
					File newDir = new File(newPath);
					newDir.mkdirs();
					generateForDir(f, newPath);
				} else if(f.isFile() && f.getName().matches(".*\\.vm$")) {
					newName = newName.substring(0, newName.length()-3);
					String newPath = path + File.separator + newName;
					Template t = ve.getTemplate(f.getAbsolutePath());
					PrintWriter writer;
					try {
						writer = new PrintWriter(new File(newPath));
						t.merge(context, writer);
						writer.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
					
				}
			}

	}

	private String fillWithParameters(String name) {
		for(Object key : parameters.keySet()) {
			name = name.replaceAll("__" + key.toString() + "__", parameters.get(key).toString());
		}
		return name;
	}

}
