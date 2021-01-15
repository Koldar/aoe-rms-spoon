package com.thekoldar.aoe_rms_spoon.framework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.collections.impl.factory.primitive.CharSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zafarkhaja.semver.Version;
import com.thekoldar.aoe_rms_spoon.models.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

public class SpoonFramework {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpoonFramework.class); 
	
	private AbstractAoEVersion targetVersion;
	private Path outputFile;

	public static SpoonFramework instance(AbstractAoEVersion targetVersion, Path outputFile) {
		return new SpoonFramework(targetVersion, outputFile);
	}
	
	private SpoonFramework(AbstractAoEVersion targetVersion, Path outputFile) {
		this.targetVersion = targetVersion;
		this.outputFile = outputFile;
	}
	
	/**
	 * Get spoon version
	 * @return
	 */
	public static Version getSpoonFrameworkVersion() {
		try {
			MavenXpp3Reader reader = new MavenXpp3Reader();
		    Model model;
		    if ((new File("pom.xml")).exists()) {
		    	model = reader.read(new FileReader("pom.xml"));
		    }
		    else {
		    	model = reader.read(new InputStreamReader(SpoonFramework.class.getResourceAsStream("/META-INF/maven/com.thekoldar/aoe-rms-spoon/pom.xml")));
		    }
		    
		    return Version.valueOf(model.getVersion());	
		}
		catch (IOException | XmlPullParserException e) {
			throw new RuntimeException(e);
		}
	}
	
	public AbstractAoEVersion getAgeVersion() {
		return this.targetVersion;
	}
	
	public void generate(Function<AbstractRootNode, AbstractRootNode> script) {
		this.generate((root, logger) -> {
			return script.apply(root);
		});
	}
	
	public void generate(BiFunction<AbstractRootNode, Logger, AbstractRootNode> script) {
		LOG.info("Starting generate RMS file");
		LOG.info("Target: {}", this.targetVersion);
		var root = this.targetVersion.root();
		
		LOG.info("Computing Script to generate...");
		var rmsASTTree = script.apply(root, LOG);
		
		//now perform some semantic analysis
		LOG.info("Performing semantic analysis...");
		var semanticInput = new SemanticCheckInput();
		try {
			var semanticOutput = rmsASTTree.semanticCheck(semanticInput);
			if (semanticOutput != null) {
				//if null, the semantic analysis is completely correct
				for (var w : semanticOutput.getWarnings()) {
					LOG.warn("W{} {}", w.getErrorCode(), w.getMessage());
				}	
			}
			
		} catch (AbstractRMSException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		//coede generate
		LOG.info("generate RMS code");
		var codeGenerationInput = new CodeGenerationInput();
		var codeGenerationOutput = rmsASTTree.codeGeneration(codeGenerationInput);
		
		//dump code generation into a file
		LOG.info("Dump code into file");
		try (var writer = new PrintStream(this.outputFile.toFile())) {
			for (var l : codeGenerationOutput.getLines()) {
				writer.println(l);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		LOG.info("Done!");
	}
	
}
