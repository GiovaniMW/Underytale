package net.mcreator.underytaleneo.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelCustomModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("underytaleneo", "model_custom_model"),
			"main");
	public final ModelPart bone;

	public ModelCustomModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone = partdefinition.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(6, 3).addBox(0.0F, -1.5F, -2.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.5F)).texOffs(1, 4)
						.addBox(-0.5F, -2.0F, -2.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.1F)).texOffs(5, 5)
						.addBox(0.0F, -1.5F, -2.3F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.4F)).texOffs(0, 4)
						.addBox(-0.5F, -2.0F, -1.4F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.1F)).texOffs(12, 4)
						.addBox(-0.5F, -2.0F, -1.6F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.1F)).texOffs(0, 4)
						.addBox(-0.5F, -2.0F, -2.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.1F)).texOffs(2, 4).addBox(-0.5F, -2.0F, -1.8F, 1.0F,
								1.0F, 0.0F, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(2.0F, 25.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}
