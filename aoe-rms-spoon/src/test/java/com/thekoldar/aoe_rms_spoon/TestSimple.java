package com.thekoldar.aoe_rms_spoon;

import java.nio.file.Path;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.junit.Assert;
import org.junit.Test;

import com.github.zafarkhaja.semver.Version;
import com.thekoldar.aoe_rms_spoon.age_versions.de.DefinitiveEdition;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.ast.builders.DictExprBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.ConstRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.framework.models.Point2D;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.ConstNotFoundInSymbolTableActionEnum;

public class TestSimple {

	@Test
	public void test_empty() throws AbstractRMSException {
		
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
			.setOutputFile(outputFile)
			.disableSemanticAnalysis()
			;
		
		spoon.generate((root) -> {
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void test_comments() throws AbstractRMSException {
		
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.disableSemanticAnalysis()
				;
		
		spoon.generate((root) -> {
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_01() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.disableSemanticAnalysis()
				;
		
		spoon.generate((root) -> {
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_02() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
		.setOutputFile(outputFile)
		.disableSemanticAnalysis()
		;
		
		spoon.generate((root, log) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_03() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.from(12))
				;
		
		spoon.generate((root, log) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			root.landGeneration()
				.comment("fill the entire map with this terrain. After the inclusi9on, TERRAIN_CHOSEN is populated")
				.includeDrs("get-random-base-land-terrain.inc")
				.baseTerrain(RMSExprs.constVal("TERRAIN_CHOSEN"))
				.createPlayerLands(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS"),
						spoon.getAgeVersion().baseSize().addArgument(19),
						spoon.getAgeVersion().landPercent().addArgument(5),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(15),
						spoon.getAgeVersion().setZoneByTeam(),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().circlePlacement(),
						spoon.getAgeVersion().circleRadius().addArgument(38)
				))
				;
			
			root.objectsGeneration()
				.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				.includeDrs("GeneratingObjects.inc")
				;
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_04() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.from(12))
				;
		
		spoon.generate((root, log) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			var landGeneration = root.landGeneration();
			
			landGeneration
				.comment("fill the entire map with this terrain. After the inclusi9on, TERRAIN_CHOSEN is populated")
				.includeDrs("get-random-base-land-terrain.inc")
				.baseTerrain(RMSExprs.constVal("TERRAIN_CHOSEN"))
			;
			
			landGeneration
				.comment("Setup player positions")
				.createPlayerLands(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS"),
						spoon.getAgeVersion().baseSize().addArgument(19),
						spoon.getAgeVersion().landPercent().addArgument(5),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(15),
						spoon.getAgeVersion().setZoneByTeam(),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().circlePlacement(),
						spoon.getAgeVersion().circleRadius().addArgument(38)
				))
			;
			
			landGeneration.comment("create centered arena");
			
			//20x20 -> 40x40
			//20x40 -> 20x40
			//40x20 -> 40x20
			//40x40 -> 20x20
			var borders = new Point2D[][] { 
					new Point2D[] { new Point2D(20, 20), new Point2D(40, 40), },
					new Point2D[] { new Point2D(20, 40), new Point2D(20, 40), },
					new Point2D[] { new Point2D(40, 20), new Point2D(40, 20), },
					new Point2D[] { new Point2D(40, 40), new Point2D(20, 20), },
			};
			
			for (var border : borders) {
				var topleft = border[0];
				var bottomright = border[1];
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS3"),
						spoon.getAgeVersion().baseSize().addArgument(20),
						spoon.getAgeVersion().landPercent().addArgument(30),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(8),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().topBorder().addArgument(topleft.getX()),
						spoon.getAgeVersion().leftBorder().addArgument(topleft.getY()),
						spoon.getAgeVersion().rightBorder().addArgument(bottomright.getX()),
						spoon.getAgeVersion().bottomBorder().addArgument(bottomright.getY()),
						spoon.getAgeVersion().landId().addArgument(9),
						spoon.getAgeVersion().zone().addArgument(9)
				));
			}
			
			root.objectsGeneration()
				.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				.includeDrs("GeneratingObjects.inc")
			;
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_05() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.from(12))
				;
		
		spoon.generate((root, log) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			var landGeneration = root.landGeneration();
			
			landGeneration
				.comment("fill the entire map with this terrain. After the inclusi9on, TERRAIN_CHOSEN is populated")
				.includeDrs("get-random-base-land-terrain.inc")
				.baseTerrain(RMSExprs.constVal("TERRAIN_CHOSEN"))
			;
			
			landGeneration
				.comment("Setup player positions")
				.createPlayerLands(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS"),
						spoon.getAgeVersion().baseSize().addArgument(19),
						spoon.getAgeVersion().landPercent().addArgument(5),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(15),
						spoon.getAgeVersion().setZoneByTeam(),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().circlePlacement(),
						spoon.getAgeVersion().circleRadius().addArgument(38)
				))
			;
			
			landGeneration.comment("create centered arena");
			
			//20x20 -> 40x40
			//20x40 -> 20x40
			//40x20 -> 40x20
			//40x40 -> 20x20
			var borders = new Point2D[][] { 
					new Point2D[] { new Point2D(20, 20), new Point2D(40, 40), },
					new Point2D[] { new Point2D(20, 40), new Point2D(20, 40), },
					new Point2D[] { new Point2D(40, 20), new Point2D(40, 20), },
					new Point2D[] { new Point2D(40, 40), new Point2D(20, 20), },
			};
			
			for (var border : borders) {
				var topleft = border[0];
				var bottomright = border[1];
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS3"),
						spoon.getAgeVersion().baseSize().addArgument(20),
						spoon.getAgeVersion().landPercent().addArgument(30),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(8),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().topBorder().addArgument(topleft.getX()),
						spoon.getAgeVersion().leftBorder().addArgument(topleft.getY()),
						spoon.getAgeVersion().rightBorder().addArgument(bottomright.getX()),
						spoon.getAgeVersion().bottomBorder().addArgument(bottomright.getY()),
						spoon.getAgeVersion().landId().addArgument(9),
						spoon.getAgeVersion().zone().addArgument(9)
				));
			}
			
			root.terrainGeneration()
				.createTerrain(RMSExprs.constVal("PALM_DESERT"), RMSExprs.dict(
						spoon.getAgeVersion().baseTerrain().addArgument("DESERT"),
						spoon.getAgeVersion().spacingToOtherTerrainTypes().addArgument(0),
						spoon.getAgeVersion().landPercent().addArgument(100),
						spoon.getAgeVersion().numberOfClumps().addArgument(100),
						spoon.getAgeVersion().setAvoidPlayerStartAreas(),
						spoon.getAgeVersion().setScaleByGroups(),
						spoon.getAgeVersion().clumpingFactor().addArgument(15),
						spoon.getAgeVersion().heightLimits().addArgument(0).addArgument(7)
				));
			
			root.objectsGeneration()
			.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				.includeDrs("GeneratingObjects.inc")
			;
				
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_06() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.from(12))
				.setConstNotFoundInSymbolTableAction(ConstNotFoundInSymbolTableActionEnum.ASSUME_0)
				;
		
		spoon.generate((root, log) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			var landGeneration = root.landGeneration();
			
			landGeneration
				.comment("fill the entire map with this terrain. After the inclusi9on, TERRAIN_CHOSEN is populated")
				.includeDrs("get-random-base-land-terrain.inc")
				.baseTerrain(RMSExprs.constVal("TERRAIN_CHOSEN"))
			;
			
			landGeneration
				.comment("Setup player positions")
				.createPlayerLands(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS"),
						spoon.getAgeVersion().baseSize().addArgument(19),
						spoon.getAgeVersion().landPercent().addArgument(5),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(15),
						spoon.getAgeVersion().setZoneByTeam(),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().circlePlacement(),
						spoon.getAgeVersion().circleRadius().addArgument(38)
				))
			;
			
			landGeneration.comment("create centered arena");
			
			//20x20 -> 40x40
			//20x40 -> 20x40
			//40x20 -> 40x20
			//40x40 -> 20x20
			var borders = new Point2D[][] { 
					new Point2D[] { new Point2D(20, 20), new Point2D(40, 40), },
					new Point2D[] { new Point2D(20, 40), new Point2D(20, 40), },
					new Point2D[] { new Point2D(40, 20), new Point2D(40, 20), },
					new Point2D[] { new Point2D(40, 40), new Point2D(20, 20), },
			};
			
			for (var border : borders) {
				var topleft = border[0];
				var bottomright = border[1];
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS3"),
						spoon.getAgeVersion().baseSize().addArgument(20),
						spoon.getAgeVersion().landPercent().addArgument(30),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(8),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().topBorder().addArgument(topleft.getX()),
						spoon.getAgeVersion().leftBorder().addArgument(topleft.getY()),
						spoon.getAgeVersion().rightBorder().addArgument(bottomright.getX()),
						spoon.getAgeVersion().bottomBorder().addArgument(bottomright.getY()),
						spoon.getAgeVersion().landId().addArgument(9),
						spoon.getAgeVersion().zone().addArgument(9)
				));
			}
			
			root.terrainGeneration()
				.createTerrain(RMSExprs.constVal("PALM_DESERT"), RMSExprs.dict(
						spoon.getAgeVersion().baseTerrain().addArgument("DESERT"),
						spoon.getAgeVersion().spacingToOtherTerrainTypes().addArgument(0),
						spoon.getAgeVersion().landPercent().addArgument(100),
						spoon.getAgeVersion().numberOfClumps().addArgument(100),
						spoon.getAgeVersion().setAvoidPlayerStartAreas(),
						spoon.getAgeVersion().setScaleByGroups(),
						spoon.getAgeVersion().clumpingFactor().addArgument(15),
						spoon.getAgeVersion().heightLimits().addArgument(0).addArgument(7)
				));
			
			var connectionGeneration = root.connectionGeneration();
			
			connectionGeneration.createConnectTeamsLands(RMSExprs.dict(
					
					spoon.getAgeVersion().terrainCost().addArguments("PALM_DESERT", 3),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS", 1),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS2", 1),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS3", 1),
					spoon.getAgeVersion().terrainCost().addArguments("ROAD", 0),
					
					spoon.getAgeVersion().terrainSize().addArguments("PALM_DESERT", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("PALM_GRASS", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("PALM_GRASS3", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("DESERT", 5, 0)
			));
			
			root.objectsGeneration()
			.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				.includeDrs("GeneratingObjects.inc")
			;
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_07() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.from(12))
				.setConstNotFoundInSymbolTableAction(ConstNotFoundInSymbolTableActionEnum.ASSUME_0)
				;
		
		spoon.generate((root, log) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			var landGeneration = root.landGeneration();
			
			landGeneration
				.comment("fill the entire map with this terrain. After the inclusi9on, TERRAIN_CHOSEN is populated")
				.includeDrs("get-random-base-land-terrain.inc")
				.baseTerrain(RMSExprs.constVal("TERRAIN_CHOSEN"))
			;
			
			landGeneration
				.comment("Setup player positions")
				.createPlayerLands(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS"),
						spoon.getAgeVersion().baseSize().addArgument(19),
						spoon.getAgeVersion().landPercent().addArgument(5),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(15),
						spoon.getAgeVersion().setZoneByTeam(),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().circlePlacement(),
						spoon.getAgeVersion().circleRadius().addArgument(38)
				))
			;
			
			landGeneration.comment("create centered arena");
			
			//20x20 -> 40x40
			//20x40 -> 20x40
			//40x20 -> 40x20
			//40x40 -> 20x20
			var borders = new Point2D[][] { 
					new Point2D[] { new Point2D(20, 20), new Point2D(40, 40), },
					new Point2D[] { new Point2D(20, 40), new Point2D(20, 40), },
					new Point2D[] { new Point2D(40, 20), new Point2D(40, 20), },
					new Point2D[] { new Point2D(40, 40), new Point2D(20, 20), },
			};
			
			for (var border : borders) {
				var topleft = border[0];
				var bottomright = border[1];
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS3"),
						spoon.getAgeVersion().baseSize().addArgument(20),
						spoon.getAgeVersion().landPercent().addArgument(30),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(8),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().topBorder().addArgument(topleft.getX()),
						spoon.getAgeVersion().leftBorder().addArgument(topleft.getY()),
						spoon.getAgeVersion().rightBorder().addArgument(bottomright.getX()),
						spoon.getAgeVersion().bottomBorder().addArgument(bottomright.getY()),
						spoon.getAgeVersion().landId().addArgument(9),
						spoon.getAgeVersion().zone().addArgument(9)
				));
			}
			
			root.terrainGeneration()
				.createTerrain(RMSExprs.constVal("PALM_DESERT"), RMSExprs.dict(
						spoon.getAgeVersion().baseTerrain().addArgument("DESERT"),
						spoon.getAgeVersion().spacingToOtherTerrainTypes().addArgument(0),
						spoon.getAgeVersion().landPercent().addArgument(100),
						spoon.getAgeVersion().numberOfClumps().addArgument(100),
						spoon.getAgeVersion().setAvoidPlayerStartAreas(),
						spoon.getAgeVersion().setScaleByGroups(),
						spoon.getAgeVersion().clumpingFactor().addArgument(15),
						spoon.getAgeVersion().heightLimits().addArgument(0).addArgument(7)
				));
			
			var connectionGeneration = root.connectionGeneration();
			
			connectionGeneration.createConnectTeamsLands(RMSExprs.dict(
					
					spoon.getAgeVersion().terrainCost().addArguments("PALM_DESERT", 3),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS", 1),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS2", 1),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS3", 1),
					spoon.getAgeVersion().terrainCost().addArguments("ROAD", 0),
					
					spoon.getAgeVersion().terrainSize().addArguments("PALM_DESERT", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("PALM_GRASS", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("PALM_GRASS3", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("DESERT", 5, 0)
			));
				
			root.constant("WELL", 1567);
		
			var objectGeneration = root.objectsGeneration();
			
			objectGeneration
			.startRandom()
				.percentChance(100, spoon.getAgeVersion().define("HI_VULTURE"))
			.endRandom()
			;
			
			objectGeneration
			.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				.includeDrs("GeneratingObjects.inc")
			;
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
	@Test
	public void team_arena_08() throws AbstractRMSException {
		var outputFile = Path.of(Thread.currentThread().getStackTrace()[1].getMethodName() + ".rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition())
				.setOutputFile(outputFile)
				.setCodeAsWarning(RMSErrorCode.from(12))
				.setConstNotFoundInSymbolTableAction(ConstNotFoundInSymbolTableActionEnum.ASSUME_0)
				;
		
		spoon.generate((root, log, aoe) -> {
			log.info("add file info");
			
			root.addFileInfo(
					"Koldar",
					"Example RMS file",
					Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
					new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
					new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));
			root.define("PH_AUTUMN");
			root.includeDrs("F_seasons.inc");
			root.playerSetup()
				.groupedByTeam()
				.aiInfoMapType(
						RMSExprs.constVal("ARENA"), 
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("FALSE"),
						RMSExprs.constVal("TRUE")
				)
				.comment("#include_drs tghebr_setup.inc")
			;
			
			var landGeneration = root.landGeneration();
			
			landGeneration
				.comment("fill the entire map with this terrain. After the inclusi9on, TERRAIN_CHOSEN is populated")
				.includeDrs("get-random-base-land-terrain.inc")
				.baseTerrain(RMSExprs.constVal("TERRAIN_CHOSEN"))
			;
			
			landGeneration
				.comment("Setup player positions")
				.createPlayerLands(RMSExprs.dict(
						aoe.terrainType().addArgument("GRASS"),
						aoe.baseSize().addArgument(19),
						aoe.landPercent().addArgument(5),
						aoe.clumpingFactor().addArgument(3),
						aoe.borderFuzziness().addArgument(15),
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance().addArgument(0),
						aoe.circlePlacement(),
						aoe.circleRadius().addArgument(38)
				))
			;
			
			landGeneration.comment("create centered arena");
			
			//20x20 -> 40x40
			//20x40 -> 20x40
			//40x20 -> 40x20
			//40x40 -> 20x20
			var borders = new Point2D[][] { 
					new Point2D[] { new Point2D(20, 20), new Point2D(40, 40), },
					new Point2D[] { new Point2D(20, 40), new Point2D(20, 40), },
					new Point2D[] { new Point2D(40, 20), new Point2D(40, 20), },
					new Point2D[] { new Point2D(40, 40), new Point2D(20, 20), },
			};
			
			for (var border : borders) {
				var topleft = border[0];
				var bottomright = border[1];
				landGeneration.createLand(RMSExprs.dict(
						spoon.getAgeVersion().terrainType().addArgument("GRASS3"),
						spoon.getAgeVersion().baseSize().addArgument(20),
						spoon.getAgeVersion().landPercent().addArgument(30),
						spoon.getAgeVersion().clumpingFactor().addArgument(3),
						spoon.getAgeVersion().borderFuzziness().addArgument(8),
						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
						spoon.getAgeVersion().topBorder().addArgument(topleft.getX()),
						spoon.getAgeVersion().leftBorder().addArgument(topleft.getY()),
						spoon.getAgeVersion().rightBorder().addArgument(bottomright.getX()),
						spoon.getAgeVersion().bottomBorder().addArgument(bottomright.getY()),
						spoon.getAgeVersion().landId().addArgument(9),
						spoon.getAgeVersion().zone().addArgument(9)
				));
			}
			
			root.terrainGeneration()
				.createTerrain(RMSExprs.constVal("PALM_DESERT"), RMSExprs.dict(
						spoon.getAgeVersion().baseTerrain().addArgument("DESERT"),
						spoon.getAgeVersion().spacingToOtherTerrainTypes().addArgument(0),
						spoon.getAgeVersion().landPercent().addArgument(100),
						spoon.getAgeVersion().numberOfClumps().addArgument(100),
						spoon.getAgeVersion().setAvoidPlayerStartAreas(),
						spoon.getAgeVersion().setScaleByGroups(),
						spoon.getAgeVersion().clumpingFactor().addArgument(15),
						spoon.getAgeVersion().heightLimits().addArgument(0).addArgument(7)
				));
			
			var connectionGeneration = root.connectionGeneration();
			
			connectionGeneration.createConnectTeamsLands(RMSExprs.dict(
					
					spoon.getAgeVersion().terrainCost().addArguments("PALM_DESERT", 3),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS", 1),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS2", 1),
					spoon.getAgeVersion().terrainCost().addArguments("GRASS3", 1),
					spoon.getAgeVersion().terrainCost().addArguments("ROAD", 0),
					
					spoon.getAgeVersion().terrainSize().addArguments("PALM_DESERT", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("PALM_GRASS", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("PALM_GRASS3", 5, 0),
					spoon.getAgeVersion().terrainSize().addArguments("DESERT", 5, 0)
			));
				
			root.constant("WELL", 1567);
		
			var objectGeneration = root.objectsGeneration();
			
			objectGeneration
			.startRandom()
				.percentChance(100, spoon.getAgeVersion().define("HI_VULTURE"))
			.endRandom()
			;
			
			var ifNode = IfBlockBuilder.instance(aoe)
				.condition("HI_VULTURE")
				.then("VULTURE")
				.elseBlock("HAWK")
				.endIf()
			;
			
			objectGeneration.createObject(ifNode, (d) -> {
				d.addStatements(
						aoe.numberOfObjects(4),
						aoe.setGaiaObjectOnly(),
						aoe.numberOfGroups().addArgument(RMSExprs.rndVal(5, 10))
				);
			});
			
//			objectGeneration
//			.beginIf()
//			.condition(RMSExprs.defineVal("HI_VULTURE"))
//			.then(spoon.getAgeVersion().createObject().addArgument("VULTURE"))
//			.elseBlock(spoon.getAgeVersion().createObject().addArgument("HAWK"))
//			.endIf()
//			;
//			objectGeneration.dict(
//					aoe.numberOfObjects().addArgument(4),
//					aoe.setGaiaObjectOnly(),
//					aoe.setScaleBySize(),
//					aoe.numberOfGroups().addArgument(RMSExprs.rndVal(5, 10))
//			);
			
			objectGeneration
			.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
				.includeDrs("GeneratingObjects.inc")
			;
			
			log.info("final script");
			return root;
		});
		
		Assert.assertTrue(outputFile.toFile().exists());
	}
	
}

