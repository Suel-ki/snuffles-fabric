package io.github.suel_ki.snuffles.client.model;

import io.github.suel_ki.snuffles.client.renderer.entity.state.SnuffleEntityRenderState;
import io.github.suel_ki.snuffles.core.config.SnufflesConfig;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SnuffleModel extends EntityModel<SnuffleEntityRenderState> {
    private final ModelPart body;
    private final ModelPart hump;
    private final ModelPart tongue;
    private final ModelPart horns;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;

    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.6F);

    public SnuffleModel(ModelPart part) {
        super(part);
        this.body = part.getChild("body");
        this.hump = this.body.getChild("hump");
        this.tongue = this.body.getChild("tongue");
        this.horns = this.body.getChild("horns");
        this.rightFrontLeg = part.getChild("right_front_leg");
        this.leftFrontLeg = part.getChild("left_front_leg");
        this.rightHindLeg = part.getChild("right_hind_leg");
        this.leftHindLeg = part.getChild("left_hind_leg");
    }

    public static LayerDefinition getTexturedModelData(boolean baby) {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.0436F, 0.0F, 0.0F));
        body.addOrReplaceChild("hump", CubeListBuilder.create().texOffs(56, 30).addBox(-9.0F, -8.0F, -6.0F, 18.0F, 2.0F, 13.0F), PartPose.ZERO);
        body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 25).addBox(-9.0F, -6.0F, -10.0F, 18.0F, 11.0F, 20.0F), PartPose.ZERO);
        body.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(80, 16).addBox(-6.0F, 0.0F, -7.0F, 12.0F, 1.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 4.0F, -10.0F, 0.3927F, 0.0F, 0.0F));
        body.addOrReplaceChild("fluff", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 5.0F, -10.0F, 18.0F, 5.0F, 20.0F), PartPose.ZERO);
        PartDefinition horns = body.addOrReplaceChild("horns", CubeListBuilder.create(),  PartPose.ZERO);
        horns.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(102, 0).addBox(-3.0F, -7.0F, -6.0F, 3.0F, 8.0F, 8.0F), PartPose.offsetAndRotation(-9.0F, -4.0F, -6.0F, 0.0873F, 0.0F, 0.0F));
        horns.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(80, 0).addBox(0.0F, -7.0F, -6.0F, 3.0F, 8.0F, 8.0F), PartPose.offsetAndRotation(9.0F, -4.0F, -6.0F, 0.0873F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(-4.5F, 17.0F, -5.5F, 0.0F, 0.1309F, 0.0F));
        modelPartData.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(4.5F, 17.0F, -5.5F, 0.0F, -0.1309F, 0.0F));
        modelPartData.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(-4.5F, 17.0F, 5.5F, 0.0F, 0.1309F, 0.0F));
        modelPartData.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(4.5F, 17.0F, 5.5F, 0.0F, -0.1309F, 0.0F));

        modelPartData.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(-4.5F, 17.0F, -5.5F, 0.0F, 0.0873F, 0.0F));
        modelPartData.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(4.5F, 17.0F, -5.5F, 0.0F, -0.0873F, 0.0F));
        modelPartData.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(-4.5F, 17.0F, 5.5F, 0.0F, 0.0873F, 0.0F));
        modelPartData.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(4.5F, 17.0F, 5.5F, 0.0F, -0.0873F, 0.0F));
        LayerDefinition texturedModelData = LayerDefinition.create(modelData, 128, 64);
        return baby? texturedModelData.apply(BABY_TRANSFORMER) : texturedModelData;
    }

    public void setupAnim(SnuffleEntityRenderState state) {
        super.setupAnim(state);
        float age = state.ageInTicks;
        float limbSwing = state.walkAnimationPos;
        float limbSwingAmount = state.walkAnimationSpeed;
        boolean flag = !state.isBaby;
        this.horns.visible = flag;
        this.hump.visible = flag && !SnufflesConfig.instance().legacy_snuffle_model;
        if (state.frosting) {
            this.body.xRot = -0.0436F + 0.2F * Mth.sin(age * 1.2F);
            this.body.zRot = 0.12F * Mth.cos(age * 0.6F);
        } else {
            this.body.xRot = -0.0436F + 0.2F * Mth.sin(limbSwing * 0.6F) * limbSwingAmount;
            this.body.zRot = 0.12F * Mth.cos(limbSwing * 0.2F) * limbSwingAmount;
        }

        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.45F) * 1.5F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.45F + (float) Math.PI) * 1.5F * limbSwingAmount;
        this.rightHindLeg.zRot = Mth.cos(limbSwing * 0.45F) * 0.15F * limbSwingAmount;
        this.leftHindLeg.zRot = Mth.cos(limbSwing * 0.45F + (float) Math.PI) * 0.15F * limbSwingAmount;

        this.rightFrontLeg.xRot = this.leftHindLeg.xRot;
        this.leftFrontLeg.xRot = this.rightHindLeg.xRot;
        this.rightFrontLeg.zRot = this.rightHindLeg.zRot;
        this.leftFrontLeg.zRot = this.leftHindLeg.zRot;

        if (state.licking || state.frosting) {
            this.tongue.xRot = 0.3927F + Mth.sin(age * 0.8F) * 0.2F;
        } else {
            this.tongue.xRot = 0.3927F + Mth.sin(age * 0.12F) * 0.2F;
        }
    }
}