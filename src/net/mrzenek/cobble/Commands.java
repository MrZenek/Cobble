package net.mrzenek.cobble;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	private Main main;

	public Commands(Main main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) return false;
		DropControler dropControler = DropControler.getInstance();
		if (sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = (ConsoleCommandSender) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) { //Console global command
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("on")) {//Console global on command
							dropControler.setCanDrop(true);
							consoleMessage("[Cobble]", "info.globaldrop", dropControler.canDropString(), console);
						}
						if (args[1].equalsIgnoreCase("off")) {//Console global off command
							dropControler.setCanDrop(false);
							consoleMessage("[Cobble]", "info.globaldrop", dropControler.canDropString(), console);
						}
					} else {
						return false;
					}
				} else if (args[0].equalsIgnoreCase("help")) {//Console help command
					System.out.println("Cobble HELP " + main.pdf.getVersion());
					consoleMessage("/cobble: ", "help.cobble", "", console);
					consoleMessage("/cobble global/g on: ", "help.globalon", "", console);
					consoleMessage("/cobble global/g off: ", "help.globaloff", "", console);
					consoleMessage("", "help.instead", "", console);
				}
			} else {
				consoleMessage("[Cobble]", "info.globaldrop", dropControler.canDropString(), console);
				return false;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (cant(player) && args[0].equalsIgnoreCase("turn")) {//Player turn command
					dropControler.turn(player);
					playerMessage("[Cobble]", "info.youdrop", dropControler.playerCanDropSring(player), player);
				} else if (cant(player) && args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {//Player global command
					if (args.length == 2 & pCan(player)) {
						if (args[1].equalsIgnoreCase("on")) {//Player global on command
							dropControler.setCanDrop(true);
							playerMessage("[Cobble]", "info.globaldrop", dropControler.canDropString(), player);
						} else if (args[1].equalsIgnoreCase("off")) {//Player global off command
							dropControler.setCanDrop(false);
							playerMessage("[Cobble]", "info.globaldrop", dropControler.canDropString(), player);
						} else {
							if (!pCan(player)) playerMessage("[Cobble]", "error.nopermission", "", player);
							return false;
						}
					} else {
						if (!pCan(player)) playerMessage("[Cobble]", "error.nopermission", "", player);
						return false;
					}
				} else if (args[0].equalsIgnoreCase("help")) {//Player help command
					player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET + "Cobble HELP [v" + main.pdf.getVersion() + "]" + ChatColor.YELLOW + "---|");
					playerMessage(ChatColor.GOLD + "/cobble: ", "help.cobble", "", player);
					playerMessage(ChatColor.GOLD + "/cobble turn: ", "help.turn", "", player);
					if (pCan(player)) {
						playerMessage(ChatColor.RED + "/cobble global/g on: ", "help.globalon", "", player);
						playerMessage(ChatColor.RED + "/cobble global/g off: ", "help.globaloff", "", player);
						playerMessage("", "help.instead", "", player);
					}
				}
			} else {
				player.sendMessage(ChatColor.YELLOW + "|---" + ChatColor.RESET + "Cobble" + ChatColor.YELLOW + "---|");
				playerMessage("", "info.youdrop", dropControler.playerCanDropSring(player), player);
				playerMessage("", "info.globaldrop", dropControler.canDropString(), player);
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

	public void consoleMessage(String prefix, String input, String text, ConsoleCommandSender console) {
		String msg = main.config.getString("messages." + input);
		msg = replaceColors(prefix + msg, true);
		if (text == null) {
			console.sendMessage(msg);
			return;
		}
		msg += text;
		console.sendMessage(msg);
	}

	public void playerMessage(String prefix, String input, String text, Player player) {
		String msg = main.config.getString("messages." + input);
		msg = replaceColors(prefix + ChatColor.RESET + msg, false);
		if (text == null) {
			player.sendMessage(msg);
			return;
		}
		msg += text;
		player.sendMessage(msg);
	}

	public String replaceColors(String input, boolean console) {
		if (input == null) return null;
		if (console) {
			return input.replaceAll("(&([a-fk-or0-9]))", "");
		}
		return input.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2");
	}

}

