package me.supermaxman.petronius.Events;

import me.supermaxman.petronius.Objects.Pet;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PetAttackEvent {
	private boolean hasAttacked = false;
	public PetAttackEvent(final Pet pet){
			Item i = pet.getItem();
			if(i.getServer().getPlayerExact(pet.getOwner())!=null){
				
				//Arrow a = i.getWorld().spawn(i.getLocation().add(0, 1, 0),Arrow.class);
				//a.setVelocity(i.getServer().getPlayerExact(pet.getOwner()).getLocation().getDirection().multiply(3));
				for(Entity e:i.getNearbyEntities(10, 10, 10)){
					if (e instanceof LivingEntity){
						if(e instanceof Monster){
						if(!(e instanceof Player)){
							//attack type
							ItemStack[] items = pet.getInventory().getContents();
							if(items[0].getType()==Material.BOW){
							for(ItemStack it : pet.getInventory().getContents()){
								if (it==null){
								}else if ((it.getType()==Material.ARROW)&&(it.getAmount()>1)){
									items[0].setDurability((short)(items[0].getDurability()+1));
									it.setAmount(it.getAmount()-1);
									Arrow a = i.getWorld().spawn(i.getLocation().add(0, 1, 0),Arrow.class);
									a.setVelocity(((e.getLocation().toVector().subtract(i.getLocation().toVector()).multiply(0.2))));
									a.setShooter(i.getServer().getPlayerExact(pet.getOwner()));
									hasAttacked = true;
									break;
								}else if((it.getType()==Material.ARROW)&&(it.getAmount()==1)){
									items[0].setDurability((short)(items[0].getDurability()+1));
									pet.getInventory().removeItem(it);
									Arrow a = i.getWorld().spawn(i.getLocation().add(0, 1, 0),Arrow.class);
									a.setVelocity(((e.getLocation().toVector().subtract(i.getLocation().toVector()).multiply(0.2))));
									a.setShooter(i.getServer().getPlayerExact(pet.getOwner()));
									hasAttacked = true;
									break;
								}
							}
							}
							
							
							break;
						}
					}
				}
				
			}
				
			if(hasAttacked == false){
				for(Entity e:i.getNearbyEntities(4, 4, 4)){
					if (e instanceof LivingEntity){
						if(e instanceof Monster){
						if(!(e instanceof Player)){
							//attack type
							ItemStack[] items = pet.getInventory().getContents();
							if(items[0].getType()==Material.WOOD_SWORD||items[0].getType()==Material.STONE_SWORD||items[0].getType()==Material.IRON_SWORD||items[0].getType()==Material.DIAMOND_SWORD||items[0].getType()==Material.GOLD_SWORD){
								ItemStack sword = items[0];
								sword.setDurability((short)(sword.getDurability()+1));
								((LivingEntity) e).damage(3, i.getServer().getPlayerExact(pet.getOwner()));
								hasAttacked = true;
							}
							
							
							break;
						}
					}
				}
				}
			}

	}
	}
	
}
