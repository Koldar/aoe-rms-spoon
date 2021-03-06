package com.thekoldar.aoe_rms_spoon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import org.eclipse.collections.api.factory.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import com.github.zafarkhaja.semver.Version;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.SeasonProvidedConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.de.DefinitiveEdition;
import com.thekoldar.aoe_rms_spoon.age_versions.de.DefinitiveEditionImportantFiles;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.functions.RandomNumberNode;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.models.Point2D;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces.TriFunction;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.ConstNotFoundInSymbolTableActionEnum;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.CreateRandomTerrains;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.Lands;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.Libraries;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.Switches;

public class TestSemanticAnalysis {

	
	@Test
	public void test_duplicateSections() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				;
			
			spoon.generate((root, log, aoe) -> {
				
				root.playerSetup();
				
				root.playerSetup();
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.DUPLICATE_TYPE);
		
	}
	
	@Test()
	public void test_RequiredPlayer() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon.generate((root, log, aoe) -> {
				
				root.landGeneration().createPlayerLands(
						aoe.terrainType("GRASS3"),
						aoe.baseSize(19),
						aoe.landPercent(5),
						aoe.borderFuzziness(3),
						aoe.clumpingFactor(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement(),
						aoe.circleRadius(18)
				);
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.DUPLICATE_TYPE);
	}
	
	@Test()
	public void test_RequiredObjects() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon.generate((root, log, aoe) -> {
				
				root.playerSetup();
				
				root.landGeneration().createPlayerLands(
						aoe.terrainType("GRASS3"),
						aoe.baseSize(19),
						aoe.landPercent(5),
						aoe.borderFuzziness(3),
						aoe.clumpingFactor(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement(),
						aoe.circleRadius(18)
				);
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.DUPLICATE_TYPE);
	}
	
	@Test
	public void test_IncludeNotFound() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon.generate((root, log, aoe) -> {
				
				root.playerSetup();
				
				root.landGeneration().createPlayerLands(
						aoe.terrainType("GRASS3"),
						aoe.baseSize(19),
						aoe.landPercent(5),
						aoe.borderFuzziness(3),
						aoe.clumpingFactor(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement(),
						aoe.circleRadius(18)
				);
				
				root.objectsGeneration();
				
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.REQUIRED_INCLUDE_NOT_INCLUDED);
		
	}
	
	@Test
	public void test_VillsNotFound() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon.generate((root, log, aoe) -> {
				
				var de = (DefinitiveEdition) aoe;
				
				root.includeDrs(de.getImportantFiles().generatingObjects());
				
				root.playerSetup();
				
				root.landGeneration().createPlayerLands(
						aoe.terrainType("GRASS3"),
						aoe.baseSize(19),
						aoe.landPercent(5),
						aoe.borderFuzziness(3),
						aoe.clumpingFactor(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement(),
						aoe.circleRadius(18)
				);
				
				root.objectsGeneration()
				.defines("GNR_NORMALTC", "GNR_CLASSICSCOUT")
				;
				
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.DEFINE_IS_NOT_DEFINED);
		
	}
	
	@Test
	public void test_TownCenterNotFound() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon.generate((root, log, aoe) -> {
				
				var de = (DefinitiveEdition) aoe;
				
				root.includeDrs(de.getImportantFiles().generatingObjects());
				
				root.playerSetup();
				
				root.landGeneration().createPlayerLands(
						aoe.terrainType("GRASS3"),
						aoe.baseSize(19),
						aoe.landPercent(5),
						aoe.borderFuzziness(3),
						aoe.clumpingFactor(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement(),
						aoe.circleRadius(18)
				);
				
				root.objectsGeneration()
				.defines("GNR_CLASSICSCOUT", "GNR_STARTVILLS")
				;
				
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.DEFINE_IS_NOT_DEFINED);
		
	}
	
	@Test
	public void test_ScoutNotFound() {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon.generate((root, log, aoe) -> {
				
				var de = (DefinitiveEdition) aoe;
				
				root.includeDrs(de.getImportantFiles().generatingObjects());
				
				root.playerSetup();
				
				root.landGeneration().createPlayerLands(
						aoe.terrainType("GRASS3"),
						aoe.baseSize(19),
						aoe.landPercent(5),
						aoe.borderFuzziness(3),
						aoe.clumpingFactor(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement(),
						aoe.circleRadius(18)
				);
				
				root.objectsGeneration()
				.defines("GNR_NORMALTC", "GNR_STARTVILLS")
				;
				
				return root;
			});
		});
		
		assertEquals(ex.getErrorCode(), RMSErrorCode.DEFINE_IS_NOT_DEFINED);
		
	}
	
	@Test
	public void teamClumpingFactorySetTo101() throws AbstractRMSException {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of("test.rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
			.setOutputFile(outputFile)
			;
			
			spoon
				.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.setCodeAsError(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.deleteOutputFilesIfPossible()
				.setOutputFileInLocalMods("ya-team-arena")
				;
			
			spoon.generate((root, log, aoe) -> {
				log.info("add file info");
				
				
			
				root
					.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
					.addStatement(Libraries.stdBool(aoe))
				;
				
				var playerSetup = root.playerSetup();
				
				playerSetup
					.groupedByTeam()
					.aiInfoMapType(
							RMSExprs.constVal("BLACK_FOREST"), 
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("TRUE")
					)
				;
				
				var landGeneration = root.landGeneration();
				
				landGeneration
					.baseTerrain(RMSExprs.constVal("DESERT"))
				;
				
				//setting DLC_RAINFOREST with a terrain_type will cerate the forest as well!
				landGeneration
					.comment("Setup player positions")
					.createPlayerLands(RMSExprs.dict(
							aoe.terrainType("GRASS"),
							aoe.baseSize(5),
							aoe.landPercent(10),
							aoe.clumpingFactor(5),
							aoe.borderFuzziness(101),
							aoe.setZoneByTeam(),
							aoe.otherZoneAvoidanceDistance(0),
							aoe.circlePlacement(),
							aoe.circleRadius(20, 0)
					))
				;
				
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("DLC_SAVANNAH"),
						//spoon.getAgeVersion().baseSize(20),
						spoon.getAgeVersion().landPercent(30),
						spoon.getAgeVersion().clumpingFactor(10),
						spoon.getAgeVersion().landPosition(50, 50),
						spoon.getAgeVersion().topBorder(20),
						spoon.getAgeVersion().leftBorder(20),
						spoon.getAgeVersion().rightBorder(20),
						spoon.getAgeVersion().bottomBorder(20),
						spoon.getAgeVersion().borderFuzziness(0),
						spoon.getAgeVersion().otherZoneAvoidanceDistance(0),
						spoon.getAgeVersion().landId(9),
						spoon.getAgeVersion().zone(9)
				));
				var objectGeneration = root.objectsGeneration();
				
				objectGeneration
					.includeDrs(((DefinitiveEditionImportantFiles)aoe.getImportantFiles()).generatingObjects());
				;
				
				log.info("final script");
				return root;
			});
		
			Assert.assertFalse(outputFile.toFile().exists());
		});
	}
	
	@Test
	public void testborderFuzzinessSetToMinus1() throws AbstractRMSException {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of("test.rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition())
					.setOutputFile(outputFile)
					;
			
			spoon
				.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.setCodeAsError(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.deleteOutputFilesIfPossible()
				.setOutputFileInLocalMods("ya-team-arena")
				;
				
			spoon.generate((root, log, aoe) -> {
				log.info("add file info");
				
			
				root
					.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
					.addStatement(Libraries.stdBool(aoe))
				;
				
				var playerSetup = root.playerSetup();
				
				playerSetup
					.groupedByTeam()
					.aiInfoMapType(
							RMSExprs.constVal("BLACK_FOREST"), 
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("TRUE")
					)
				;
				
				var landGeneration = root.landGeneration();
				
				landGeneration
					.baseTerrain(RMSExprs.constVal("DESERT"))
				;
				
				//setting DLC_RAINFOREST with a terrain_type will cerate the forest as well!
				landGeneration
					.comment("Setup player positions")
					.createPlayerLands(RMSExprs.dict(
							aoe.terrainType("GRASS"),
							aoe.baseSize(5),
							aoe.landPercent(10),
							aoe.clumpingFactor(5),
							aoe.borderFuzziness(-1),
							aoe.setZoneByTeam(),
							aoe.otherZoneAvoidanceDistance(0),
							aoe.circlePlacement(),
							aoe.circleRadius(20, 0)
					))
				;
				
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("DLC_SAVANNAH"),
						//spoon.getAgeVersion().baseSize(20),
						spoon.getAgeVersion().landPercent(30),
						spoon.getAgeVersion().clumpingFactor(10),
						spoon.getAgeVersion().landPosition(50, 50),
						spoon.getAgeVersion().topBorder(20),
						spoon.getAgeVersion().leftBorder(20),
						spoon.getAgeVersion().rightBorder(20),
						spoon.getAgeVersion().bottomBorder(20),
						spoon.getAgeVersion().borderFuzziness(0),
						spoon.getAgeVersion().otherZoneAvoidanceDistance(0),
						spoon.getAgeVersion().landId(9),
						spoon.getAgeVersion().zone(9)
				));
				var objectGeneration = root.objectsGeneration();
				
				objectGeneration
					.includeDrs(((DefinitiveEditionImportantFiles)aoe.getImportantFiles()).generatingObjects());
				;
				
				log.info("final script");
				return root;
			});
			
			Assert.assertFalse(outputFile.toFile().exists());
			
		});
	}
	
