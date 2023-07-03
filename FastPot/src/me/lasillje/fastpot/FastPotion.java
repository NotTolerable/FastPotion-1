package me.lasillje.fastpot;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FastPotion extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
                getServer().getPluginManager().registerEvents(this, this);
                }

        @EventHandler
        public void onPlayerDamage(EntityDamageByEntityEvent event) {
                if (event.getEntity() instanceof Player) {
                        Player damagedPlayer = (Player) event.getEntity();
                        disableShield(damagedPlayer);
                }
        
                if (event.getDamager() instanceof Player) {
                        Player damager = (Player) event.getDamager();
                        disableShield(damager);
                }
        }

        @EventHandler
        public void onPlayerItemDamage(PlayerItemDamageEvent event) {
                if (event.getItem().getType() == Material.SHIELD) {
                        event.setCancelled(true);
                }
        }

        private void disableShield(Player player) {
                player.getInventory().setItemInOffHand(null); // Clear off-hand item
        
                new BukkitRunnable() {
                        @Override
                        public void run() {
                                player.getInventory().setItemInOffHand(player.getInventory().getItemInMainHand()); // Restore off-hand item
                        }
                }.runTaskLater(this, 20L); // Delay for 1 second (20 ticks)
        }
}
