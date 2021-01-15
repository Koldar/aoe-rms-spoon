package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;

public abstract class AbstractObjectsGeneration extends AbstractRMSSection implements AddComment, AddDirectives{


	public AbstractObjectsGeneration() {
		super(RMSNodeType.OBJECTS_GENERATION);
	}

	@Override
	public String getSectionName() {
		return "OBJECTS_GENERATION";
	}

}
