package net.mrzenek.cobble;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DropControler {

	private boolean canDrop;

	private List<Player> players;

	public DropControler(boolean canDrop) {
		this.canDrop = canDrop;
		this.players = new ArrayList<Player>();
	}

	public DropControler(boolean canDrop, ArrayList<Player> players) {
		this.canDrop = canDrop;
		this.players = players;
	}

	public void setCanDrop(boolean can) {
		players.clear();
		canDrop = can;
	}

	public void turn(Player player) {
		if (players.contains(player)) {
			players.remove(player);
		} else {
			players.add(player);
		}
	}

	public String canDropString() {
		return canDrop ? "true" : "false";
	}

	public boolean canDropBoolean() {
		return canDrop;
	}

	public String playerCanDropSring(Player player) {
		if (players.contains(player)) {
			return canDrop ? "false" : "true";
		} else {
			return canDrop ? "true" : "false";
		}
	}

	public boolean playerCanDropBoolean(Player player) {
		return players.contains(player) ? false : true;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