	@Test
	public void teamZoneSetTo99() throws AbstractRMSException {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of("test.rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition());
			
			spoon
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.setCodeAsError(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.deleteOutputFilesIfPossible()
				.setOutputFileInLocalMods("ya-team-arena")
				;
				
			spoon.generate((root, log, aoe) -> {
				log.info("add file info");
			
				root
					.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
					.addStatement(Libraries.stdBool(aoe))
				;
				
				var playerSetup = root.playerSetup();
				
				playerSetup
					.groupedByTeam()
					.aiInfoMapType(
							RMSExprs.constVal("BLACK_FOREST"), 
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("TRUE")
					)
				;
				
				var landGeneration = root.landGeneration();
				
				landGeneration
					.baseTerrain(RMSExprs.constVal("DESERT"))
				;
				
				//setting DLC_RAINFOREST with a terrain_type will cerate the forest as well!
				landGeneration
					.comment("Setup player positions")
					.createPlayerLands(RMSExprs.dict(
							aoe.terrainType("GRASS"),
							aoe.baseSize(5),
							aoe.landPercent(10),
							aoe.clumpingFactor(5),
							aoe.borderFuzziness(100),
							aoe.setZoneByTeam(),
							aoe.otherZoneAvoidanceDistance(0),
							aoe.circlePlacement(),
							aoe.circleRadius(20, 0)
					))
				;
				
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("DLC_SAVANNAH"),
						//spoon.getAgeVersion().baseSize(20),
						spoon.getAgeVersion().landPercent(30),
						spoon.getAgeVersion().clumpingFactor(10),
						spoon.getAgeVersion().landPosition(50, 50),
						spoon.getAgeVersion().topBorder(20),
						spoon.getAgeVersion().leftBorder(20),
						spoon.getAgeVersion().rightBorder(20),
						spoon.getAgeVersion().bottomBorder(20),
						spoon.getAgeVersion().borderFuzziness(0),
						spoon.getAgeVersion().otherZoneAvoidanceDistance(0),
						spoon.getAgeVersion().landId(9),
						spoon.getAgeVersion().zone(99)
				));
				var objectGeneration = root.objectsGeneration();
				
				objectGeneration
					.includeDrs(((DefinitiveEditionImportantFiles)aoe.getImportantFiles()).generatingObjects());
				;
				
				log.info("final script");
				return root;
			});
		
			Assert.assertFalse(outputFile.toFile().exists());
			
		});
	}
	
	public void generate(TriFunction<AbstractRootNode, Logger, AbstractAoEVersion, AbstractRootNode> script) throws AbstractRMSException {
		
		var ex = assertThrows(AbstractRMSException.class, () -> {
			var outputFile = Path.of("test.rms");
			var spoon = SpoonFramework.instance(new DefinitiveEdition());
			
			spoon
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.setCodeAsError(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.deleteOutputFilesIfPossible()
				.setOutputFileInLocalMods("ya-team-arena")
				;
				
			spoon.generate((root, log, aoe) -> {
				log.info("add file info");
			
				root
					.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
					.addStatement(Libraries.stdBool(aoe))
				;
				
				var playerSetup = root.playerSetup();
				
				playerSetup
					.groupedByTeam()
					.aiInfoMapType(
							RMSExprs.constVal("BLACK_FOREST"), 
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("FALSE"),
							RMSExprs.constVal("TRUE")
					)
				;
				
				var landGeneration = root.landGeneration();
				
				landGeneration
					.baseTerrain(RMSExprs.constVal("DESERT"))
				;
				
				//setting DLC_RAINFOREST with a terrain_type will cerate the forest as well!
				landGeneration
					.comment("Setup player positions")
					.createPlayerLands(RMSExprs.dict(
							aoe.terrainType("GRASS"),
							aoe.baseSize(5),
							aoe.landPercent(10),
							aoe.clumpingFactor(5),
							aoe.borderFuzziness(100),
							aoe.setZoneByTeam(),
							aoe.otherZoneAvoidanceDistance(0),
							aoe.circlePlacement(),
							aoe.circleRadius(20, 0)
					))
				;
				
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("DLC_SAVANNAH"),
						//spoon.getAgeVersion().baseSize(20),
						spoon.getAgeVersion().landPercent(30),
						spoon.getAgeVersion().clumpingFactor(10),
						spoon.getAgeVersion().landPosition(50, 50),
						spoon.getAgeVersion().topBorder(20),
						spoon.getAgeVersion().leftBorder(20),
						spoon.getAgeVersion().rightBorder(20),
						spoon.getAgeVersion().bottomBorder(20),
						spoon.getAgeVersion().borderFuzziness(0),
						spoon.getAgeVersion().otherZoneAvoidanceDistance(0),
						spoon.getAgeVersion().landId(9),
						spoon.getAgeVersion().zone(4)
				));
				var objectGeneration = root.objectsGeneration();
				
				objectGeneration
					.includeDrs(((DefinitiveEditionImportantFiles)aoe.getImportantFiles()).generatingObjects());
				;
				
				RandomBlockBuilder.instance()
					.percentChance(5, aoe.constant("ABS", 42))
				.endRandom();
				
				
				
				log.info("final script");
				return root;
			});
		
			Assert.assertFalse(outputFile.toFile().exists());
			
		});
	}
	
	@Test
	public void defaultTerrainReplacement() throws AbstractRMSException {

		Assert.assertThrows(AbstractRMSException.class, () -> {
			var spoon = SpoonFramework.instance(new DefinitiveEdition());

			//configure spoon
			spoon
				//.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.setCodeAsError(RMSErrorCode.WRONG_CHILDREN_NUMBER)
				.deleteOutputFilesIfPossible()
				.setConstNotFoundInSymbolTableAction(ConstNotFoundInSymbolTableActionEnum.ASSUME_0)
			;
			
			//create script

			spoon.generate((root, log, aoe) -> {

				//add season
				root
					.includeDrs("F_seasons.inc")
					.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				;

				var playerSetup = root.playerSetup();

				playerSetup.groupedByTeam()
					.aiInfoMapType(RMSExprs.constVal("BLACK_FOREST"), RMSExprs.constVal("FALSE"), RMSExprs.constVal("FALSE"), RMSExprs.constVal("TRUE"))
				;
				
				var connectionGeneration = root.connectionGeneration();
				
				connectionGeneration
					.createConnectTeamsLands(
						aoe.replaceTerrain("DESERT", "DESERT"),
						aoe.replaceTerrain("PALM_DESERT", "GRASS"),
						aoe.replaceTerrain("GRASS", "GRASS"),
						aoe.replaceTerrain("GRASS3", "GRASS3"),
						aoe.defaultTerrainReplacement("CUSTOM_BASE_PLAYER_TERRAIN"),
						
						aoe.terrainCost("PALM_DESERT", 3),
						aoe.terrainCost("GRASS", 1),
						aoe.terrainCost("GRASS2", 1),
						aoe.terrainCost("GRASS3", 1),
						aoe.terrainCost("ROAD", 0),
						
						aoe.terrainSize("PALM_DESERT", 5, 0),
						aoe.terrainSize("GRASS", 5, 0),
						aoe.terrainSize("GRASS3", 5, 0),
						aoe.terrainSize("DESERT", 5, 0)
				);
//				
//				root.constant("WELL", 1567);

				var objectGeneration = root.objectsGeneration();

				objectGeneration
						.includeDrs(((DefinitiveEditionImportantFiles) aoe.getImportantFiles()).generatingObjects());
				;

				log.info("final script");
				return root;
			});
			
			Assert.assertFalse(spoon.getOutputFile().toFile().exists());
		});
	}
}
