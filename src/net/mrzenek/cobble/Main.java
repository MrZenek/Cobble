package net.mrzenek.cobble;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

	public static PluginDescriptionFile pdf;
	public Commands cmd = new Commands();
	public BreakListener bl = new BreakListener();

	public void onEnable() {
		pdf = this.getDescription();

		System.out.println("[" +  pdf.getName() + " " + pdf.getVersion() + "]" + "Plugins is ON");

		cmd.canDrop = true;
		cmd.drop = new ArrayList<Player>();
		this.getCommand(cmd.name).setExecutor(cmd);

		bl.init(this);
		getServer().getPluginManager().registerEvents(bl, this);
	}

	public void onDisable() {
		System.out.println("[" +  pdf.getName() + " " + pdf.getVersion() + "]" + "Plugins is OFF");
	}

}
