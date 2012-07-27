package me.supermaxman.petronius.Events;

import me.supermaxman.petronius.Objects.Pet;
import me.supermaxman.petronius.main.Petronius;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

public class PetMoveEvent {
	
	private int t;
	private int aDelay = 0;
	public PetMoveEvent(final Pet pet){
		t = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Petronius.plugin, new Runnable(){ 
			public void run() {	
					Item i = pet.getItem();
					if(i.getServer().getPlayerExact(pet.getOwner())!=null){
					if(i.isDead()){
						i.remove();
						i.getServer().getPlayerExact(pet.getOwner()).sendMessage(ChatColor.RED + "[Petronius]: "+pet.getName()+" Has Died.");  
				        Petronius.pets.remove(pet);
						pet.killPet();
						Bukkit.getServer().getScheduler().cancelTask(t);
					}
					if(!Petronius.pets.contains(pet)){
						if(i!=null){
							i.remove();
						}
						Bukkit.getServer().getScheduler().cancelTask(t);
						pet.killPet();
					}
					if(pet.getAttack()){
						if(aDelay>=20){
							aDelay = 0;
							new PetAttackEvent(pet);
						}
					}
					i.getItemStack().setAmount(1);
					i.teleport(i.getServer().getPlayerExact(pet.getOwner()).getLocation().add(0, pet.getHeight(), 0));
					i.setVelocity(new Vector(0,0.1,0));
					aDelay++;
					}
			}
		},1,1);

	}
	
}
