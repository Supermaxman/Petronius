package me.supermaxman.petronius.Events.executors;

import java.util.ConcurrentModificationException;

import me.supermaxman.petronius.Objects.Pet;
import me.supermaxman.petronius.main.Petronius;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class petAttackExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	try{
    	for(Pet pet : Petronius.pets){	
			if(pet.getOwner().equalsIgnoreCase(player.getName())){
				if(pet.getAttack()){
					player.sendMessage(ChatColor.BLUE + "[Petronius]: "+pet.getName()+" Is Not Attacking!");  
					pet.setAttack(false);
				}else{
					player.sendMessage(ChatColor.BLUE + "[Petronius]: "+pet.getName()+" Is Attacking!");  
					pet.setAttack(true);
				}
		        
			}
		}
    	}catch(ConcurrentModificationException e){
    		
    	}
        
        
    }
    
    public petAttackExecutor(Petronius XE) {
        super(XE);
    }
}
