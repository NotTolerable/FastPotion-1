package me.lasillje.fastpot;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.lasillje.fastpot.listeners.PotionListener;

public class FastPotion extends JavaPlugin {

	@Override
	public void onEnable() {
		registerListener(new PotionListener());
	}
	
	public void registerListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener,this);
	}
}
