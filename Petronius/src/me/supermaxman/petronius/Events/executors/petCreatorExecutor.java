package me.supermaxman.petronius.Events.executors;

import me.supermaxman.petronius.Objects.Pet;
import me.supermaxman.petronius.main.Petronius;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class petCreatorExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String name = args[0];
            Material m = player.getItemInHand().getType();
            if(m!=Material.AIR){
            	
            
            boolean hasPet = false;
    		for(Pet pet : Petronius.pets){	
    			if(pet.getOwner().equalsIgnoreCase(player.getName())){
                	player.sendMessage(ChatColor.RED + "[Petronius]: You Already Have A Pet!");
                	hasPet = true;
                	break;
    			}
    		}
    		if(!hasPet){
    			ItemStack it = player.getItemInHand();
    			if ((it.getAmount()>1)){
					it.setAmount(it.getAmount()-1);
				}else if((it.getAmount()==1)){
					player.getInventory().removeItem(it);
				}
        	Pet pet = new Pet(player.getName(), m, player.getLocation(), player.getServer(), 2.1);
        	pet.setName(name);
			player.sendMessage(ChatColor.BLUE + "[Petronius]: "+pet.getName()+" Has Been Created.");  
            
    		}
            }else{
                player.sendMessage(ChatColor.RED + "[Petronius]: ERROR, You are not holding an item in hand.");
            }
        } else {
        	
            player.sendMessage(ChatColor.RED + "[Petronius]: SYNTAX ERROR, type /cpet [name] with an item in hand.");
            
        }
    }
    
    public petCreatorExecutor(Petronius XE) {
        super(XE);
    }
}
