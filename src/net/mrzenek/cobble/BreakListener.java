package net.mrzenek.cobble;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener{

	public Main main;

	public void init(Main main) {
		this.main = main;
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if(e.getBlock().getType() == Material.STONE) {
			if(!main.cmd.canDrop) {
				if(!main.cmd.canDropB(player)) return;
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			} else if (!main.cmd.canDropB(player)) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
				e.getBlock().setType(Material.AIR);
			}
		}
	}
}
