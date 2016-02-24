package net.mrzenek.cobble;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) return false;
		if (sender instanceof ConsoleCommandSender) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) { //Console global command
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("on")) {//Console global on command
							DropControler.setCanDrop(true);
							System.out.println("|---Cobble---|");
							System.out.println("Global Drop: " + DropControler.canDropString());
						}
						if (args[1].equalsIgnoreCase("off")) {//Console global off command
							DropControler.setCanDrop(false);
							System.out.println("|---Cobble---|");
							System.out.println("Global Drop: " + DropControler.canDropString());
						}
					} else {
						return false;
					}
				} else if (args[0].equalsIgnoreCase("help")) {//Console help command
					System.out.println("Cobble HELP " + Main.pdf.getVersion());
					System.out.println("/cobble - Showing global drop status");
					System.out.println("/cobble global/g on - Turn on cobblestone drop");
					System.out.println("/cobble global/g off - Turn off cobblestone drop");
					System.out.println("Instead, global you can use the g");
				}
			} else {
				System.out.println("|---Cobble---|");
				System.out.println("Global Drop: " + DropControler.canDropString());
				return false;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("turn")) {//Player turn command
					DropControler.turn(player);
					player.sendMessage("|---Cobble---|");
					player.sendMessage("Your drop: " + DropControler.playerCanDropSring(player));
				} else if (args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {//Player global command
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("on")) {//Player global on command
							DropControler.setCanDrop(true);
							player.sendMessage("|---Cobble---|");
							player.sendMessage("Global Drop: " + DropControler.canDropString());
						}
						if (args[1].equalsIgnoreCase("off")) {//Player global off command
							DropControler.setCanDrop(false);
							player.sendMessage("|---Cobble---|");
							player.sendMessage("Global Drop: " + DropControler.canDropString());
						}
					} else {
						return false;
					}
				} else if (args[0].equalsIgnoreCase("help")) {//Player help command
					player.sendMessage("Cobble HELP " + Main.pdf.getVersion());
					player.sendMessage("/cobble - Showing your drop and global drop status");
					player.sendMessage("/cobble turn - changing your cobblestone drop status");
					player.sendMessage("/cobble global/g on - Turn on cobblestone drop");
					player.sendMessage("/cobble global/g off - Turn off cobblestone drop");
					player.sendMessage("Instead, global you can use the g");
				}
			} else {
				player.sendMessage("|---Cobble---|");
				player.sendMessage("Your drop: " + DropControler.playerCanDropSring(player));
				player.sendMessage("Global Drop: " + DropControler.canDropString());
				return false;
			}
		}
		return true;
	}
}

