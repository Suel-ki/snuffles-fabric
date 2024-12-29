package io.github.suel_ki.snuffles.common.entity.animal;

import io.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import io.github.suel_ki.snuffles.core.registry.SnufflesEntityTypes;
import io.github.suel_ki.snuffles.core.registry.SnufflesParticleTypes;
import io.github.suel_ki.snuffles.core.registry.SnufflesSoundEvents;
import io.github.suel_ki.snuffles.core.tags.SnufflesBlockTags;
import io.github.suel_ki.snuffles.core.tags.SnufflesItemTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class Snuffle extends AnimalEntity implements Shearable {

    private static final TrackedData<Integer> FROST_COUNTER = DataTracker.registerData(Snuffle.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> DATA_HAIRSTYLE_ID = DataTracker.registerData(Snuffle.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> DATA_FLUFF = DataTracker.registerData(Snuffle.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DATA_FROSTY = DataTracker.registerData(Snuffle.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_LICKING = DataTracker.registerData(Snuffle.class, TrackedDataHandlerRegistry.BOOLEAN);

    private int fluffGrowTime = 18000 + this.getRandom().nextInt(6000);

    public Snuffle(EntityType<Snuffle> snuffle, World world) {
        super(snuffle, world);
    }

    public static DefaultAttributeContainer.Builder createSnuffleAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new SnuffleClimbOnTopOfPowderSnowGoal(this, this.getWorld()));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new SnuffleTemptGoal(1.1D, Ingredient.fromTag(Registries.ITEM.getOptional(SnufflesItemTags.SNUFFLE_FOOD).orElseThrow()), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(5, new FrostGoal());
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0D, 0.6F * this.getStandingEyeHeight(), this.getWidth() * 0.4F);
    }

    /*
     * Data Methods
     */

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(FROST_COUNTER, 0);
        builder.add(DATA_HAIRSTYLE_ID, 0);
        builder.add(DATA_FLUFF, false);
        builder.add(DATA_FROSTY, false);
        builder.add(IS_LICKING, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        compound.putInt("Hairstyle", this.getHairstyleId());
        compound.putBoolean("HasFluff", this.hasFluff());
        compound.putBoolean("Frosty", this.isFrosty());
        compound.putInt("FluffGrowTime", this.fluffGrowTime);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setHairstyleId(compound.getInt("Hairstyle"));
        this.setFluff(compound.getBoolean("HasFluff"));
        this.setFrosty(compound.getBoolean("Frosty"));
        this.fluffGrowTime = compound.getInt("FluffGrowTime");
    }

    public void setFrostCounter(int counter) {
        this.dataTracker.set(FROST_COUNTER, counter);
    }

    public int getFrostCounter() {
        return this.dataTracker.get(FROST_COUNTER);
    }

    public boolean isFrosting() {
        return this.getFrostCounter() > 0;
    }

    public void setHairstyleId(int id) {
        this.dataTracker.set(DATA_HAIRSTYLE_ID, id);
    }

    public int getHairstyleId() {
        return MathHelper.clamp(this.dataTracker.get(DATA_HAIRSTYLE_ID), 0, 3);
    }

    public Hairstyle getHairstyle() {
        return Hairstyle.getHairstyleById(this.getHairstyleId());
    }

    public void setFluff(boolean hasFluff) {
        this.dataTracker.set(DATA_FLUFF, hasFluff);
    }

    public boolean hasFluff() {
        return this.dataTracker.get(DATA_FLUFF);
    }

    public void setFrosty(boolean isFrosty) {
        this.dataTracker.set(DATA_FROSTY, isFrosty);
    }

    public boolean isFrosty() {
        return this.dataTracker.get(DATA_FROSTY);
    }

    public void setLicking(boolean isLicking) {
        this.dataTracker.set(IS_LICKING, isLicking);
    }

    public boolean isLicking() {
        return this.dataTracker.get(IS_LICKING);
    }

    /*
     * Frost & Fluff Methods
     */

    @Override
    public void tick() {
        super.tick();

        if (this.isFrosty()) {
            if (this.isOnFire()) {
                this.extinguish();
                this.setFrosty(false);
                this.playExtinguishSound();
            }

            if ((this.lastRenderX != this.getX() || this.lastRenderY != this.getY() || this.lastRenderZ != this.getZ()) && this.getRandom().nextBoolean())
                this.getWorld().addParticle(SnufflesParticleTypes.SNOWFLAKE, this.getParticleX(0.4D), this.getRandomBodyY(), this.getParticleZ(0.4D), 0.0D, 0.0D, 0.0D);
        }

        if (!this.hasFluff() && !this.isBaby()) {
            if (this.fluffGrowTime > 0)
                --this.fluffGrowTime;
            else
                this.setFluff(true);
        }
    }

    private boolean isSnowingAt(World world, BlockPos pos) {
        if (!getWorld().isRaining())
            return false;
        else if (!getWorld().isSkyVisible(pos))
            return false;
        else if (getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos).getY() > pos.getY())
            return false;
        else
            return getWorld().getBiome(pos).value().isCold(pos, world.getSeaLevel());
    }

    /*
     * Interaction Methods
     */

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.SLIME_BALL)) {
            if (!this.getWorld().isClient()) {
                this.eat(player, hand, stack);
                this.setHairstyleId((this.getHairstyleId() + 1) % 4);
                this.playSound(SnufflesSoundEvents.SNUFFLE_STYLE, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }

            return ActionResult.SUCCESS;
        } else if (stack.isOf(Items.MAGMA_CREAM) && this.isFrosty()) {
            if (!this.getWorld().isClient()) {
                this.setFrosty(false);
                this.eat(player, hand, stack);
                this.playSound(SnufflesSoundEvents.SNUFFLE_THAW, 0.7F, 1.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
            }

            return ActionResult.SUCCESS;
        } else  if (stack.isIn(ConventionalItemTags.SHEAR_TOOLS)) {
            if (!this.getWorld().isClient() && this.isShearable()) {
                this.sheared((ServerWorld) getWorld(), SoundCategory.PLAYERS, stack);
                this.emitGameEvent(GameEvent.SHEAR, player);
                stack.damage(1, player, getSlotForHand(hand));

                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }

        return super.interactMob(player, hand);
    }

    @Override
    protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
        if (this.isBreedingItem(stack))
            this.playSound(SnufflesSoundEvents.SNUFFLE_EAT, 1.0F, 1.0F);

        super.eat(player, hand, stack);
    }

    /*
     * Sound Methods
     */

    @Override
    protected SoundEvent getAmbientSound() {
        return SnufflesSoundEvents.SNUFFLE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SnufflesSoundEvents.SNUFFLE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SnufflesSoundEvents.SNUFFLE_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SnufflesSoundEvents.SNUFFLE_STEP, 0.15F, 1.0F);
    }

    /*
     * Client Method
     */

    @Override
    public void handleStatus(byte id) {
        if (id == 10) {
            for (int i = 0; i < 4; i ++)
                this.getWorld().addParticle(SnufflesParticleTypes.SNOWFLAKE, this.getParticleX(0.8D), this.getEyeY(), this.getParticleZ(0.8D), 0.0D, 0.1D, 0.0D);
        } else
            super.handleStatus(id);
    }

    /*
     * Shearing Methods
     */

    @Override
    public void sheared(ServerWorld world, SoundCategory shearedSoundCategory, ItemStack shears) {
        if (this.getWorld().isClient())
            return;
        this.getWorld().playSoundFromEntity(null, this, SnufflesSoundEvents.SNUFFLE_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setFluff(false);
        this.fluffGrowTime = 18000 + this.getRandom().nextInt(6000);
        ItemEntity itemEntity = this.dropStack((ServerWorld) this.getWorld(), new ItemStack(this.isFrosty() ? SnufflesBlocks.FROSTY_FLUFF : SnufflesBlocks.SNUFFLE_FLUFF), 1.0F);
        if (itemEntity != null) {
            itemEntity.setVelocity(itemEntity.getVelocity()
                    .add((
                                    this.random.nextFloat() - this.random.nextFloat()) * 0.1f,
                            this.random.nextFloat() * 0.05f,
                            (this.random.nextFloat() - this.random.nextFloat()) * 0.1f));
        }
    }

    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby() && this.hasFluff();
    }

    public static boolean checkSnuffleSpawnRules(EntityType<Snuffle> snuffle, WorldAccess world, SpawnReason spawnType, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(SnufflesBlockTags.SNUFFLES_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnType, @Nullable EntityData groupData) {
        boolean frosty = getWorld().getBiome(this.getBlockPos()).value().isCold(this.getBlockPos(), world.getSeaLevel());

        if (groupData instanceof SnuffleGroupData)
            frosty = ((SnuffleGroupData) groupData).frosty;
        else
            groupData = new SnuffleGroupData(frosty);

        SnuffleGroupData snuffleGroupData = (SnuffleGroupData) groupData;
        if (snuffleGroupData.getSpawnedCount() > 0 && this.random.nextFloat() <= snuffleGroupData.getBabyChance())
            this.setBreedingAge(-24000);
        else
            this.setFluff(true);

        this.setFrosty(frosty);
        return super.initialize(world, difficulty, spawnType, groupData);
    }

    /*
     * Breeding Methods
     */

    @Nullable
    @Override
    public Snuffle createChild(ServerWorld world, PassiveEntity entity) {
        return SnufflesEntityTypes.SNUFFLE.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(SnufflesItemTags.SNUFFLE_FOOD);
    }

    /*
     * Death Method
     */

    @Override
    public void onDeath(DamageSource source) {
        this.setFrostCounter(0);
        super.onDeath(source);
    }

    /*
     * Data
     */

    public static class SnuffleGroupData extends PassiveData {
        public final boolean frosty;

        public SnuffleGroupData(boolean frosty) {
            super(false);
            this.frosty = frosty;
        }
    }

    /*
     * Hairstyle Enum
     */

    public enum Hairstyle {
        DEFAULT,
        SHEEPDOG,
        PORO,
        HORSESHOE;

        private static final Hairstyle[] HAIRSTYLES = Hairstyle.values();

        public static Hairstyle getHairstyleById(int id) {
            return HAIRSTYLES[id];
        }
    }

    /*
     * AI Goals
     */

    class SnuffleClimbOnTopOfPowderSnowGoal extends PowderSnowJumpGoal {
        public SnuffleClimbOnTopOfPowderSnowGoal(MobEntity mob, World world) {
            super(mob, world);
        }

        @Override
        public boolean canStart() {
            boolean flag = Snuffle.this.wasInPowderSnow || Snuffle.this.inPowderSnow;
            if (flag && Snuffle.this.getType().isIn(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
                BlockPos blockpos = Snuffle.this.getBlockPos().up();
                BlockState blockstate = Snuffle.this.getWorld().getBlockState(blockpos);
                return blockstate.isOf(Blocks.POWDER_SNOW) || blockstate.getCollisionShape(Snuffle.this.getWorld(), blockpos) == VoxelShapes.empty() && Snuffle.this.isFrosty();
            } else {
                return false;
            }
        }
    }

    class SnuffleTemptGoal extends TemptGoal {
        public SnuffleTemptGoal(double speedModifier, Ingredient items, boolean canScare) {
            super(Snuffle.this, speedModifier, items, canScare);
        }

        public void start() {
            super.start();
            Snuffle.this.setLicking(true);
        }

        public void stop() {
            super.stop();
            Snuffle.this.setLicking(false);
        }
    }

    class FrostGoal extends Goal {
        private static final int WAIT_TIME_BEFORE_FROST = toGoalTicks(140);
        private int countdown = Snuffle.this.random.nextInt(WAIT_TIME_BEFORE_FROST);

        public FrostGoal() {
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
        }

        @Override
        public boolean canStart() {
            return Snuffle.this.sidewaysSpeed == 0.0F && Snuffle.this.upwardSpeed == 0.0F && Snuffle.this.forwardSpeed == 0.0F && this.canFrost();
        }

        @Override
        public boolean shouldContinue() {
            return Snuffle.this.isFrosting() && this.canFrost();
        }

        private boolean canFrost() {
            if (this.countdown > 0) {
                --this.countdown;
                return false;
            } else {
                World world = Snuffle.this.getWorld();
                BlockPos pos = Snuffle.this.getBlockPos();
                return !Snuffle.this.isFrosty() && (Snuffle.this.isSnowingAt(world, pos) || getWorld().getBlockState(pos).isOf(Blocks.POWDER_SNOW));
            }
        }

        @Override
        public void tick() {
            Snuffle.this.setFrostCounter(Math.max(0, Snuffle.this.getFrostCounter() - 1));

            if (Snuffle.this.getFrostCounter() % this.getTickCount(4) == 0) {
                Snuffle.this.playSound(SnufflesSoundEvents.SNUFFLE_SHAKE, 1.0F, (Snuffle.this.random.nextFloat() - Snuffle.this.random.nextFloat()) * 0.2F + 1.0F);
                Snuffle.this.setFrosty(Snuffle.this.getFrostCounter() == this.getTickCount(4));
                Snuffle.this.getWorld().sendEntityStatus(Snuffle.this, (byte) 10);
            }
        }

        @Override
        public void start() {
            Snuffle.this.setFrostCounter(this.getTickCount(40));
            Snuffle.this.emitGameEvent(GameEvent.ENTITY_ACTION);
            Snuffle.this.getNavigation().stop();
        }

        @Override
        public void stop() {
            Snuffle.this.setFrostCounter(0);
            this.countdown = Snuffle.this.random.nextInt(WAIT_TIME_BEFORE_FROST);
        }
    }
}