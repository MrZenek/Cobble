package net.mrzenek.cobble;

import org.bukkit.ChatColor;
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
							Main.getDropController().setCanDrop(true);
							System.out.println("|---Cobble---|");
							System.out.println("Global Drop: " + Main.getDropController().canDropString());
						}
						if (args[1].equalsIgnoreCase("off")) {//Console global off command
							Main.getDropController().setCanDrop(false);
							System.out.println("|---Cobble---|");
							System.out.println("Global Drop: " + Main.getDropController().canDropString());
						}
					} else {
						return false;
					}
				} else if (args[0].equalsIgnoreCase("help")) {//Console help command
					System.out.println("Cobble HELP " + Main.getPDF().getVersion());
					System.out.println("/cobble - Showing global drop status");
					System.out.println("/cobble global/g on - Turn on cobblestone drop");
					System.out.println("/cobble global/g off - Turn off cobblestone drop");
					System.out.println("Instead, global you can use the g");
				}
			} else {
				System.out.println("|---Cobble---|");
				System.out.println("Global Drop: " + Main.getDropController().canDropString());
				return false;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (cant(player) && args[0].equalsIgnoreCase("turn")) {//Player turn command
					Main.getDropController().turn(player);
					player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET +  "Cobble" + ChatColor.YELLOW + "---|");
					player.sendMessage(ChatColor.LIGHT_PURPLE + "Your drop: " + Main.getDropController().playerCanDropSring(player));
				} else if (cant(player) && args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {//Player global command
					if (args.length == 2 & pCan(player)) {
						if (args[1].equalsIgnoreCase("on")) {//Player global on command
							Main.getDropController().setCanDrop(true);
							player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET +  "Cobble" + ChatColor.YELLOW + "---|");
							player.sendMessage(ChatColor.GREEN + "Global Drop: " + Main.getDropController().canDropString());
						} else
						if (args[1].equalsIgnoreCase("off")) {//Player global off command
							Main.getDropController().setCanDrop(false);
							player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET +  "Cobble" + ChatColor.YELLOW + "---|");
							player.sendMessage(ChatColor.RED + "Global Drop: " + Main.getDropController().canDropString());
						} else {
							if (!pCan(player)) player.sendMessage(ChatColor.RED + "You can`t do that!");
							return false;
						}
					} else {
						if (!pCan(player)) player.sendMessage(ChatColor.RED + "You can`t do that!");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("help")) {//Player help command
					player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET +  "Cobble HELP [v" + Main.getPDF().getVersion() + "]" + ChatColor.YELLOW + "---|");
					player.sendMessage(ChatColor.GOLD + "/cobble:" + ChatColor.RESET +  " Showing your drop and global drop status");
					player.sendMessage(ChatColor.GOLD + "/cobble turn:" + ChatColor.RESET +  " Changing your cobblestone drop status");
					if (pCan(player)) {
						player.sendMessage(ChatColor.RED + "/cobble global/g on" + ChatColor.RESET +  " Turn on cobblestone drop");
						player.sendMessage(ChatColor.RED + "/cobble global/g off" + ChatColor.RESET +  " Turn off cobblestone drop");
						player.sendMessage(ChatColor.GRAY + "Instead, global you can use the g");
					}
				}
			} else {
				player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET +  "Cobble" + ChatColor.YELLOW + "---|");
				player.sendMessage(ChatColor.GREEN + "Your drop: " + Main.getDropController().playerCanDropSring(player));
				player.sendMessage(ChatColor.AQUA + "Global Drop: " + Main.getDropController().canDropString());
				return false;
			}
		}
		return true;
	}

	private boolean pCan(Player player) {
		if (player.isOp()) return true;
		if (player.hasPermission("cobble.admin") | player.hasPermission("cobble.global")) return true;
		return false;
	}

	private boolean cant(Player player) {
		if (player.isOp()) return true;
		if (player.hasPermission("cobble.cant")) return false;
		return true;
	}

}

