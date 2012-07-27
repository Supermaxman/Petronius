package me.supermaxman.petronius.Objects;

import me.supermaxman.petronius.Events.PetMoveEvent;
import me.supermaxman.petronius.main.Petronius;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class Pet implements InventoryHolder {
	
    private String name;
    private String owner;
    private Material type;
    private Item item;
    private double height;
    private boolean attack;
    private Inventory inv;
    private Location loc;
    //private boolean isVisable;
    public Pet(String owner, Material type, Location loc, Server s, double height) {
        this.owner = owner;
        this.type = type;
        this.height = height;
        this.item = loc.getWorld().dropItem(loc, new ItemStack(type,1));
        this.item.setPickupDelay(Integer.MAX_VALUE);
        this.loc = loc;
        Petronius.pets.add(this);
        new PetMoveEvent(this);
        this.inv = s.createInventory(this, 9);
    }
    
    //inventory
    public void giveItem(ItemStack i){
    	this.inv.addItem(i);
    }
    public Inventory getInventory(){
		return inv;
    }
    public void setInventory(Inventory inv){
		this.inv =  inv;
    }
    public void killPet(){
        Petronius.pets.remove(this);
        try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }
    
    //attack
    public void setAttack(boolean b){
    	this.attack = b;
    }
    public boolean getAttack(){
		return attack;
    }
    //height
    public void setHeight(double h){
    	this.height = h;
    }
    public double getHeight(){
		return height;
    }
    //item
    public void setItem(Item i){
    	this.item = i;
    }
    public Item getItem(){
		return item;
    }
    //name
    public void setName(String name){
    	this.name = name;
    }
    public String getName(){
		return name;
    }
    //type
    public void setType(Material type){
    	this.type = type;
    }
    public Material getType(){
		return type;
    }
    //owner
    public String getOwner(){
		return owner;
    }
    public void setOwner(String owner){
		this.owner = owner;
    }
    

    public void save() {
        FileConfiguration config = Petronius.conf;
        config.set("pets." + owner + ".name", name);
        config.set("pets." + owner + ".material", type.toString());
        config.set("pets." + owner + ".location.world", loc.getWorld().getName());
        config.set("pets." + owner + ".location.x", loc.getX());
        config.set("pets." + owner + ".location.y", loc.getY());
        config.set("pets." + owner + ".location.z", loc.getZ());
        config.set("pets." + owner + ".height", height);
        int slot = 0;
        for(ItemStack i: inv.getContents()){
        	if(i!=null){
            config.set("pets." + owner + ".inventory.items."+slot+".material", i.getType().toString());
            config.set("pets." + owner + ".inventory.items."+slot+".amount", i.getAmount());
            config.set("pets." + owner + ".inventory.items."+slot+".data", i.getData().getData());
            config.set("pets." + owner + ".inventory.items."+slot+".durability", i.getDurability());
        	}else{
                config.set("pets." + owner + ".inventory.items."+slot+".material", "AIR");
        	}
            slot++;
        }
        this.item.remove();
        Petronius.plugin.saveConfig();
    }
    
}
