package skytheory.attenuator;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import skytheory.attenuator.enchantment.EnchantmentAttenuate;
import skytheory.attenuator.event.AttenuateEvent;
import skytheory.attenuator.init.proxy.CommonProxy;
import skytheory.lib.init.ResourceRegister;

@Mod(
		modid=MiningAttenuator.MOD_ID,
		name=MiningAttenuator.MOD_NAME,
		version=MiningAttenuator.VERSION,
		dependencies = "required-after:stlib@[1.12.2-1.3.0,);after:endercore;after:thermalexpansion"
		)

public class MiningAttenuator {

	public static final String MOD_ID = "mining_attenuator";
	public static final String MOD_NAME = "Mining Attenuator";
	public static final String MC_VERSION = "1.12.2";
	public static final String MOD_VERSION = "1.0.0";
	public static final String VERSION = MC_VERSION + "-" + MOD_VERSION;

	@Mod.Instance
	public static MiningAttenuator INSTANCE;

	public static final String PROXY_CLIENT = "skytheory.attenuator.init.proxy.CommonProxy";
	public static final String PROXY_SERVER = "skytheory.attenuator.init.proxy.CommonProxy";

	@SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void construct(FMLConstructionEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(AttenuateEvent.class);
	}

	@SubscribeEvent
	public void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
		ResourceRegister.register(event.getRegistry(), EnchantmentAttenuate.INSTANCE, MOD_ID, "attenuate", null);
		// Ender IO
		FMLInterModComms.sendMessage("enderio", "recipe:xml",
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<recipes>"
						+ "<recipe name=\"Enchanter: mining_attenuator:attenuate\" required=\"true\" disabled=\"false\">"
						+ "<enchanting>"
						+ "<input name=\"minecraft:comparator\" amount=\"16\"/>"
						+ "<enchantment name=\"mining_attenuator:attenuate\" costMultiplier=\"1\"/>"
						+ "</enchanting>"
						+ "</recipe>"
						+ "</recipes>");
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
