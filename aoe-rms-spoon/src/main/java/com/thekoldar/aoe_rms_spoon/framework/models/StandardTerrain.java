package com.thekoldar.aoe_rms_spoon.framework.models;

import java.awt.Color;
import java.nio.file.Path;

import com.thekoldar.aoe_rms_spoon.framework.ITerrain;

/**
 * Standard implementation of {@link ITerrain}
 * @author massi
 *
 */
public class StandardTerrain implements ITerrain {

	private Color color;
	private String rmsConstName;
	private int rmsConstValue;
	private boolean isForest;
	private boolean isBeach;
	private boolean isLandWalkable;
	private boolean isShipWalkable;
	private boolean isBuildable;
	private boolean isDockable;
	private boolean isWallable;
	private boolean canResourcesBePutOnIt;
	private Path textureFile;
	private String description;
	
	

	public StandardTerrain(Color color, Path textureFile, String rmsConstName, int rmsConstValue, boolean isForest, boolean isBeach,
			boolean isLandWalkable, boolean isShipWalkable, boolean isBuildable, boolean isDockable, boolean isWallable,
			boolean canResourcesBePutOnIt, String description) {
		super();
		this.color = color;
		this.textureFile = textureFile;
		this.rmsConstName = rmsConstName;
		this.rmsConstValue = rmsConstValue;
		this.isForest = isForest;
		this.isBeach = isBeach;
		this.isLandWalkable = isLandWalkable;
		this.isShipWalkable = isShipWalkable;
		this.isBuildable = isBuildable;
		this.isDockable = isDockable;
		this.isWallable = isWallable;
		this.canResourcesBePutOnIt = canResourcesBePutOnIt;
		this.description = description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public Path getTextureFile() {
		return this.textureFile;
	}
	

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public String getConstName() {
		return this.rmsConstName;
	}

	@Override
	public int getConstValue() {
		return this.rmsConstValue;
	}

	@Override
	public boolean isForest() {
		return this.isForest;
	}
	
	@Override
	public boolean isBeach() {
		return this.isBeach;
	}

	@Override
	public boolean isLandWalkable() {
		return this.isLandWalkable;
	}

	@Override
	public boolean isShipWalkable() {
		return this.isShipWalkable;
	}

	@Override
	public boolean isBuildable() {
		return this.isBuildable;
	}

	@Override
	public boolean isDockable() {
		return this.isDockable;
	}
	
	@Override
	public boolean isWallable() {
		return this.isWallable;
	}

	@Override
	public boolean canNaturalResourcesBePutOnIt() {
		return this.canResourcesBePutOnIt;
	}
	
	

}
