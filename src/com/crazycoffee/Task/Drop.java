package com.crazycoffee.Task;

import com.crazycoffee.Main;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Drop extends Task {

    private static final String Drop_Action = "Drop";

    @Override
    public boolean validate() {
        return Inventory.isFull() && Main.powerMine == true;
    }

    @Override
    public int execute() {
        if (Inventory.isFull()) {
            for (Item ore : Inventory.getItems(item -> item.getName().equalsIgnoreCase("Copper Ore"))) {
                ore.interact(Drop_Action);
                Log.info("Dropping ores!");
                Time.sleep(400, 900);
            }
        }

        return Random.low(400, 500);
    }
}
