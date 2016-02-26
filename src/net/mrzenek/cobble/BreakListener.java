package net.mrzenek.cobble;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();

		if (e.getBlock().getType() == Material.STONE) {
			if(player.getGameMode() != GameMode.SURVIVAL | player.getGameMode() != GameMode.ADVENTURE) return;
			if (!player.isOp() & player.hasPermission("cobble.on")) return;
			if (!player.isOp() & player.hasPermission("cobble.off")) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			}
			if (!Main.getDropController().canDropBoolean()) {
				if (!Main.getDropController().playerCanDropBoolean(player)) return;
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			} else if (!Main.getDropController().playerCanDropBoolean(player)) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			}
		}
	}

}
