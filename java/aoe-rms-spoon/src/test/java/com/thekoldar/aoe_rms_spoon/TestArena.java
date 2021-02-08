package com.thekoldar.aoe_rms_spoon;

import java.nio.file.Path;

import org.eclipse.collections.api.factory.Lists;
import org.junit.Assert;
import org.junit.Test;

import com.github.zafarkhaja.semver.Version;
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

		var outputFile = Path.of("..", "..", "..", "..", "Games", "Age of Empires 2 DE", "76561198256653370", "mods",
				"local", "ya-team-arena", "resources", "_common", "random-map-scripts", "ya-team-arena.rms");
		var spoon = SpoonFramework.instance(new DefinitiveEdition());

		//configure spoon
		spoon
			.setOutputFile(outputFile)
			.setCodeAsWarning(RMSErrorCode.WRONG_CHILDREN_NUMBER)
			.setCodeAsError(RMSErrorCode.WRONG_CHILDREN_NUMBER)
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
			;
			
			var terrainGeneration = root.terrainGeneration();
			
			terrainGeneration.createTerrain("CUSTOM_BASE_FOREST", d -> {
				d.addStatements(
					aoe.baseTerrain("CUSTOM_BASE_TERRAIN"),
					aoe.spacingToOtherTerrainTypes(0),
					aoe.landPercent(100)
					
				);
			});
			
//			var param = RMSExprs.dict();
//			param = UsefulScripts.createOctagonalLand(param, aoe, 30, 30, 30, 30);
//			landGeneration.createLand(param.addStatements(
//					spoon.getAgeVersion().terrainType().addArgument("JUNGLE"),
//					spoon.getAgeVersion().clumpingFactor(10),
//					spoon.getAgeVersion().borderFuzziness(8),
//					spoon.getAgeVersion().otherZoneAvoidanceDistance(0), 
//					spoon.getAgeVersion().landId(CENTRAL_ZONE_ID),
//					spoon.getAgeVersion().zone(CENTRAL_ZONE_ID))
//			);
					// spoon.getAgeVersion().baseSize(20),
//					spoon.getAgeVersion().landPercent(100), 
//					 
//					spoon.getAgeVersion().topBorder(20),
//					spoon.getAgeVersion().leftBorder(20), 
//					spoon.getAgeVersion().rightBorder(20),
//					spoon.getAgeVersion().bottomBorder(20), 
//					spoon.getAgeVersion().borderFuzziness(8),
//					spoon.getAgeVersion().otherZoneAvoidanceDistance(0), 
//					spoon.getAgeVersion().landId(9),
//					spoon.getAgeVersion().zone(9)));

