package net.mrzenek.cobble;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

	public String cmd = "cobble";

	public static PluginDescriptionFile pdf;

	public void onEnable() {
		pdf = this.getDescription();

		System.out.println("[" +  pdf.getName() + " " + pdf.getVersion() + "]" + "Plugins is ON");

		new DropControler(true, new ArrayList<Player>());

		this.getCommand(cmd).setExecutor(new Commands());

		getServer().getPluginManager().registerEvents(new BreakListener(), this);
	}

	public void onDisable() {
		System.out.println("[" +  pdf.getName() + " " + pdf.getVersion() + "]" + "Plugins is OFF");
	}

}
