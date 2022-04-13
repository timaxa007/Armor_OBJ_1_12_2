package timaxa007.very_custom_armor;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCustomArmor extends ItemArmor {

	public ItemCustomArmor(ItemArmor.ArmorMaterial material, EntityEquipmentSlot armorType) {
		super(material, 1, armorType);
	}

	@Nullable
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return MyMod.MODID + ":textures/empty_armor.png";
	}

	@SideOnly(Side.CLIENT)
	@Nullable
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		return timaxa007.very_custom_armor.client.ProxyClient.getModel(entityLiving, itemStack);
	}

}
