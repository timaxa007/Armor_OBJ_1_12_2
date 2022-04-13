package timaxa007.very_custom_armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = MyMod.MODID, name = MyMod.NAME, version = MyMod.VERSION)
public class MyMod {

	public static final String
	MODID = "very_custom_armor",
	NAME = "Very Custom Armor",
	VERSION = "0.5.2";

	@Mod.Instance(MODID) public static MyMod instance;

	@SidedProxy(modId = MODID,
			serverSide = "timaxa007.very_custom_armor.ProxyCommon",
			clientSide = "timaxa007.very_custom_armor.client.ProxyClient")
	public static ProxyCommon proxy;

	public static Item
	armorHelmet,
	armorVest,
	armorPants,
	armorBoots;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		armorHelmet = new ItemCustomArmor(ArmorMaterial.IRON, EntityEquipmentSlot.HEAD)
				.setRegistryName(new ResourceLocation(MODID, "armor_helmet"))
				.setUnlocalizedName("armor_helmet")
				//.setTextureName(MODID + ":armor_helmet")
				.setCreativeTab(CreativeTabs.COMBAT);
		ForgeRegistries.ITEMS.register(armorHelmet);

		armorVest = new ItemCustomArmor(ArmorMaterial.IRON, EntityEquipmentSlot.CHEST)
				.setRegistryName(new ResourceLocation(MODID, "armor_vest"))
				.setUnlocalizedName("armor_vest")
				//.setTextureName(MODID + ":armor_vest")
				.setCreativeTab(CreativeTabs.COMBAT);
		ForgeRegistries.ITEMS.register(armorVest);

		armorPants = new ItemCustomArmor(ArmorMaterial.IRON, EntityEquipmentSlot.LEGS)
				.setRegistryName(new ResourceLocation(MODID, "armor_pants"))
				.setUnlocalizedName("armor_pants")
				//.setTextureName(MODID + ":armor_pants")
				.setCreativeTab(CreativeTabs.COMBAT);
		ForgeRegistries.ITEMS.register(armorPants);

		armorBoots = new ItemCustomArmor(ArmorMaterial.IRON, EntityEquipmentSlot.FEET)
				.setRegistryName(new ResourceLocation(MODID, "armor_boots"))
				.setUnlocalizedName("armor_boots")
				//.setTextureName(MODID + ":armor_boots")
				.setCreativeTab(CreativeTabs.COMBAT);
		ForgeRegistries.ITEMS.register(armorBoots);

		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

}
