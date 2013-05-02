package Reika.DragonAPI;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public final class ReikaPotionHelper {
	
	private ReikaPotionHelper() {throw new RuntimeException("The class "+this.getClass()+" cannot be instantiated!");}
	
	/** Returns a potion ID from the damage value. Returns -1 if invalid damage value. */
	public static int getPotionID(int dmg) {
        List effects = Item.potion.getEffects(dmg);
        if (effects != null && !effects.isEmpty()) {
        	Iterator potioneffects = effects.iterator();
            while (potioneffects.hasNext()) {
            	PotionEffect effect = (PotionEffect)potioneffects.next();
            	return effect.getPotionID();
            }
		}
        return -1;
	}

}
