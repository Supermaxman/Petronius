package me.supermaxman.petronius.main;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.supermaxman.petronius.Events.executors.petAttackExecutor;
import me.supermaxman.petronius.Events.executors.petCreatorExecutor;
import me.supermaxman.petronius.Events.executors.petDeleteExecutor;
import me.supermaxman.petronius.Events.executors.petInventoryExecutor;
import me.supermaxman.petronius.Listener.PetroniusListener;
import me.supermaxman.petronius.Objects.Pet;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Petronius extends JavaPlugin{
	public static Petronius plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	public static List<Pet> pets = new ArrayList<Pet>();
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
	}
	
	@Override
	public void onEnable() {
        getServer().getPluginManager().registerEvents(new PetroniusListener(), this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
		Petronius.plugin = this;
        getCommand("cpet").setExecutor(new petCreatorExecutor(this));
        getCommand("createpet").setExecutor(new petCreatorExecutor(this));
        getCommand("dpet").setExecutor(new petDeleteExecutor(this));
        getCommand("deletepet").setExecutor(new petDeleteExecutor(this));
        getCommand("pa").setExecutor(new petAttackExecutor(this));
        getCommand("petattack").setExecutor(new petAttackExecutor(this));
        getCommand("pi").setExecutor(new petInventoryExecutor(this));
        getCommand("petinventory").setExecutor(new petInventoryExecutor(this));
        getCommand("petinv").setExecutor(new petInventoryExecutor(this));
        
	}
	
	
	
	
	
}
