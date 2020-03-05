package com.crazycoffee.Task;

import com.crazycoffee.data.Rocks;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Mine extends Task {



    private static final String Mine_Action = "Mine";


    @Override
    public boolean validate() {
        return Players.getLocal().getAnimation() == -1 && !Inventory.isFull();
    }

    @Override
    public int execute() {
        SceneObject rockToMine = SceneObjects.getNearest(1);
        if ((rockToMine != null) && !Inventory.isFull()) {
            rockToMine.interact(Mine_Action);
            Log.info("Interacting with Rocks!");

            if (Time.sleepUntil(() -> (Players.getLocal().getAnimation() == 625), Random.low(5000, 8000))){
                Log.info("Interaction with Rocks successful, currently mining!");
            }
        }

        return Random.high(1000, 5000);
    }
}
