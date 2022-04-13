package timaxa007.very_custom_armor.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ArmorCustomModel extends ModelBiped {

	public int color = -1;//Раскраска брони в цвет.

	public abstract void pre();//До всех частей.
	public abstract void post();//После всех частей.
	public abstract void partHead();//Часть: Голова.
	public abstract void partBody();//Часть: Тело.
	public abstract void partRightArm();//Часть: Правая рука.
	public abstract void partLeftArm();//Часть: Левая рука.
	public abstract void partRightLeg();//Часть: Правая нога.
	public abstract void partLeftLeg();//Часть: Левая нога.

	public ArmorCustomModel() {
		super();
		setVisible(false);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		RenderHelper.disableStandardItemLighting();
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		if (entityIn instanceof EntityLivingBase) render((EntityLivingBase)entityIn, scale);
		RenderHelper.enableStandardItemLighting();
	}

	public void render(EntityLivingBase entity, float scale) {

		GlStateManager.pushMatrix();

		if (color != -1) {
			float red = (float)(color >> 16 & 255) / 255F;
			float blue = (float)(color >> 8 & 255) / 255F;
			float green = (float)(color & 255) / 255F;
			GlStateManager.color(red, blue, green);
		}

		pre();

		//partHead
		GlStateManager.pushMatrix();

		if (entity.isChild()) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
		} else if (entity.isSneaking())
			GlStateManager.translate(0.0F, 0.2F, 0.0F);

		blank(super.bipedHead, scale);
		partHead();
		GlStateManager.popMatrix();

		if (entity.isChild()) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
		} else if (entity.isSneaking())
			GlStateManager.translate(0.0F, 0.2F, 0.0F);

		//partBody
		GlStateManager.pushMatrix();
		blank(super.bipedBody, scale);
		partBody();
		GlStateManager.popMatrix();

		//partRightArm
		GlStateManager.pushMatrix();
		blank(super.bipedRightArm, scale);
		partRightArm();
		GlStateManager.popMatrix();

		//partLeftArm
		GlStateManager.pushMatrix();
		blank(super.bipedLeftArm, scale);
		partLeftArm();
		GlStateManager.popMatrix();

		//partRightLeg
		GlStateManager.pushMatrix();
		blank(super.bipedRightLeg, scale);
		partRightLeg();
		GlStateManager.popMatrix();

		//partLeftLeg
		GlStateManager.pushMatrix();
		blank(super.bipedLeftLeg, scale);
		partLeftLeg();
		GlStateManager.popMatrix();

		if (entity.isChild())
			GlStateManager.popMatrix();

		post();

		GlStateManager.color(1F, 1F, 1F);

		GlStateManager.popMatrix();

	}

	private void blank(ModelRenderer model, float parTicks) {
		GlStateManager.translate(model.offsetX, model.offsetY, model.offsetZ);
		if (model.rotationPointX != 0.0F || model.rotationPointY != 0.0F || model.rotationPointZ != 0.0F)
			GlStateManager.translate(model.rotationPointX * parTicks, model.rotationPointY * parTicks, model.rotationPointZ * parTicks);
		if (model.rotateAngleZ != 0F) GlStateManager.rotate(model.rotateAngleZ * (180F / (float)Math.PI), 0F, 0F, 1F);
		if (model.rotateAngleY != 0F) GlStateManager.rotate(model.rotateAngleY * (180F / (float)Math.PI), 0F, 1F, 0F);
		if (model.rotateAngleX != 0F) GlStateManager.rotate(model.rotateAngleX * (180F / (float)Math.PI), 1F, 0F, 0F);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		//GlStateManager.translate(-model.offsetX, -model.offsetY, -model.offsetZ);
	}

}

