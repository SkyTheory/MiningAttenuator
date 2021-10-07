package skytheory.attenuator.init.proxy;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skytheory.attenuator.enchantment.EnchantmentAttenuate;

public class CommonProxy {

	public Configuration config;

	public void preInit(FMLPreInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
		ItemStack enchanted = ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(EnchantmentAttenuate.INSTANCE, 1));
		enchanted.addEnchantment(EnchantmentAttenuate.INSTANCE, 1);
		NBTTagCompound enchantRecipeMessage = new NBTTagCompound();
		enchantRecipeMessage.setInteger("energy", 40000);
		enchantRecipeMessage.setTag("input", (new ItemStack(Items.BOOK).serializeNBT()));
		enchantRecipeMessage.setTag("input2", (new ItemStack(Items.COMPARATOR).serializeNBT()));
		enchantRecipeMessage.setTag("output", (enchanted.serializeNBT()));
		enchantRecipeMessage.setInteger("experience", 5000);
		FMLInterModComms.sendMessage("thermalexpansion", "addenchanterrecipe", enchantRecipeMessage);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}
