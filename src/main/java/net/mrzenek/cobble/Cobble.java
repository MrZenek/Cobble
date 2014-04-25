package net.mrzenek.cobble;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Cobble extends JavaPlugin implements Listener {
	
	protected static final Logger log = Logger.getLogger("Minecraft");
	public PluginDescriptionFile pdf = this.getDescription();
	HashMap<String, String>pcobl = new HashMap<String,String>();
	String Name = ChatColor.AQUA + "[" + pdf.getName() + "] " + ChatColor.RESET;
	
    public void onDisable() {
		log.info(pdf.getName() + " zostal wylaczony");
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
		log.info(Name + " zostal wlaczony.");
		log.info(Name + "Autor: " + pdf.getAuthors());
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("cobble")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Name + ChatColor.RED +"Musisz byc graczem by to zrobic!");
				return true;
			}
			if(args.length < 1){
				sender.sendMessage(Name + ChatColor.DARK_RED + "Poprawne użycie komendy: /cobble on/off");
				return true;
			}
			if(args.length > 1){
				sender.sendMessage(Name + ChatColor.DARK_RED + "Za duzo argumentów!");
				sender.sendMessage(Name + ChatColor.DARK_RED + "Poprawne użycie komendy: /cobble on/off");
				return true;
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("off")){
					pcobl.put(sender.getName(), "off");
					sender.sendMessage(Name + ChatColor.DARK_GREEN + "Wylaczyles wypadanie cobble");
					return true;
				}else{
					if(args[0].equalsIgnoreCase("on")){
						sender.sendMessage(Name + ChatColor.BLUE + "Wlaczyles wypadanie cobble");
						pcobl.put(sender.getName(), "on");
						return true;
					}else{
						sender.sendMessage(Name + ChatColor.DARK_RED + "Poprawne użycie komendy: /cobble on/off");
						return true;
					}
				}
			}
	}
		return false;	
	}

    @EventHandler(priority=EventPriority.HIGHEST)
	public void PlayerBreak(BlockBreakEvent event){
		Player p = event.getPlayer();
		if(event.getBlock().getType() == Material.STONE){
			if(pcobl.get(p.getName()).equals("off")){
			event.setCancelled(true);
			
			event.getBlock().setType(Material.AIR);
			}
		}
	}
}

