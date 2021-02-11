package com.thekoldar.aoe_rms_spoon.framework;

import java.lang.Long;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.primitive.CharSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zafarkhaja.semver.Version;
import com.opencsv.CSVWriter;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces.TriFunction;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.ConstNotFoundInSymbolTableActionEnum;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;

public class SpoonFramework {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpoonFramework.class); 
	
	private AbstractAoEVersion targetVersion;
	private Path outputFile;
	/**
	 * list of error codes that are treated as warning
	 */
	private MutableSet<RMSErrorCode> treatedAsWarning;
	/**
	 * list of error codes that are trated as errors
	 */
	private MutableSet<RMSErrorCode> treatedAsError;
	
	/**
	 * image of the AST tree to generate
	 */
	@Nullable
	private Path generateASTImage;
	/**
	 * if true, the semantic analysis will be skipped altogether. Strongly discouraged.
	 * It is present only if the semantic analysis generates too many problems
	 */
	private boolean disableSemanticAnalysis;
	
	private boolean enableComments;
	
	/**
	 * what should we be if a value is not found in the symbol table
	 */
	private ConstNotFoundInSymbolTableActionEnum constNotFoundInSymbolTableAction;
	
	/**
	 * structure that is used by the semantic analysis to keep bookmark about the state of the anslysis
	 */
	private SemanticCheckInput semanticInput;
	
	/**
	 * if true, we will show additional information before generating the script
	 */
	private boolean showInfo;
	
	/**
	 * if not null, represent sthe csv file that we want to create containing all the terrains detected by the framework
	 */
	@Nullable
	private Path csvOutputFile;

	public static SpoonFramework instance(AbstractAoEVersion targetVersion) {
		return new SpoonFramework(targetVersion);
	}
	
	private SpoonFramework(AbstractAoEVersion targetVersion) {
		this.targetVersion = targetVersion;
		this.outputFile = Paths.get("output.rms");
		this.treatedAsError = Sets.mutable.empty();
		this.treatedAsWarning = Sets.mutable.empty();
		this.generateASTImage = null;
		this.csvOutputFile = null;
		this.disableSemanticAnalysis = false;
		this.enableComments = true;
		this.semanticInput = null;
		this.showInfo = false;
	}
	
	public SpoonFramework setConstNotFoundInSymbolTableAction(ConstNotFoundInSymbolTableActionEnum x) {
		this.constNotFoundInSymbolTableAction = x;
		return this;
	}
	
	public SpoonFramework setOutputFile(Path outputFile) {
		this.outputFile = outputFile;
		return this;
	}
	
	/**
	 * if true, we will show additional information when starting the script
	 * @param s
	 * @return
	 */
	public SpoonFramework requestShowInfo(boolean s) {
		this.showInfo = s;
		return this;
	}
	
	/**
	 * request to generate all the terrains avaialable for this Age of empires version in csv file
	 * 
	 * @param csvOutputFile path of the file that will store the csv contaiing all detected terrains
	 * @return this
	 */
	public SpoonFramework generateTerrainCSV(Path csvOutputFile) {
		this.csvOutputFile = csvOutputFile;
		return this;
	}
	
	/**
	 * disable the semantic analysis. Cannot be turned on again
	 * This means that no checks will be performed to ensure the validitiy of the generated code
	 * @return this
	 */
	public SpoonFramework disableSemanticAnalysis() {
		this.disableSemanticAnalysis = true;
		return this;
	}
	
	/**
	 * if true, we will output in the rms file the comments as well
	 * @param enableComments true if you want to output comments, false otehrwise
	 * @return this
	 */
	public SpoonFramework outputComments(boolean enableComments) {
		this.enableComments = enableComments;
		return this;
	}
	
	/**
	 * request to generate all the terrains avaialable for this Age of empires version in csv file
	 * 
	 * @param csvOutputFile path of the file that will store the csv contaiing all detected terrains
	 * @return this
	 */
	public SpoonFramework generateTerrainCSV(String csvOutputFile) {
		return this.generateTerrainCSV(Paths.get(csvOutputFile));
	}
	
	/**
	 * set the output file. MIddle directories in a mod installed locally
	 * @param steamId steam id of the user
	 * @param modName mod name you want to create
	 * @param mapName map name you want to create
	 * @return
	 */
	public SpoonFramework setOutputFileInLocalMods(String steamId, String modName, String mapName) {
		var homeDrive = System.getenv("HOMEDRIVE");
		var homePath = System.getenv("HOMEPATH");
		this.outputFile = Paths.get(homeDrive, homePath, "Games", this.targetVersion.getUserGameDirectoryName(), steamId, "mods", "local", modName, "resources", "_common", "random-map-scripts", String.format("%s.rms", mapName));
		return this;
	}
	
	/**
	 * set the output file. steam id is automatically fetched from the existing directory
	 * @param modName name fo the mod where you want to install the map
	 * @param mapName map name you want to create
	 * @return
	 */
	public SpoonFramework setOutputFileInLocalMods(String modName, String mapName) {
		return this.setOutputFileInLocalMods(this.getSteamId(), modName, mapName);
	}
	
	/**
	 * set the output file. steam id is automatically fetched from the existing directory. Map name is the set to be the same of modName
	 * @param modName name fo the mod where you want to install the map
	 * @return
	 */
	public SpoonFramework setOutputFileInLocalMods(String modName) {
		return this.setOutputFileInLocalMods(this.getSteamId(), modName, modName);
	}
	
	/**
	 * try to get the steam id from the Age of empires 2 directory
	 * @return
	 */
	private String getSteamId() {
		var homeDrive = System.getenv("HOMEDRIVE");
		var homePath = System.getenv("HOMEPATH");
		var folder = Paths.get(homeDrive, homePath, "Games", this.targetVersion.getUserGameDirectoryName());
		for (File f : folder.toFile().listFiles(File::isDirectory)) {
			try {
				var n = Long.parseLong(f.getName());
				if (n > 0) {
					return f.getName();
				}
			} catch (NumberFormatException e) {
				continue;
			}
		}
		throw new IllegalArgumentException(String.format("Cannot find steam id within the folder %s", folder));
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
	
	/**
	 * get the output path
	 * @return
	 */
	public Path getOutputFile() {
		return this.outputFile.toAbsolutePath().normalize();
	}
	
	/**
	 * Declare that you want to force this code to be treated as a warning
	 * 
	 * @param errorCode the error code to alter
	 */
	public SpoonFramework setCodeAsWarning(RMSErrorCode errorCode) {
		this.treatedAsWarning.add(errorCode);
		return this;
	}
	
	/**
	 * Declare that you want to force this code to be treated as a warning
	 * 
	 * @param errorCode the error code to alter
	 */
	public SpoonFramework setCodeAsError(RMSErrorCode errorCode) {
		this.treatedAsError.add(errorCode);
		return this;
	}

	/**
	 * delete the output files, if possible. If any exception occurs, it is silently ignored
	 */
	public SpoonFramework deleteOutputFilesIfPossible() {
		try {
			this.outputFile.toFile().delete();	
		} catch (Exception e) {
		}
		return this;
	}
	
	/**
	 * request the framework to generate the AST tree, just before running the semantic analysis
	 * @param image the image file to generate. You should include the extension as well
	 * @return this
	 */
	public SpoonFramework generateASTTreeImage(String image) {
		this.generateASTImage = Paths.get(image);
		return this;
	}
	
	/**
	 * request the framework to generate the AST tree, just before running the semantic analysis
	 * @param image the image file to generate. You should include the extension as well
	 * @return this
	 */
	public SpoonFramework generateASTTreeImage(Path image) {
		this.generateASTImage = image;
		return this;
	}
	
	public AbstractAoEVersion getAgeVersion() {
		return this.targetVersion;
	}
	
	/**
	 * Inform the semantic analysis that a constant has been defined. It is usually very useful when you include a file and you
	 * know that such a file generates some "const" set to some values. For examnple "F_season.inc" is such a file 
	 * @param constant const to set
	 */
	public void informSemanticAnalysisThatConstIsDefined(String constant) {
		if (this.semanticInput != null) {
			this.semanticInput.knowThatConstCanAlsoBe(constant, 0); // 0: a dummy value
		}
	}
	
	/**
	 * Inform the semantic analysis that a constant has been defined. It is usually very useful when you include a file and you
	 * know that such a file generates some "const" set to some values. For examnple "F_season.inc" is such a file
	 *  
	 * @param constants const to set
	 */
	public void informSemanticAnalysisThatConstIsDefined(RichIterable<String> constants) {
		constants.forEach(c -> this.informSemanticAnalysisThatConstIsDefined(c));
	}
	
	/**
	 * Inform the semantic analysis that a constant has been defined. It is usually very useful when you include a file and you
	 * know that such a file generates some "const" set to some values. For examnple "F_season.inc" is such a file
	 *  
	 * @param constants const to set
	 */
	public void informSemanticAnalysisThatConstIsDefined(String... constants) {
		this.informSemanticAnalysisThatConstIsDefined(Lists.immutable.of(constants));
	}
	
	public void generate(Function<AbstractRootNode, AbstractRootNode> script) throws AbstractRMSException{
		this.generate((root, logger) -> {
			return script.apply(root);
		});
	}
	
	public void generate(BiFunction<AbstractRootNode, Logger, AbstractRootNode> script) throws AbstractRMSException {
		this.generate((root, logger, aoeVersion) -> {
			return script.apply(root, logger);
		});
	}
	
	private void generateAST(Path image, IRMSNode node) throws IOException, InterruptedException {
		var dotFile = new File(image.toString() + ".dot").toPath();
		try (var pw = new PrintStream(dotFile.toFile(), StandardCharsets.US_ASCII)) {
			pw.println("digraph {");
			pw.println();
			//nodes
			this.generateASTNodes(pw, node);
			//edges
			this.generateASTEdges(pw, node);
			
			pw.println();
			pw.println("}");
		} catch ( IOException e) {
			throw new RuntimeException(e);
		}
		
		var extension = FilenameUtils.getExtension(image.toString());
		var cmd = String.format("dot -T%s -o \"%s\" \"%s\"", extension, image.toString(), dotFile.toString());
		LOG.info("Calling dot to build AST tree...");
		LOG.info(cmd);
		Runtime.getRuntime().exec(cmd).waitFor();
		LOG.info("Deleting dot file...");
		Files.deleteIfExists(dotFile);
		LOG.info("AST tree is available {}", image.toAbsolutePath());
	}
	
	private void generateASTEdges(PrintStream pw, IRMSNode node) {
		for (var child : node.getChildren()) {
			pw.println(String.format("N%016d -> N%016d;", System.identityHashCode(node), System.identityHashCode(child)));
			this.generateASTEdges(pw, child);
		}
	}
	
	private void generateASTNodes(PrintStream pw, IRMSNode node) {
		pw.println(String.format("N%016d [label=\"%s\\n%s\"];", System.identityHashCode(node), node.getNodeType(), node.getLabel()));
		for (var child : node.getChildren()) {
			this.generateASTNodes(pw, child);
		}
	}
	
	private void generateTerrainCsv(Path csvOutputFile, AbstractAoEVersion aoe) throws IOException {
		try (var writer = new CSVWriter(new FileWriter(csvOutputFile.toAbsolutePath().normalize().toString()))) {
			//write header
			writer.writeNext(new String[] {
					"Row Id",
					"RMS Const Name", 
					"RMS Const Value", 
					"Is land walkable", 
					"Is ship walkable", 
					"Implicitly creates forest", 
					"Is buildable", 
					"Is dockable", 
					"Is wallable", 
					"Can natural resources be put on it", 
					"Color",
					"Texture"
			}, false);
			
			aoe.getUsableTerrains().forEachWithIndex((terrain, index) -> {
				writer.writeNext(new String[] {
					Integer.toString(index), //"Row Id",
					terrain.getConstName(), //"RMS Const Name", 
					Integer.toString(terrain.getConstValue()), //"RMS Const Value", 
					terrain.isLandWalkable() ? "yes" : "no", //"is land walkable", 
					terrain.isShipWalkable() ? "yes" : "no", //"is ship walkable", 
					terrain.isForest() ? "yes" : "no", //"implicitly creates forest", 
					terrain.isBuildable() ? "yes" : "no", //"is buildable", 
					terrain.isDockable() ? "yes" : "no", //"is dockable",
					terrain.isWallable() ? "yes" : "no", //"is wallable",
					terrain.canNaturalResourcesBePutOnIt() ? "yes" : "no", //"can natural resources be put on it", 
					String.format("R=%d G=%d B=%d A=%d", terrain.getColor().getRed(), terrain.getColor().getGreen(), terrain.getColor().getBlue(), terrain.getColor().getAlpha()), //"color"
					terrain.getTextureFile().toString() //texture
				}, false);
			});
		}
	}
	
	/**
	 * show additional information regarding the context where we are operating on
	 */
	private void showInfo() {
		BiConsumer<String, Object[]> logFunction = LOG::info;
		
		for (var landing : this.targetVersion.getUsableTerrains().select(t -> t.isLandWalkable())) {
			logFunction.accept("Land walkable terrain: {} (value = {})", new Object[] {landing.getConstName(), landing.getConstValue()});
		}
		
		for (var forest : this.targetVersion.getUsableTerrains().select(t -> t.isForest())) {
			logFunction.accept("Forest terrain: {} (value={})", new Object[] {forest.getConstName(), forest.getConstValue()});
		}
		
		for (var gameObject: this.targetVersion.getUsableGameObjects()) {
			logFunction.accept("Game Object: {}", new Object[] { gameObject });
		}
	}
	
	public void generate(TriFunction<AbstractRootNode, Logger, AbstractAoEVersion, AbstractRootNode> script) throws AbstractRMSException {
		
		// *******************************************************
		// generate AST of the RMS script to create
		// *******************************************************
		
		LOG.info("Starting generate RMS file");
		LOG.info("Target: {}", this.targetVersion);
		LOG.info("Output path: {}", this.outputFile.toAbsolutePath().normalize());
		var root = this.targetVersion.root();
		
		//create semantci analysis (we do it here since the suer may give additional information to the smantic analyzer in generate)
		this.semanticInput = new SemanticCheckInput(
				this.treatedAsWarning.toImmutable(), 
				this.treatedAsError.toImmutable(),
				this.constNotFoundInSymbolTableAction
		);
		
		LOG.info("Computing Script to generate...");
		var rmsASTTree = script.apply(root, LOG, this.targetVersion);
		
		// *******************************************************
		// POLL AGE OF EMPIRES VERSION IN RODER TO FETCH CONSTS AND DEFINES
		// *******************************************************
		
		//now perform some semantic analysis
		LOG.info("Performing semantic analysis...");
		
		//before eprforming semantic analysis, we need to load the consts implicitly avaiable by the AGE of Empires versions
		LOG.info("Adding to the known constants the one available from {}...", this.targetVersion.getName());
		for (var c : this.targetVersion.availableConst()) {
			this.semanticInput.knowThatConstCanOnlyBe(c.getName(), c.getValue());
		}
		
		//before eprforming semantic analysis, we need to load the defines implicitly avaiable by the AGE of Empires versions
		LOG.info("Adding to the known define the one available from {}...", this.targetVersion.getName());
		var availableDefines = this.targetVersion.availableDefines().toList();
		for (var define : availableDefines) {
			this.semanticInput.knowThatDefineCanOnlyBe(define.getName(), true);	
		}
		LOG.info("Added {} defines", availableDefines.size());
		
		// *********************************************************
		// show info
		// **********************************************************
		
		if (this.showInfo) {
			this.showInfo();	
		}
		
		// *******************************************************
		// generating AST image (if present)
		// *******************************************************
		LOG.info("Should generate AST tree? {}", this.generateASTImage != null ? "yes" : "no");
		if (this.generateASTImage != null) {
			try {
				this.generateAST(this.generateASTImage, rmsASTTree);
			} catch (IOException | InterruptedException e) {
				LOG.warn("Could not generate AST tree. Occured exception {}", e.getMessage());
			}
		}
		
		// *******************************************************
		// generate the CSV map of the terrains (if present)
		// *******************************************************
		
		if (this.csvOutputFile != null) {
			try {
				this.generateTerrainCsv(this.csvOutputFile, this.targetVersion);
				LOG.info("CSV representing terrain specifications of {} available at \"{}\"", this.targetVersion, this.csvOutputFile.toAbsolutePath().normalize());
			} catch (Exception e) {
				LOG.warn("Could not generate Terrain CSV. Occured exception {}", e.getMessage());
			}
		}
		
		// *******************************************************
		// semantic analysis
		// *******************************************************
		
		if (this.disableSemanticAnalysis) {
			LOG.warn("The user reuqested to disable Semantic analysis. Skipping this important phase. Note that this implies that the semantic input will be absent in the code generation phase as well");
		} else {
			try {
				var semanticOutput = rmsASTTree.semanticCheck(this.semanticInput);
				if (semanticOutput != null) {
					//if null, the semantic analysis is completely correct
					for (var w : semanticOutput.getWarnings()) {
						LOG.warn("W{} {}", w.getErrorCode().getId(), w.getMessage());
					}
				}
				
			} catch (AbstractRMSException e) {
				LOG.error("E{} {}", e.getErrorCode().getId(), e.getMessage());
				throw new RMSSemanticErrorException(e);
			} catch (Exception e) {
				LOG.error("Error while performing semantic analysis (this is very serious!). Error is {}", e.getMessage());
				throw e;
			}
		}
		
		
		// *******************************************************
		// code generation
		// *******************************************************
		
		
		LOG.info("generate RMS code");
		var codeGenerationInput = new CodeGenerationInput(this.disableSemanticAnalysis ? null : this.semanticInput, this.enableComments);
		var codeGenerationOutput = rmsASTTree.codeGeneration(codeGenerationInput);
		
		// *******************************************************
		// print code genreated in a file
		// *******************************************************
		
		
		//dump code generation into a file
		LOG.info("Dump code into file");
		try (var writer = new PrintStream(this.outputFile.toFile())) {
			for (var l : codeGenerationOutput.getTabLines()) {
				writer.println("\t".repeat(l.getOne()) + l.getTwo() + " "); //always add trailing space at the end of the line
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		// *******************************************************
		// finalize code
		// *******************************************************
		
		LOG.info("Done! Output file is available at {}", this.outputFile.toAbsolutePath().normalize() );
	}
	
}
