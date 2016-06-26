package me.soringaming.moon.korra.SpiritualProjection;

import java.util.logging.Level;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.SpiritualAbility;

public class SpiritualProjection extends SpiritualAbility implements AddonAbility {
	
	private Player player;
	private Location loc;
	private Location start;
	private Permission perm;
	private long startTime;
	private long chargeTime;
	private long Duration;
	private GameMode gamemode;
	private boolean Charged;
	private boolean Activated;
	
	public SpiritualProjection(Player player) {
		super(player);
		this.player = player;
		this.loc = player.getLocation();
		this.start = player.getLocation();
		this.startTime = System.currentTimeMillis();
		this.chargeTime = 5000;
		this.Charged = false;
		this.Activated = false;
		this.gamemode = player.getGameMode();
		start();
	}

	@Override
	public long getCooldown() {
		return 0;
	}

	@Override
	public Location getLocation() {
		return player.getLocation();
	}

	@Override
	public String getName() {
		return "SpiritualProjection";
	}

	@Override
	public boolean isHarmlessAbility() {
		return true;
	}

	@Override
	public boolean isSneakAbility() {
		return true;
	}

	@Override
	public void progress() {
		if(!bPlayer.getBoundAbility().equals(CoreAbility.getAbility("SpiritualProjection"))) {
			remove();
			return;
		}
		this.loc = player.getLocation();
		if(Charged == false) {
			if(player.isSneaking() && chargeTime + startTime > System.currentTimeMillis()) {
				
			}
			
			if(player.isSneaking() && chargeTime + startTime < System.currentTimeMillis()) {
				Charged = true;
			}
			
			if(!player.isSneaking() && chargeTime + startTime > System.currentTimeMillis()) {
				remove();
				return;
			}
			if(!player.isSneaking() && chargeTime + startTime < System.currentTimeMillis()) {
				Charged = true;
			}
		} else {
			if(Activated == false) {
				this.startTime = System.currentTimeMillis();
				this.Duration = 6000;
				Activated = true;
			}
			if(startTime + Duration < System.currentTimeMillis()) {
				remove();
				return;
			}
			player.setGameMode(GameMode.SPECTATOR);
			if(loc.getBlock().getType() == Material.BEDROCK || loc.getBlock().getType() == Material.BARRIER) {
				remove();
				return;
			}
		}
		
	}

	@Override
	public String getAuthor() {
		return "Soringaming, SquirtleHerder and Moon243";
	}

	@Override
	public String getVersion() {
		return "v1.0";
	}

	@Override
	public void load() {
		ProjectKorra.plugin.getServer().getLogger().log(Level.INFO, getName() + " " + getVersion() + " Developed By " + getAuthor() + " Has Been Enabled.");
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new SpiritualProjectionListener(), ProjectKorra.plugin);
		perm = new Permission("bending.ability.SpiritualProjection");
		ProjectKorra.plugin.getServer().getPluginManager().addPermission(perm);
		perm.setDefault(PermissionDefault.TRUE);
	}
	
	@Override
	public void remove() {
		player.teleport(start);
		player.setGameMode(gamemode);
	}

	@Override
	public void stop() {
		ProjectKorra.plugin.getServer().getLogger().log(Level.INFO, getName() + " " + getVersion() + " Developed By " + getAuthor() + " Has Been Disabled.");
		ProjectKorra.plugin.getServer().getPluginManager().removePermission(perm);
		super.remove();
	}

}
