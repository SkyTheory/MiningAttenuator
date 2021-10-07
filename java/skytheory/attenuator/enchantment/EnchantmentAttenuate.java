package skytheory.attenuator.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentAttenuate extends Enchantment {

	public static Enchantment INSTANCE = new EnchantmentAttenuate();

	protected EnchantmentAttenuate() {
		super(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("attenuate");
	}

	public int getMinEnchantability(int enchantmentLevel) {
		return 21;
	}

	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 50;
	}

	public int getMaxLevel() {
		return 1;
	}

}
