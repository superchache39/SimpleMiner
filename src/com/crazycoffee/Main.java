package com.crazycoffee;

import com.crazycoffee.Task.Drop;
import com.crazycoffee.Task.Mine;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import java.awt.*;

@ScriptMeta(developer = "CrazyCoffee", name = "SimpleMiner", desc = "Gets some Ores!")
public class Main extends TaskScript implements RenderListener {

    //Running
    public static boolean isRunning = true;

    //Task Array
    private static final Task[] Tasks = {new Mine(), new Drop() };

    //Powermine
    public static boolean powerMine = true;

    //Stats
    public int startXP;
    public int startLevel;
    private long startTime;
    long runTime;

    @Override
    public void onStart() {

        if (isRunning){
            Log.fine("SimpleMiner has started");
            submit(Tasks);
        }
        else {
            Log.severe("Fatal Error, stopping script!");
            setStopping(true);
        }

        //Stats Cont.
        startLevel = Skills.getLevel(Skill.MINING);
        startTime = System.currentTimeMillis();
        startXP = Skills.getExperience(Skill.MINING);
    }

    @Override
    public void onStop() {
        Log.severe("SimpleMiner has stopped!");
        setStopping(true);
    }

    //Time Format
    public final  String formatTime(final long ms) {
        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60;
        m %= 60;
        h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }


    //Paint Color and Text.
    private final Color color1 = new Color(46, 46, 46, 222);
    private final Color color2 = new Color(254, 254, 254);

    private final Font font1 = new Font("Consolas", 0, 12);
    private final Font font2 = new Font("Consolas", 0, 11);

    @Override
    public void notify(RenderEvent renderEvent) {

        Graphics g = renderEvent.getSource();

        //Additional stat variables.
        int currentXp = Skills.getExperience(Skill.MINING);
        int currentLvl = Skills.getLevel(Skill.MINING);
        runTime = System.currentTimeMillis() - startTime;
        int xpGained = currentXp - startXP;
        int gainedLvl = currentLvl - startLevel;

        g.setColor(color1);
        g.fillRect(3, 304, 516, 36);
        g.setFont(font1);
        g.setColor(color2);
        g.drawString("Xp/Hr: " + xpGained +"/"+ xpGained * (3600000 / runTime), 11, 329);
        g.drawString("Lvls: " + currentLvl +"/"+ "(" + "+"+ gainedLvl + ")", 150, 329);
        g.drawString("Runtime: " + formatTime(runTime), 270, 329);
        g.setFont(font2);
        g.setColor(Color.green);
        g.drawString("SimpleMiner", 420, 329);

    }
}
