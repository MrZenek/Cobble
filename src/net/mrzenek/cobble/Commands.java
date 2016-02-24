package net.mrzenek.cobble;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Commands implements CommandExecutor {

	public String name = "cobble";

	public boolean canDrop;

	public ArrayList<Player> drop;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) return false;
		if (sender instanceof ConsoleCommandSender) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("on")) {
							setCanDrop(true);
							System.out.println("|---Cobble---|");
							System.out.println("Global Drop: " + canDrop());
						}
						if (args[1].equalsIgnoreCase("off")) {
							setCanDrop(false);
							System.out.println("|---Cobble---|");
							System.out.println("Global Drop: " + canDrop());
						}
					} else {
						return false;
					}
				} else if(args[0].equalsIgnoreCase("help")) {
					System.out.println("Cobble HELP " + Main.pdf.getVersion());
					System.out.println("/cobble - Showing global drop status");
					System.out.println("/cobble global on - Turn on cobblestone drop");
					System.out.println("/cobble global off - Turn off cobblestone drop");
					System.out.println("Instead, global you can use the g");
				}
			} else {
				System.out.println("|---Cobble---|");
				System.out.println("Global Drop: " + canDrop());
				return false;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("turn")) {
					turn(player);
					player.sendMessage("|---Cobble---|");
					player.sendMessage("Your drop: " + canDrop(player));
				} else if(args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {
					if(args.length > 1) {
						if (args[1].equalsIgnoreCase("on")) {
							setCanDrop(true);
							player.sendMessage("|---Cobble---|");
							player.sendMessage("Global Drop: " + canDrop());
						}
						if (args[1].equalsIgnoreCase("off")) {
							setCanDrop(false);
							player.sendMessage("|---Cobble---|");
							player.sendMessage("Global Drop: " + canDrop());
						}
					} else {
						return false;
					}
				} else if(args[0].equalsIgnoreCase("help")) {
					player.sendMessage("Cobble HELP " + Main.pdf.getVersion());
					player.sendMessage("/cobble - Showing your drop and global drop status");
					player.sendMessage("/cobble turn - changing your cobblestone drop status");
					player.sendMessage("/cobble global on - Turn on cobblestone drop");
					player.sendMessage("/cobble global off - Turn off cobblestone drop");
					player.sendMessage("Instead, global you can use the g");
				}
			} else {
				player.sendMessage("|---Cobble---|");
				player.sendMessage("Your drop: " + canDrop(player));
				player.sendMessage("Global Drop: " + canDrop());
				return false;
			}
		}
		return true;
	}

	public void setCanDrop(boolean can) {
		drop.clear();
		canDrop = can;
	}

	public void turn(Player player) {
		if (drop.contains(player)) {
			drop.remove(player);
		} else {
			drop.add(player);
		}
	}

	public String canDrop() {
		if (canDrop) {
			return "true";
		} else {
			return "false";
		}
	}

	public String canDrop(Player player) {
		if (drop.contains(player)) {
			if(canDrop) return "false";
			return "true";
		} else {
			if(canDrop) return "true";
			return "false";
		}
	}

	public boolean canDropB(Player player) {
		if (drop.contains(player)) {
			return false;
		} else {
			return true;
		}
	}


}

