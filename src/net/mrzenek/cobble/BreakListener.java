package net.mrzenek.cobble;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener{

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();

		if(e.getBlock().getType() == Material.STONE) {
			if(!DropControler.canDrop) {
				if(!DropControler.playerCanDropBoolean(player)) return;
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			} else if (!DropControler.playerCanDropBoolean(player)) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			}
		}
	}
}
