package net.mrzenek.cobble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import net.mrzenek.cobble.events.PlayerBreak;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Cobble extends JavaPlugin implements Listener{
	
	protected static Logger logger;
	public PluginDescriptionFile pdf = this.getDescription();
	public static ArrayList<Player>offc = new ArrayList<Player>();
	public static HashMap<String, String>global = new HashMap<String, String>();
	
	Cobble p = this;
	
	public void onEnable(){
		logger = p.getLogger();
		logger.info("zostal wlaczony.");
		logger.info("Autor: " + pdf.getAuthors());
		
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Startup(), 10);
	}
	
	public void onDisable(){
		logger.info(pdf.getName() + " zostal wylaczony");
	}
	
	class Startup implements Runnable{

		public void run() {
			PluginManager pm = getServer().getPluginManager();
			setCommands();
			pm.registerEvents(new PlayerBreak(), p);
		}
		
	}
	
	public void setCommands(){
		getCommand("cobble").setExecutor(new WczytajKomende(p));
	}
	
	
}
