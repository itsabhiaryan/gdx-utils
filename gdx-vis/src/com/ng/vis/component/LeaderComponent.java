package com.ng.vis.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.ai.fma.Formation;
import com.badlogic.gdx.ai.fma.FormationPattern;
import com.badlogic.gdx.ai.fma.patterns.DefensiveCircleFormationPattern;
import com.badlogic.gdx.ai.fma.patterns.OffensiveCircleFormationPattern;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ng.gdxutils.ai.Scene2dLocation;
import com.ng.gdxutils.ai.formation.*;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 */

public class LeaderComponent extends Component {

	public enum FormationType {
		OFFENSIVE_CIRCLE(0),
		DEFENSIVE_CIRCLE(1),
		PLAYER_CHILD(2),
		SQUARE(3),
		WEDGE(4),
		LINE(5),
		COLUMN(6),
		DRAGON(7),
		;

		public int defaultValue;
		FormationType(int defaultValue){
			this.defaultValue=defaultValue;
		}
	}

	public boolean isUpdateSlot =true;
	public Array<Entity> memberEntity=new Array<Entity>();
	public Array<Steerable<Vector2>> memberSteerable=new Array<Steerable<Vector2>>();

	public Formation<Vector2> formation;
	public Scene2dLocation location=new Scene2dLocation();
	private Steerable<Vector2> steerable;
	
	public LeaderComponent(Steerable<Vector2> steerable){
		this(steerable, FormationType.DEFENSIVE_CIRCLE,.2f);
	}

	public LeaderComponent(Steerable<Vector2> steerable,FormationType type,float memberRadius){
		this.steerable=steerable;

		FormationPattern<Vector2> formationPattern=null;
		//FreeSlotAssignmentStrategy<Vector2> freeSlotAssignmentStrategy=new FreeSlotAssignmentStrategy<Vector2>();
		//OffensiveCircleFormationPattern<Vector2> pattern=new OffensiveCircleFormationPattern<Vector2>(.2f);
		//DefensiveCircleFormationPattern<Vector2> pattern2=new DefensiveCircleFormationPattern<Vector2>(.2f);
		//SquareFormationPattern squareFormationPattern=new SquareFormationPattern(.1f);
		//WedgeFormationPattern wedgeFormationPattern=new WedgeFormationPattern(.1f);
		if(type== FormationType.OFFENSIVE_CIRCLE)
			formationPattern=new OffensiveCircleFormationPattern<Vector2>(memberRadius);
		else if(type== FormationType.DEFENSIVE_CIRCLE)
			formationPattern=new DefensiveCircleFormationPattern<Vector2>(memberRadius);
		else if(type== FormationType.SQUARE)
			formationPattern=new SquareFormationPattern(memberRadius);
		else if(type== FormationType.WEDGE)
			formationPattern=new WedgeFormationPattern(memberRadius);
		else if(type== FormationType.LINE)
			formationPattern=new LineFormationPattern(memberRadius);
		else if(type== FormationType.COLUMN)
			formationPattern=new ColumnFormationPattern(memberRadius);
		else if(type== FormationType.PLAYER_CHILD)
			formationPattern=new PlayerChildFormationPattern(memberRadius);
		else if(type== FormationType.DRAGON)
			formationPattern=new DragonFormationPattern(memberRadius);

		formation=new Formation<Vector2>(steerable, formationPattern);
	}

	public LeaderComponent(Steerable<Vector2> steerable,FormationType type,float memberRadius,float center){
		this.steerable=steerable;

		FormationPattern<Vector2> formationPattern=null;
		//FreeSlotAssignmentStrategy<Vector2> freeSlotAssignmentStrategy=new FreeSlotAssignmentStrategy<Vector2>();
		//OffensiveCircleFormationPattern<Vector2> pattern=new OffensiveCircleFormationPattern<Vector2>(.2f);
		//DefensiveCircleFormationPattern<Vector2> pattern2=new DefensiveCircleFormationPattern<Vector2>(.2f);
		//SquareFormationPattern squareFormationPattern=new SquareFormationPattern(.1f);
		//WedgeFormationPattern wedgeFormationPattern=new WedgeFormationPattern(.1f);
		if(type== FormationType.OFFENSIVE_CIRCLE)
			formationPattern=new OffensiveCircleFormationPattern<Vector2>(memberRadius);
		else if(type== FormationType.DEFENSIVE_CIRCLE)
			formationPattern=new DefensiveCircleFormationPattern<Vector2>(memberRadius);
		else if(type== FormationType.SQUARE)
			formationPattern=new SquareFormationPattern(memberRadius);
		else if(type== FormationType.WEDGE)
			formationPattern=new WedgeFormationPattern(memberRadius);
		else if(type== FormationType.LINE)
			formationPattern=new LineFormationPattern(memberRadius);
		else if(type== FormationType.COLUMN)
			formationPattern=new ColumnFormationPattern(memberRadius);
		else if(type== FormationType.PLAYER_CHILD)
			formationPattern=new PlayerChildFormationPattern(memberRadius);
		else if(type== FormationType.DRAGON)
			formationPattern=new DragonFormationPattern(memberRadius,center);

		formation=new Formation<Vector2>(steerable, formationPattern);
	}


	public void addMember(Entity entity){
		
		//Gdx.app.log("TeamComponent","addMember");
		memberEntity.add(entity);
		memberSteerable.add(entity.getComponent(SteerableComponent.class));
		formation.addMember(entity.getComponent(FormationMemberComponent.class));
	}
	
	public void removeMember(Entity entity){
		memberEntity.removeValue(entity,true);
		memberSteerable.removeValue(entity.getComponent(SteerableComponent.class),true);
		formation.removeMember(entity.getComponent(FormationMemberComponent.class));
	}
	
	public void setFormationPattern(FormationPattern<Vector2> pattern){
		
		formation.changePattern(pattern);
	}
	
	public Vector2 getPosition(){
		return steerable.getPosition();
	}
	
	public void setTarget(Vector2 target){
	      location.getPosition().set(target);
	}
}
