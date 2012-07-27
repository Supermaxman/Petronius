package me.supermaxman.petronius.Events.executors;

import java.util.ConcurrentModificationException;

import me.supermaxman.petronius.Objects.Pet;
import me.supermaxman.petronius.main.Petronius;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class petDeleteExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	try{
    	for(Pet pet : Petronius.pets){	
			if(pet.getOwner().equalsIgnoreCase(player.getName())){
		        player.sendMessage(ChatColor.RED + "[Petronius]: "+pet.getName()+" Deleted");  
				pet.killPet();
			}
		}
    	}catch(ConcurrentModificationException e){
    		
    	}
        
        
    }
    
    public petDeleteExecutor(Petronius XE) {
        super(XE);
    }
}
