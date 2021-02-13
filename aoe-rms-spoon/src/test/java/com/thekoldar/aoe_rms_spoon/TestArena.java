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
			
			cliffGeneration.addStatement(Switches.doDependingOnMapSize(aoe, (mp, n) -> {
					n.addStatements(
						aoe.minNumberOfCliffs(1 * mp.getTilesPer100x100()),
						aoe.maxNumberOfCliffs(3 * mp.getTilesPer100x100()),
						aoe.minLengthOfCliff(5 * mp.getTilesPer100x100()),
						aoe.maxLengthOfCliff(7 * mp.getTilesPer100x100())
					);
					return n;
			}));
			
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
					
					aoe.terrainSize("CUSTOM_BASE_PLAYER_TERRAIN", 3, 1)
			);
//			
//			root.constant("WELL", 1567);

			var objectGeneration = root.objectsGeneration();

			objectGeneration
					.comment("Allows to place the players in the correct position")
					.defines("GNR_NORMALTC", "GNR_STARTVILLS", "GNR_CLASSICSCOUT")
					.comment("Put a correct number fo relics on the map (5)")
					.defines("GNR_GIVERELICS", "GNR_RELICMODERN_MEDIUM")
					//.defines("GNR_RESCLOSE")
					.comment("Alter herdables, gold and stone position by putting them close")
					.define("GNR_RESCLOSE")
					.comment("Request that gold should be generated")
					.define("GNR_STARTINGGOLD")
					.comment("give to all players 7 patches or near gold, 4 to medium gold and finally 4 patches of far gold")
					.define("GNR_STARTGOLD744CL")
					.comment("Request that stone should be generated")
					.define("GNR_STARTINGSTONE")
					.comment("Request that all player should have 5 patches of main stone and 4 patches of home stone")
					.define("GNR_STARTSTONE54CL")
					.comment("Request that berries needs to be generated. If GNR_KERIK7NUMBER is also set, we generate 7 patches of berries, 6 otherwise. GNR_HIDEOUTGS_DISTANCE and GNR_RESSUPERCLOSE control distance")
					.define("GNR_6KER")
					.comment("Given herdables of type A. Groups of heardables A are controlled by GNR_HERD4CLASSIC_A, GNR_HERD3_A, GNR_HERD2_A, GNR_HERD1_A, GNR_HERD6_A")
					.defines("GNR_GIVEHERD_A", "GNR_HERD4CLASSIC_A")
					.comment("Given herdables of type B. Groups of heardables B are controller by GNR_HERD22CLASSIC_B, GNR_HERD33_B, GNR_HERD23_B, GNR_HERD4_B, GNR_HERD3_B, GNR_HERD2_B, GNR_HERD1_B. Distance affected by GNR_RESCLOSE, GNR_RESSUPERCLOSE, GNR_HERDBDIST_CL, GNR_HERDBDIST_SCL, GNR_HERDBDIST_FAR")
					.defines("GNR_GIVEHERD_B", "GNR_HERD22CLASSIC_B")
					.comment("Generate deers or hunt-like. Number affected by GNR_HUNT3OR4CLASSIC, GNR_HUNT8MODERN, GNR_HUNT6MODERN, GNR_HUNT5MODERN, GNR_HUNT4MODERN, GNR_HUNT3MODERN, GNR_HUNT2MODERN. Group number affected by GNR_HUNTGROUPS_2. Ditances affected by GNR_RESSUPERCLOSE, GNR_RESCLOSE, GNR_HUNTDIST_CL, GNR_HUNTDIST_SCL, GNR_HUNTDIST_HIDEOUT, GNR_HUNTDIST_FAR")
					.defines("GNR_GIVEHUNT", "GNR_HUNT3OR4CLASSIC")
					.comment("Add boar-like animals. Number affected by GNR_CLASSICLURES, GNR_CLASSICLURES_ONE, GNR_CLASSICLURES_TWO, GNR_CLASSICLURES_THREE. Distance Affected by GNR_LUREDIST_CL, GNR_LUREDIST_SCL, GNR_LUREDIST_FAR, GNR_LUREDIST_OPENDIST, GNR_RESSUPERCLOSE, GNR_RESCLOSE.")
					.defines("GNR_GIVELURES", "GNR_CLASSICLURES")
					.comment("If GNR_DOUBLEPRED is set, we create a predator. If GNR_CLASSICPRED is set we create another predator")
					.defines("GNR_CLASSICPRED")
					.comment("generates brd. Create 2 bird fo type A and 2 birds of type B. Otherwise if GNR_ABIRDS_ONLY only birds A are set")
					.define("GNR_NORMALBIRDS")
					.comment("Create another 2 predators A")
					.define("GNR_ADDITIONALPRED")
					.includeDrs(((DefinitiveEditionImportantFiles) aoe.getImportantFiles()).generatingObjects())
					.comment("Create gold in the middle of the map")
					.createObject("GOLD", d -> {
						d.addStatements(
							aoe.numberOfObjects(4),
							aoe.numberOfGroups(1),
							aoe.setGaiaObjectOnly(),
							aoe.setScalingToMapSize(),
							aoe.minDistanceToPlayers(33),
							aoe.minDistanceGroupPlacement(28)
						);
					})
					.comment("Crate Stone in the middle of the map")
					.createObject("STONE", d -> {
						d.addStatements(
							aoe.numberOfObjects(4),
							aoe.numberOfGroups(1),
							aoe.setGaiaObjectOnly(),
							aoe.setScalingToMapSize(),
							aoe.minDistanceToPlayers(42),
							aoe.minDistanceGroupPlacement(23)
						);
					})
					.comment("Create hawks")
					.createObject("HAWK", (d) -> {
						d.addStatements(
								aoe.numberOfObjects(3),
								aoe.setGaiaObjectOnly(),
								aoe.setScalingToMapSize()
						);
					})
					.comment("create a wooden sign and put it on the created road")
					.createObject("SIGN", d -> {
						d.addStatements(
							aoe.numberOfObjects(1),
							aoe.numberOfGroups(1),
							//aoe.setAvoidPlayerStartAreas(),
							aoe.setGaiaObjectOnly(), // necessary if you want to put down the sign
							aoe.setPlaceForEveryPlayer(),
							aoe.minDistanceToPlayers(12),
							aoe.maxDistanceToPlayers(16),
							aoe.terrainToPlaceOn(SeasonProvidedConstants.ROAD)
						);
					})
					.comment("create some rugs nearby each towncenter")
					.createObject("RUGS", d -> {
						d.addStatements(
							aoe.numberOfObjects(3),
							aoe.numberOfGroups(1),
							aoe.setGaiaObjectOnly(), // necessary if you want to put down the sign
							aoe.setPlaceForEveryPlayer(),
							aoe.minDistanceToPlayers(6),
							aoe.terrainToPlaceOn(SeasonProvidedConstants.ROAD)
						);
					})
					.comment("Create the walls")
					.createObject("WALL", d -> {
						d.addStatement(Switches.doDependingOnMapSize(aoe, (mp, n) -> {
							n.addStatements(
									aoe.numberOfObjects(9999),
									aoe.setPlaceForEveryPlayer(),
									aoe.minDistanceToPlayers(19 * mp.getPercentageRelativeToTiny()),
									aoe.maxDistanceToPlayers(26 * mp.getPercentageRelativeToTiny())
							);
							return n;
						}));
					})
					
					
					
//					.createObject("GOLD", (d) -> {
//						d.addStatements(
//							aoe.numberOfObjects(4),
//							aoe.numberOfGroups(1),
//							aoe.groupPlacementRadius(1),
//							aoe.setGaiaObjectOnly(),
//							aoe.setPlaceForEveryPlayer(),
//							aoe.minDistanceToPlayers(4),
//							aoe.maxDistanceToPlayers(8),
//							aoe.tempMinDistanceGroupPlacement(3)
//						);
//					})
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