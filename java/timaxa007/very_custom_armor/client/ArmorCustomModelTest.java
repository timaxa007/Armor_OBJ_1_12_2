package timaxa007.very_custom_armor.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ArmorCustomModelTest extends ArmorCustomModel {

	public static final ResourceLocation
	texture_glass = new ResourceLocation("textures/blocks/glass_white.png"),
	texture_wood = new ResourceLocation("textures/blocks/planks_oak.png");
	private final EntityEquipmentSlot partType;

	public ArmorCustomModelTest(EntityEquipmentSlot armorType) {
		super();
		partType = armorType;
	}

	@Override
	public void pre() {
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(
				GlStateManager.SourceFactor.SRC_ALPHA, 
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, 
				GlStateManager.SourceFactor.ONE, 
				GlStateManager.DestFactor.ZERO);
		//GlStateManager.rotate(180, 1, 0, 0);
		//GlStateManager.translate(0F, -1.5F, 0F);
		//Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
	}

	@Override
	public void post() {
		GlStateManager.disableBlend();
	}

	@Override
	public void partHead() {
		switch(partType) {
		case HEAD:
			GlStateManager.translate(0F, -1.5F, 0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture_wood);
			GlStateManager.callList(ProxyClient.displayList[0]);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture_glass);
			GlStateManager.callList(ProxyClient.displayList[1]);
			break;
		default:break;
		}
	}

	@Override
	public void partBody() {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture_wood);
		switch(partType) {
		case CHEST:
			GlStateManager.translate(0F, -1.5F, 0F);
			GlStateManager.callList(ProxyClient.displayList[2]);
			GlStateManager.callList(ProxyClient.displayList[3]);
			break;
		default:break;
		}
	}

	@Override
	public void partRightArm() {
		switch(partType) {
		case CHEST:
			GlStateManager.translate(0.3125F, -1.375F, 0F);
			GlStateManager.callList(ProxyClient.displayList[4]);
			break;
		default:break;
		}
	}

	@Override
	public void partLeftArm() {
		switch(partType) {
		case CHEST:
			GlStateManager.translate(-0.3125F, -1.375F, 0F);
			GlStateManager.callList(ProxyClient.displayList[5]);
			break;
		default:break;
		}
	}

	@Override
	public void partRightLeg() {
		GlStateManager.translate(0.125F, -0.75F, 0F);
		switch(partType) {
		case LEGS:
			GlStateManager.callList(ProxyClient.displayList[6]);
			break;
		case FEET:
			GlStateManager.callList(ProxyClient.displayList[8]);
			break;
		default:break;
		}
	}

	@Override
	public void partLeftLeg() {
		GlStateManager.translate(-0.125F, -0.75F, 0F);
		switch(partType) {
		case LEGS:
			GlStateManager.callList(ProxyClient.displayList[7]);
			break;
		case FEET:
			GlStateManager.callList(ProxyClient.displayList[9]);
			break;
		default:break;
		}
	}

}
