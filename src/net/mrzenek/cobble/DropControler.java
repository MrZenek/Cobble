package net.mrzenek.cobble;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DropControler {

	public static boolean canDrop;

	public static ArrayList<Player> players;

	public DropControler(boolean canDrop, ArrayList<Player> players) {
		this.canDrop = canDrop;
		this.players = players;
	}

	public static void setCanDrop(boolean can) {
		players.clear();
		canDrop = can;
	}

	public static void turn(Player player) {
		if (players.contains(player)) {
			players.remove(player);
		} else {
			players.add(player);
		}
	}

	public static String canDropString() {
		if (canDrop) {
			return "true";
		} else {
			return "false";
		}
	}

	public static boolean canDropBoolean() {
		return canDrop;
	}

	public static String playerCanDropSring(Player player) {
		if (players.contains(player)) {
			if(canDrop) return "false";
			return "true";
		} else {
			if(canDrop) return "true";
			return "false";
		}
	}

	public static boolean playerCanDropBoolean(Player player) {
		if (players.contains(player)) {
			return false;
		} else {
			return true;
		}
	}
}
