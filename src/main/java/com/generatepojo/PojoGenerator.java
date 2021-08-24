package com.generatepojo;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class PojoGenerator {

	/**
	 * 
	 * @param inputJson the input JSON is the location of file that you want to convert in json
	 * @param outputPojoDirectory yields created java file her
	 * @param packageName define package name of the pojos
	 * @param className the main pojo :: entry point 
	 * @throws IOException
	 */
	public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className)
			throws IOException {
		JCodeModel codeModel = new JCodeModel();
		URL source = inputJson;
		GenerationConfig config = new DefaultGenerationConfig() {
			@Override
			public boolean isGenerateBuilders() { // set config option by overriding method
				return false;
			}

			@Override
			public SourceType getSourceType() {
				return SourceType.JSON;
			}
			
			@Override
		    public boolean isIncludeAdditionalProperties() {
		        return false;
		    }

			
		};
		
		SchemaMapper mapper = new SchemaMapper(
				new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, className, packageName, source);
		codeModel.build(outputPojoDirectory);
	}
}
