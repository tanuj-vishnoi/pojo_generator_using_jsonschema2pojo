package com.generatepojo;

import java.io.File;
import java.io.IOException;

public class MainClass {

	public static void main(String[] args) {
		
		PojoGenerator pjGenerator = new PojoGenerator();
		String packageName = "com.xyz.pojos";
		File inputJson = new File("." + File.separator + "example.json");
		File outputPojoDirectory = new File(System.getProperty("user.dir")+File.separator+"target" + File.separator + "findpojo");
		outputPojoDirectory.mkdirs();
		
		try {
			pjGenerator.convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName,
					inputJson.getName().replace(".json", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Encountered issue while converting to pojo: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
