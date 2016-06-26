package me.soringaming.moon.korra.SpiritualProjection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;

public class SpiritualProjectionListener implements Listener {
	
	@EventHandler
	public void toggleSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		BendingPlayer bp = BendingPlayer.getBendingPlayer(p);
		if(p.isSneaking()) {
			return;
		}
		if(bp.canBend(CoreAbility.getAbility("SpiritualProjection"))) {
			new SpiritualProjection(p);
		}
	}

}
