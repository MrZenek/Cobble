package net.mrzenek.cobble;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

	public PluginDescriptionFile pdf;
	public FileConfiguration config;
	private String cmd = "cobble";

	public void onDisable() {
		DropControler dropControler = DropControler.getInstance();
		config.set("global-drop", dropControler.canDropBoolean());
		System.out.println("[" + pdf.getName() + " " + pdf.getVersion() + "]" + "Plugin is OFF");
	}

	public void onEnable() {
		pdf = this.getDescription();
		config = this.getConfig();
		setup();

		System.out.println("[" + pdf.getName() + " " + pdf.getVersion() + "]" + "Plugin is ON");

		this.getCommand(cmd).setExecutor(new Commands(this));
		getServer().getPluginManager().registerEvents(new BreakListener(), this);
	}

	private void setup() {
		if(config.getInt("version") == 0){
			File cc = new File(this.getDataFolder(), "config.yml");
			cc.delete();
		}
		config.options().copyDefaults(true);
		this.saveDefaultConfig();
		boolean drop = config.getString("global-drop") == null ? true : config.getBoolean("global-drop");
		DropControler dropControler = DropControler.getInstance();
		dropControler.setCanDrop(drop);
	}

}
