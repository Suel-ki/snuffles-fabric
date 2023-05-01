package com.github.suel_ki.snuffles.client.model;

import com.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import com.github.suel_ki.snuffles.core.config.SnufflesConfig;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;

public class SnuffleModel<T extends Snuffle> extends AnimalModel<T> {
    private final ModelPart body;
    private final ModelPart hump;
    private final ModelPart tongue;
    private final ModelPart horns;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;

    public SnuffleModel(ModelPart part) {
        this.body = part.getChild("body");
        this.hump = this.body.getChild("hump");
        this.tongue = this.body.getChild("tongue");
        this.horns = this.body.getChild("horns");
        this.rightFrontLeg = part.getChild("right_front_leg");
        this.leftFrontLeg = part.getChild("left_front_leg");
        this.rightHindLeg = part.getChild("right_hind_leg");
        this.leftHindLeg = part.getChild("left_hind_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 14.0F, 0.0F, -0.0436F, 0.0F, 0.0F));
        body.addChild("hump", ModelPartBuilder.create().uv(56, 30).cuboid(-9.0F, -8.0F, -6.0F, 18.0F, 2.0F, 13.0F), ModelTransform.NONE);
        body.addChild("torso", ModelPartBuilder.create().uv(0, 25).cuboid(-9.0F, -6.0F, -10.0F, 18.0F, 11.0F, 20.0F), ModelTransform.NONE);
        body.addChild("tongue", ModelPartBuilder.create().uv(80, 16).cuboid(-6.0F, 0.0F, -7.0F, 12.0F, 1.0F, 8.0F), ModelTransform.of(0.0F, 4.0F, -10.0F, 0.3927F, 0.0F, 0.0F));
        body.addChild("fluff", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, 5.0F, -10.0F, 18.0F, 5.0F, 20.0F), ModelTransform.NONE);
        ModelPartData horns = body.addChild("horns", ModelPartBuilder.create(),  ModelTransform.NONE);
        horns.addChild("right_horn", ModelPartBuilder.create().uv(102, 0).cuboid(-3.0F, -7.0F, -6.0F, 3.0F, 8.0F, 8.0F), ModelTransform.of(-9.0F, -4.0F, -6.0F, 0.0873F, 0.0F, 0.0F));
        horns.addChild("left_horn", ModelPartBuilder.create().uv(80, 0).cuboid(0.0F, -7.0F, -6.0F, 3.0F, 8.0F, 8.0F), ModelTransform.of(9.0F, -4.0F, -6.0F, 0.0873F, 0.0F, 0.0F));
        modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(-4.5F, 17.0F, -5.5F, 0.0F, 0.1309F, 0.0F));
        modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(4.5F, 17.0F, -5.5F, 0.0F, -0.1309F, 0.0F));
        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(-4.5F, 17.0F, 5.5F, 0.0F, 0.1309F, 0.0F));
        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(4.5F, 17.0F, 5.5F, 0.0F, -0.1309F, 0.0F));

        modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(-4.5F, 17.0F, -5.5F, 0.0F, 0.0873F, 0.0F));
        modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(4.5F, 17.0F, -5.5F, 0.0F, -0.0873F, 0.0F));
        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(-4.5F, 17.0F, 5.5F, 0.0F, 0.0873F, 0.0F));
        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F), ModelTransform.of(4.5F, 17.0F, 5.5F, 0.0F, -0.0873F, 0.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.rightFrontLeg, this.leftFrontLeg, this.rightHindLeg, this.leftHindLeg);
    }

    @Override
    public void animateModel(Snuffle snuffle, float limbSwing, float limbSwingAmount, float partialTick) {
        boolean flag = !snuffle.isBaby();
        this.horns.visible = flag;
        this.hump.visible = flag && !SnufflesConfig.INSTANCE.getConfig().legacy_snuffle_model;
    }

    @Override
    public void setAngles(Snuffle snuffle, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (snuffle.isFrosting()) {
            this.body.pitch = -0.0436F + 0.2F * MathHelper.sin(ageInTicks * 1.2F);
            this.body.roll = 0.12F * MathHelper.cos(ageInTicks * 0.6F);
        } else {
            this.body.pitch = -0.0436F + 0.2F * MathHelper.sin(limbSwing * 0.6F) * limbSwingAmount;
            this.body.roll = 0.12F * MathHelper.cos(limbSwing * 0.2F) * limbSwingAmount;
        }

        this.rightHindLeg.pitch = MathHelper.cos(limbSwing * 0.45F) * 1.5F * limbSwingAmount;
        this.leftHindLeg.pitch = MathHelper.cos(limbSwing * 0.45F + (float) Math.PI) * 1.5F * limbSwingAmount;
        this.rightHindLeg.roll = MathHelper.cos(limbSwing * 0.45F) * 0.15F * limbSwingAmount;
        this.leftHindLeg.roll = MathHelper.cos(limbSwing * 0.45F + (float) Math.PI) * 0.15F * limbSwingAmount;

        this.rightFrontLeg.pitch = this.leftHindLeg.pitch;
        this.leftFrontLeg.pitch = this.rightHindLeg.pitch;
        this.rightFrontLeg.roll = this.rightHindLeg.roll;
        this.leftFrontLeg.roll = this.leftHindLeg.roll;

        if (snuffle.isLicking() || snuffle.isFrosting()) {
            this.tongue.pitch = 0.3927F + MathHelper.sin(ageInTicks * 0.8F) * 0.2F;
        } else {
            this.tongue.pitch = 0.3927F + MathHelper.sin(ageInTicks * 0.12F) * 0.2F;
        }
    }
}