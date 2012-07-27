package me.supermaxman.petronius.Events.executors;

import java.util.ConcurrentModificationException;

import me.supermaxman.petronius.Objects.Pet;
import me.supermaxman.petronius.main.Petronius;

import org.bukkit.entity.Player;

public class petInventoryExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	try{
    	for(Pet pet : Petronius.pets){	
			if(pet.getOwner().equalsIgnoreCase(player.getName())){
				player.openInventory(pet.getInventory());
				break;
			}
		}
    	}catch(ConcurrentModificationException e){
    		
    	}
        
        
    }
    
    public petInventoryExecutor(Petronius XE) {
        super(XE);
    }
}
