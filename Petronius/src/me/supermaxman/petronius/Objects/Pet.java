package me.supermaxman.petronius.Objects;

import me.supermaxman.petronius.Events.PetMoveEvent;
import me.supermaxman.petronius.main.Petronius;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
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
    
    public Pet(String owner, Material type, Player p, double height) {
        this.owner = owner;
        this.type = type;
        this.height = height;
        this.item = p.getWorld().dropItem(p.getLocation().add(0, 2, 0), new ItemStack(type,1));
        this.item.setPickupDelay(Integer.MAX_VALUE);
        Petronius.pets.add(this);
        new PetMoveEvent(this);
        this.inv = p.getServer().createInventory(this, 9);
        
    }
    
    
    //inventory
    public void giveItem(ItemStack i){
    	this.inv.addItem(i);
    }
    public Inventory getInventory(){
		return inv;
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
    

    
    
}
