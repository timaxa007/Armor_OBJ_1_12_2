package timaxa007.very_custom_armor.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import timaxa007.very_custom_armor.MyMod;
import timaxa007.very_custom_armor.ProxyCommon;

public class ProxyClient extends ProxyCommon {

	public static int[] displayList = new int[10];//for 10 parts model

	public void preInit() {
		super.preInit();
		OBJLoader.INSTANCE.addDomain(MyMod.MODID);
	}

	@SuppressWarnings("deprecation")
	public void init() {

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MyMod.armorHelmet, 0,
				new ModelResourceLocation(MyMod.armorHelmet.getRegistryName(), "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MyMod.armorVest, 0,
				new ModelResourceLocation(MyMod.armorVest.getRegistryName(), "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MyMod.armorPants, 0,
				new ModelResourceLocation(MyMod.armorPants.getRegistryName(), "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MyMod.armorBoots, 0,
				new ModelResourceLocation(MyMod.armorBoots.getRegistryName(), "inventory"));

		try {

			OBJModel model = (OBJModel)OBJLoader.INSTANCE.loadModel(new ResourceLocation(MyMod.MODID, "models/armor/armor_plane.obj"));

			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder buffer = tessellator.getBuffer();

			final String[] partsName = new String[] {
					"helm",			//1
					"glass",		//2
					"body",			//3
					"plane",		//4
					"right_arm",	//5
					"left_arm",		//6
					"right_leg",	//7
					"left_leg",		//8
					"right_boot",	//9
					"left_boot"		//10
			};
			for (int i = 0; i < displayList.length; ++i) {
				displayList[i] = GLAllocation.generateDisplayLists(1);
				GlStateManager.glNewList(displayList[i], GL11.GL_COMPILE);

				OBJModel.Group g = model.getMatLib().getGroups().get(partsName[i]);

				//for (OBJModel.Group g : model.getMatLib().getGroups().values()) {
				for (OBJModel.Face f : g.getFaces()) {
					buffer.begin(f.isTriangles() ? GL11.GL_TRIANGLES : GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

					for (int s = 0; s < f.getVertices().length; ++s) {
						OBJModel.Vertex v = f.getVertices()[s];
						buffer.pos(v.getPos().x, v.getPos().y, v.getPos().z).
						tex(v.getTextureCoordinate().u, v.getTextureCoordinate().v).
						//normal(v.getNormal().x, v.getNormal().y, v.getNormal().z).
						endVertex();
					}

					tessellator.draw();
				}

				GlStateManager.glEndList();
			}

		}
		catch (Exception e) {e.printStackTrace();}

	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		//for (Item item : itemList) ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));


	}

	private static final ArmorCustomModel
	acm_head = new ArmorCustomModelTest(EntityEquipmentSlot.HEAD),
	acm_chest = new ArmorCustomModelTest(EntityEquipmentSlot.CHEST),
	acm_legs = new ArmorCustomModelTest(EntityEquipmentSlot.LEGS),
	acm_feet = new ArmorCustomModelTest(EntityEquipmentSlot.FEET);

	public static ArmorCustomModel getModel(EntityLivingBase entityPlayer, ItemStack stack) {
		if (stack.getItem() == MyMod.armorHelmet) return acm_head;
		else if (stack.getItem() == MyMod.armorVest) return acm_chest;
		else if (stack.getItem() == MyMod.armorPants) return acm_legs;
		else if (stack.getItem() == MyMod.armorBoots) return acm_feet;
		else return null;
	}

}
