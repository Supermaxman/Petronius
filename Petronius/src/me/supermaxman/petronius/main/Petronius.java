package me.supermaxman.petronius.main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import me.supermaxman.petronius.Events.executors.petAttackExecutor;
import me.supermaxman.petronius.Events.executors.petCreatorExecutor;
import me.supermaxman.petronius.Events.executors.petDeleteExecutor;
import me.supermaxman.petronius.Events.executors.petInventoryExecutor;
import me.supermaxman.petronius.Listener.PetroniusListener;
import me.supermaxman.petronius.Objects.Pet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Petronius extends JavaPlugin{
	public static Petronius plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	public static List<Pet> pets = new ArrayList<Pet>();
    public static FileConfiguration conf;
    
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
		savePets();
	}
	
	@Override
	public void onEnable() {
        conf = getConfig();
		Petronius.plugin = this;
        setupConfig();
        getServer().getPluginManager().registerEvents(new PetroniusListener(), this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
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
	
    public void setupConfig() {
        
        if (conf.isConfigurationSection("pets")) {
            for (Map.Entry<String, Object> entry : conf.getConfigurationSection("pets").getValues(false).entrySet()) {
                ConfigurationSection cs = conf.getConfigurationSection("pets." + entry.getKey());
                
                Pet pet = new Pet(entry.getKey().toString(), Material.valueOf(cs.getString("material").toUpperCase()), 
                		(new Location(plugin.getServer().getWorld(cs.getString("location.world")),
                				Double.parseDouble(cs.getString("location.x")),
                				Double.parseDouble(cs.getString("location.y")),
                				Double.parseDouble(cs.getString("location.z")))), 
                				this.getServer(), 
                				Double.parseDouble(cs.getString("height")));
                pet.setName(cs.getString("name"));
                Inventory inv = plugin.getServer().createInventory(pet, 9);
                int slots = 0;
                while(slots<inv.getSize()){
                	if(!cs.getString("inventory.items."+slots+".material").equalsIgnoreCase("AIR")){
                inv.setItem(slots, 
                		new ItemStack(Material.valueOf(cs.getString("inventory.items."+slots+".material").toUpperCase()), 
                		Integer.parseInt(cs.getString("inventory.items."+slots+".amount")), 
                		Short.parseShort(cs.getString("inventory.items."+slots+".durability"))
                		//,(byte)Integer.parseInt(cs.getString("inventory.items."+slots+".data"))
                		));
                	}
                slots++;
                }
                pet.setInventory(inv);
                pets.add(pet);
                
                
            }


        }
    }

    public void savePets() {
        for (Pet pet : pets) {
            pet.save();
        }
        saveConfig();
    }
	
	
	
}
