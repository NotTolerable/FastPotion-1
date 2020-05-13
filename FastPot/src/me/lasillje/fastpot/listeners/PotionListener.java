package me.lasillje.fastpot.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

public class PotionListener implements Listener {
	
	@EventHandler
	public void onPotThrow(ProjectileLaunchEvent e) {
		
		ThrownPotion thrown = (e.getEntity().getType() == EntityType.SPLASH_POTION) ? (ThrownPotion) e.getEntity() : null;
		
		if(thrown == null) {
			return;
		}
		
		PotionMeta meta = (PotionMeta) thrown.getItem().getItemMeta();
		
		if(meta.getBasePotionData().getType() == PotionType.INSTANT_HEAL) {
			if(thrown.getShooter() instanceof Player) {
				Vector velocity = thrown.getVelocity();
				velocity.setY(velocity.getY()-4D);
				thrown.setVelocity(velocity);
			}
		}
	}
	
	@EventHandler
	public void onPotSplash(PotionSplashEvent e) {	
		
		PotionMeta pm = (PotionMeta) e.getPotion().getItem().getItemMeta();
		
		if(pm.getBasePotionData().getType() == PotionType.INSTANT_HEAL) { 
			if(e.getEntity().getShooter() instanceof Player) {
				Player player = (Player) e.getEntity().getShooter();
				e.setIntensity(player, 1.0D);
			}
		}
	}
}

