package com.thekoldar.aoe_rms_spoon.framework.usefulscripts;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.Point2D;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class Lands {

	/**
	 * Create an octagonal-like shape land. Convenience method. The area is create in the center of the map
	 * 
	 * @param aoe version of age of empires
	 * @param expr expression where to dump the instructions. It is likely to be a create_land
	 * @param widthTowardsNE percentage of the map s.t.the land created extends towards the north-east map border 
	 * @param widthTowardsNW percentage of the map s.t.the land created extends towards the north-west map border
	 * @param widthTowardsSE percentage of the map s.t.the land created extends towards  the south-east map border
	 * @param widthTowardsSW percentage of the map s.t.the land created extends towards  the south-west map border 
	 * @return
	 */
	public static IRMSNode createOctagonalLand(AbstractAoEVersion aoe, int widthTowardsNE, int widthTowardsNW, int widthTowardsSE, int widthTowardsSW) {
		return createOctagonalLand(aoe, new Point2D(50, 50), widthTowardsNE, widthTowardsNW, widthTowardsSE, widthTowardsSW);
	}
	
	/**
	 * Create an octagonal-like shape land. Convenience method
	 * 
	 * @param aoe version of age of empires
	 * @param center the center of the octagonal area. point (0,0) is the left corner while (100,100) is the right one. (0,100) is the bottom and (100,0) is the top corner
	 * @param widthTowardsNE percentage of the map s.t.the land created extends towards the north-east map border 
	 * @param widthTowardsNW percentage of the map s.t.the land created extends towards the north-west map border
	 * @param widthTowardsSE percentage of the map s.t.the land created extends towards  the south-east map border
	 * @param widthTowardsSW percentage of the map s.t.the land created extends towards  the south-west map border 
	 * @return dict representing an octagonal land. Can still be populated after the geneation
	 */
	public static IRMSNode createOctagonalLand(AbstractAoEVersion aoe, Point2D center, int widthTowardsNE, int widthTowardsNW, int widthTowardsSE, int widthTowardsSW) {
		
		var distanceFromNorthEast = 100 - center.getX() - widthTowardsSE;
		var distanceFromNorthWest = 100 - center.getY() - widthTowardsNW;
		var distanceFromSouthWest = 0 + center.getX() - widthTowardsSW;
		var distanceFromSouthEast = 0 + center.getY() - widthTowardsNW;
		
		distanceFromNorthEast = Utils.clamp0100(distanceFromNorthEast);
		distanceFromNorthWest = Utils.clamp0100(distanceFromNorthWest);
		distanceFromSouthWest = Utils.clamp0100(distanceFromSouthWest);
		distanceFromSouthEast = Utils.clamp0100(distanceFromSouthEast);
		
		return aoe.multiplexer()
			.addStatement(aoe.landPercent(100))
			.addStatement(aoe.topBorder(distanceFromNorthWest))
			.addStatement(aoe.rightBorder(distanceFromNorthEast))
			.addStatement(aoe.leftBorder(distanceFromSouthWest))
			.addStatement(aoe.bottomBorder(distanceFromSouthEast))
		;
	}
}
