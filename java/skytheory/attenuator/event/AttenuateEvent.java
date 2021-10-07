package skytheory.attenuator.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import skytheory.attenuator.enchantment.EnchantmentAttenuate;

public class AttenuateEvent {

	@SubscribeEvent
	public static void calcBreakSpeed(PlayerInteractEvent.LeftClickBlock event) {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			if (EnchantmentHelper.getEnchantmentLevel(EnchantmentAttenuate.INSTANCE, event.getItemStack()) >= 1) {
				EntityPlayer player = event.getEntityPlayer();
				World world = player.world;
				BlockPos pos = event.getPos();
				IBlockState state = world.getBlockState(pos);
				float damage = ForgeHooks.blockStrength(state, player, world, pos);
				if (damage >= 1.0f) {
					Minecraft.getMinecraft().playerController.blockHitDelay = 5;
				}
			}
		}
	}
}
