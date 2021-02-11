package com.thekoldar.aoe_rms_spoon;

import java.nio.file.Path;

import org.eclipse.collections.api.factory.Lists;
import org.junit.Assert;
import org.junit.Test;

import com.github.zafarkhaja.semver.Version;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.SeasonProvidedConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.de.DefinitiveEdition;
import com.thekoldar.aoe_rms_spoon.age_versions.de.DefinitiveEditionImportantFiles;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.framework.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.framework.models.Point2D;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.ConstNotFoundInSymbolTableActionEnum;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.CreateRandomTerrains;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.Libraries;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.Lands;
import com.thekoldar.aoe_rms_spoon.framework.usefulscripts.Switches;

public class TestArena {
	
	public static final int CENTRAL_ZONE_ID = 9;

	@Test
	public void teamArena() throws AbstractRMSException {

		//var outputFile = Path.of("..", "..", "..", "..", "Games", "Age of Empires 2 DE", "76561198256653370", "mods", "local", "ya-team-arena", "resources", "_common", "random-map-scripts", "ya-team-arena.rms");
		//var outputFile = Path.of("team-arena.rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition());

		//configure spoon
		spoon
			//.setOutputFile(outputFile)
			.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
			.setCodeAsWarning(RMSErrorCode.from(30))
			.deleteOutputFilesIfPossible()
			.setOutputFileInLocalMods("ya-team-arena")
			.generateASTTreeImage("ast.png")
			.generateTerrainCSV("terrains.csv")
			.setConstNotFoundInSymbolTableAction(ConstNotFoundInSymbolTableActionEnum.ASSUME_0)
			.setCodeAsWarning(RMSErrorCode.from(12))
			.setCodeAsWarning(RMSErrorCode.from(20))
			.setCodeAsWarning(RMSErrorCode.DUPLICATE_TYPE)
			//.disableSemanticAnalysis()
			//.outputComments(false)
		;
		
		//create script

		spoon.generate((root, log, aoe) -> {
			
			root.addFileInfo("Koldar", "Example RMS file", Version.forIntegers(1, 2, 0),
					Lists.immutable.of(
							new ChangeLogEntry(Version.forIntegers(1, 0), "initial commit"),
							new ChangeLogEntry(Version.forIntegers(1, 1), "fix small typo")
			));

			root
				//add bool support
				.addStatement(Libraries.stdBool(aoe))
				//fetch a generic walkable buildable natural resources terrain
				.addStatement(CreateRandomTerrains.getRandomWalkableBuildableNaturalResourcesTerrain(aoe, "CUSTOM_BASE_TERRAIN"))
				//fetch a generic walkable buildable natural resources terrain
				.addStatement(CreateRandomTerrains.getRandomWalkableBuildableNaturalResourcesTerrain(aoe, "CUSTOM_ARENA_TERRAIN"))
				//fetch a generic walkable buildable natural resources terrain
				.addStatement(CreateRandomTerrains.getRandomWalkableBuildableNaturalResourcesTerrain(aoe, "CUSTOM_BASE_PLAYER_TERRAIN"))
				//fetch a generic forest
				.addStatement(CreateRandomTerrains.getRandomForestTerrain(aoe, "CUSTOM_BASE_FOREST"))
				//randomly choose a season
				.addStatement(CreateRandomTerrains.defineRandomSeason(aoe))
			;

			//add season
			root
				.includeDrs("F_seasons.inc")
				.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
			;
			// F_seasons.inc define some consts. Use this method to notify the semantci analysis abnout them (atm spoon is not able to parse RMS files)
			spoon.informSemanticAnalysisThatConstIsDefined(SeasonProvidedConstants.all());
			

			root.comment("By definibg them we can crete_player_lands safely");

			var playerSetup = root.playerSetup();

			playerSetup.groupedByTeam()
				.aiInfoMapType(RMSExprs.constVal("BLACK_FOREST"), RMSExprs.constVal("FALSE"), RMSExprs.constVal("FALSE"), RMSExprs.constVal("TRUE"))
				//.includeDrs("thebr_setup.inc")
			;

			var landGeneration = root.landGeneration();

			landGeneration
			.comment("fill the entire map with this terrain.")
			.baseTerrain(RMSExprs.constVal("CUSTOM_BASE_TERRAIN"));

			// setting DLC_RAINFOREST with a terrain_type will cerate the forest as well!
			landGeneration
				.comment("Setup player positions")
				.createPlayerLands(d -> {
					d.addStatements(
						aoe.terrainType("CUSTOM_BASE_PLAYER_TERRAIN"),
						
						//generate base_size depending on the map size
						Switches.doDependingOnMapSize(aoe, ms -> aoe.baseSize(ms.getTileFromPercentage(9))),
						//generate circle_radius depending on the map size
						Switches.doDependingOnMapSize(aoe, ms -> aoe.circleRadius(ms.getTileFromPercentage(15))),
						
						aoe.landPercent(5),
						aoe.clumpingFactor(3), 
						aoe.borderFuzziness(15), 
						aoe.setZoneByTeam(),
						aoe.otherZoneAvoidanceDistance(0),
						aoe.circlePlacement()
					);
				});

			landGeneration
				.comment("create centered arena")
				.createLand(d -> {
					d.addStatements(
							Lands.createOctagonalLand(aoe, new Point2D(50, 50), 30, 30, 30, 30),
							aoe.terrainType("CUSTOM_ARENA_TERRAIN"),
							aoe.clumpingFactor(10),
							aoe.borderFuzziness(8),
							aoe.otherZoneAvoidanceDistance(0),
							aoe.landId(CENTRAL_ZONE_ID),
							aoe.zone(CENTRAL_ZONE_ID)
					);
				})
				.createLand(d -> {
					d.addStatements(
						Lands.createOctagonalLand(aoe, new Point2D(50, 50), 5, 5, 5, 5),
						aoe.terrainType(SeasonProvidedConstants.WATER),
						aoe.landId(CENTRAL_ZONE_ID),
						aoe.zone(CENTRAL_ZONE_ID)
					);
				})
			;
			
			var elevationGeneration = root.elevationGeneration();
			
			elevationGeneration.createElevation(3, d -> { d.addStatements(
				aoe.baseTerrain("CUSTOM_ARENA_TERRAIN"), //TODO if the baseTerrain is not present on the map, hills will not be generated!
				aoe.setScaleBySize(),
				aoe.numberOfTiles(100),
				aoe.enableBalancedElevation(),
				aoe.spacing(3)
			);});
			
			
			var cliffGeneration = root.cliffGeneration();
					
			cliffGeneration
				.minNumberOfCliffs(3)
				.maxNumberOfCliffs(5)
				.minLengthOfCliff(5)
				.maxLengthOfCliff(10)
				;
			
			var terrainGeneration = root.terrainGeneration();
			
			terrainGeneration
				.comment("Creating the forest sorrounding the arena")
				.createTerrain("CUSTOM_BASE_FOREST", d -> {
					d.addStatements(
					aoe.baseTerrain("CUSTOM_BASE_TERRAIN"),
					aoe.spacingToOtherTerrainTypes(0),
					aoe.landPercent(100));
				})
				.comment("Create mini forest patches inside the arena")
				.createTerrain("CUSTOM_BASE_FOREST", d -> {
					d.addStatements(
							aoe.baseTerrain("CUSTOM_ARENA_TERRAIN"),
							aoe.landPercent(1),
							aoe.spacingToOtherTerrainTypes(0)
					);
				})
			;
			
			var connectionGeneration = root.connectionGeneration();
			
			connectionGeneration
				.createConnectTeamsLands(
					aoe.defaultTerrainReplacement(SeasonProvidedConstants.ROAD),
					aoe.replaceTerrain("CUSTOM_BASE_PLAYER_TERRAIN", SeasonProvidedConstants.ROAD),
					aoe.replaceTerrain("CUSTOM_ARENA_TERRAIN", SeasonProvidedConstants.ROAD),
					
					aoe.terrainCost("CUSTOM_BASE_PLAYER_TERRAIN", 2),
					aoe.terrainCost("CUSTOM_ARENA_TERRAIN", 1),
					aoe.terrainCost(SeasonProvidedConstants.ROAD, 0),
					
					aoe.terrainSize("CUSTOM_BASE_PLAYER_TERRAIN", 5, 1)
			);
//			
//			root.constant("WELL", 1567);

			var objectGeneration = root.objectsGeneration();

			objectGeneration
					.includeDrs(((DefinitiveEditionImportantFiles) aoe.getImportantFiles()).generatingObjects())
					//create hawk
					.createObject("HAWK", (d) -> {
						d.addStatements(
								aoe.numberOfObjects(4),
								aoe.setGaiaObjectOnly(),
								aoe.setScalingToMapSize()
						);
					})
			;

//			objectGeneration
//			.startRandom()
//				.percentChance(RMSExprs.intVal(0), spoon.getAgeVersion().define("HI_VULTURE"))
//			.endRandom()
//			;
//			
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

			log.info("final script");
			return root;
		});

		Assert.assertTrue(spoon.getOutputFile().toFile().exists());
	}
}