//			//20x20 -> 40x40
//			//20x40 -> 20x40
//			//40x20 -> 40x20
//			//40x40 -> 20x20
//			var borders = new Point2D[][] { 
//					new Point2D[] { new Point2D(20, 20), new Point2D(40, 40), },
//					new Point2D[] { new Point2D(20, 40), new Point2D(20, 40), },
//					new Point2D[] { new Point2D(40, 20), new Point2D(40, 20), },
//					new Point2D[] { new Point2D(40, 40), new Point2D(20, 20), },
//			};
//			
//			for (var border : borders) {
//				var topleft = border[0];
//				var bottomright = border[1];
//				landGeneration.createLand(RMSExprs.dict(
//						spoon.getAgeVersion().terrainType().addArgument("GRASS3"),
//						spoon.getAgeVersion().baseSize().addArgument(20),
//						spoon.getAgeVersion().landPercent().addArgument(30),
//						spoon.getAgeVersion().clumpingFactor().addArgument(3),
//						spoon.getAgeVersion().borderFuzziness().addArgument(8),
//						spoon.getAgeVersion().otherZoneAvoidanceDistance().addArgument(0),
//						spoon.getAgeVersion().topBorder().addArgument(topleft.getX()),
//						spoon.getAgeVersion().leftBorder().addArgument(topleft.getY()),
//						spoon.getAgeVersion().rightBorder().addArgument(bottomright.getX()),
//						spoon.getAgeVersion().topBorder().addArgument(bottomright.getY()),
//						spoon.getAgeVersion().landId().addArgument(9),
//						spoon.getAgeVersion().zone().addArgument(9)
//				));
//			}
//			
//			root.terrainGeneration()
//				.createTerrain("PALM_DESERT", 
//						aoe.baseTerrain("DESERT"),
//						aoe.spacingToOtherTerrainTypes(0),
//						aoe.landPercent(100),
//						aoe.numberOfClumps(100),
//						aoe.setAvoidPlayerStartAreas(),
//						aoe.setScaleByGroups(),
//						aoe.borderFuzziness(15),
//						aoe.clumpingFactor(15),
//						aoe.heightLimits(0, 7)
//				)
//				.createTerrain("DLC_BEACH2",
//						aoe.baseTerrain("GRASS3"),
//						aoe.spacingToOtherTerrainTypes(0),
//						aoe.landPercent(3),
//						aoe.numberOfClumps(30),
//						aoe.clumpingFactor(5),
//						aoe.borderFuzziness(3),
//						aoe.terrainMask(1),
//						aoe.heightLimits(0, 4)
//				)
//				.createTerrain("BAMBOO",
//						aoe.baseTerrain("GRASS3"),
//						aoe.spacingToOtherTerrainTypes(5),
//						aoe.landPercent(1),
//						aoe.numberOfClumps(2),
//						aoe.setAvoidPlayerStartAreas(),
//						aoe.setScaleByGroups(),
//						aoe.borderFuzziness(15),
//						aoe.clumpingFactor(12),
//						aoe.heightLimits(0, 0)
//				)
//				.createTerrain("GRASS3",
//						aoe.baseTerrain("GRASS3"),
//						aoe.spacingToOtherTerrainTypes(3),
//						aoe.landPercent(20),
//						aoe.numberOfClumps(12),
//						aoe.setAvoidPlayerStartAreas(),
//						aoe.setScaleByGroups(),
//						aoe.clumpingFactor(5),
//						aoe.heightLimits(0, 0)
//				)
//				.createTerrain("GRASS3",
//						aoe.baseTerrain("GRASS3"),
//						aoe.spacingToOtherTerrainTypes(3),
//						aoe.landPercent(20),
//						aoe.numberOfClumps(50),
//						aoe.setAvoidPlayerStartAreas(),
//						aoe.setScaleByGroups(),
//						aoe.clumpingFactor(4),
//						aoe.heightLimits(3, 7)
//				)
//				.createTerrain("DLC_BEACH2",
//						aoe.baseTerrain("GRASS3"),
//						aoe.spacingToOtherTerrainTypes(0),
//						aoe.landPercent(6),
//						aoe.numberOfClumps(50),
//						aoe.clumpingFactor(5),
//						aoe.borderFuzziness(1),
//						aoe.terrainMask(1),
//						aoe.heightLimits(0, 0)
//				)
//				;
//			
//			var connectionGeneration = root.connectionGeneration();
//			
//			connectionGeneration
//				.createConnectTeamsLands(
//					aoe.replaceTerrain("DESERT", "DESERT"),
//					aoe.replaceTerrain("PALM_DESERT", "GRASS"),
//					aoe.replaceTerrain("GRASS", "GRASS"),
//					aoe.replaceTerrain("GRASS3", "GRASS3"),
//					
//					aoe.terrainCost("PALM_DESERT", 3),
//					aoe.terrainCost("GRASS", 1),
//					aoe.terrainCost("GRASS2", 1),
//					aoe.terrainCost("GRASS3", 1),
//					aoe.terrainCost("ROAD", 0),
//					
//					aoe.terrainSize("PALM_DESERT", 5, 0),
//					aoe.terrainSize("GRASS", 5, 0),
//					aoe.terrainSize("GRASS3", 5, 0),
//					aoe.terrainSize("DESERT", 5, 0)
//			);
//			
//			root.constant("WELL", 1567);

			var objectGeneration = root.objectsGeneration();

			objectGeneration
					.includeDrs(((DefinitiveEditionImportantFiles) aoe.getImportantFiles()).generatingObjects());
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

		Assert.assertTrue(outputFile.toFile().exists());
	}
}