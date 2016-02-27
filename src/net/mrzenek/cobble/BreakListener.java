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
		DropControler dropControler = DropControler.getInstance();
		if (e.getBlock().getType() == Material.STONE) {
			if(player.getGameMode() != GameMode.SURVIVAL) return;
			if (!player.isOp() & player.hasPermission("cobble.on")) return;
			if (!player.isOp() & player.hasPermission("cobble.off")) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			}
			if (!dropControler.canDropBoolean()) {
				if (!dropControler.playerCanDropBoolean(player)) return;
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			} else if (!dropControler.playerCanDropBoolean(player)) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			}
		}
	}

}
