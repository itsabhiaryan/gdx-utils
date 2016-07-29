package com.nayragames.gdxutils.ai.formation;

import com.badlogic.gdx.ai.fma.FormationMember;
import com.badlogic.gdx.ai.fma.SoftRoleSlotAssignmentStrategy.SlotCostProvider;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-07-2016
 */

public class DistanceSlotCostProvider implements SlotCostProvider<Vector2> {
	
	@Override
	public float getCost(FormationMember<Vector2> member, int slotNumber) {
		/*UnitComponent unitComp = (UnitComponent) member;
		SquadComponent squadComp = Components.SQUAD.get(unitComp.getSquad());
		
		Vector2 targetPosition = squadComp.enemyPower.getSlotAssignmentAt(slotNumber).member.getTargetLocation().getPosition();
*/
		// The cost is the square distance between current position and target position
		//return unitComp.getBody().getPosition().dst2(targetPosition);
		return .1f;
	}
}
