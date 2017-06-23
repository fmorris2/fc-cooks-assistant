package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api2007.Player;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.EntityInteraction;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.framework.task.PredictableInteraction;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class CollectFlour extends Task implements PredictableInteraction
{

	@Override
	public boolean execute()
	{
		int plane = Player.getPosition().getPlane();
		
		if(plane == 2 || plane == 1)
			climbDown(plane);
		else if(plane == 0)
			if(new ClickObject("Empty", "Flour bin", 10).execute())
				Timing.waitCondition(FCConditions.inventoryContains("Pot of flour"), 5000);
		
		return true;		
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.COLLECT_FLOUR.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Collect flour";
	}
	
	private void climbDown(int plane)
	{
		if(getInteractable().execute())
			Timing.waitCondition(FCConditions.planeChanged(plane), 4000);
	}

	@Override
	public EntityInteraction getInteractable()
	{
		return new ClickObject("Climb-down", "Ladder", 10);
	}

}
