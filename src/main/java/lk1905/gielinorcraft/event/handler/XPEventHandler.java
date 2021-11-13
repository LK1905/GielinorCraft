package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.capability.skill.Skills;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class XPEventHandler {

	@SubscribeEvent
	public static void entityDamage(LivingDamageEvent event) {
		if(!(event.getSource().getDirectEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getSource().getDirectEntity();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		IAttackStyle style = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		int activeStyle = style.getActiveStyle();
		
		if(activeStyle == 1 || activeStyle == 2 || activeStyle == 3) {
			skills.addXp(0, event.getAmount() * 4);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 4 || activeStyle == 5 || activeStyle == 6) {
			skills.addXp(2, event.getAmount() * 4);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 7 || activeStyle == 8 || activeStyle == 9) {
			skills.addXp(0, event.getAmount() * (1 + 1/3));
			skills.addXp(1, event.getAmount() * (1 + 1/3));
			skills.addXp(2, event.getAmount() * (1 + 1/3));
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 10 || activeStyle == 11 || activeStyle == 12) {
			skills.addXp(1, event.getAmount() * 4);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 13 || activeStyle == 14) {
			skills.addXp(4, event.getAmount() * 4);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 15) {
			skills.addXp(1, event.getAmount() * 2);
			skills.addXp(4, event.getAmount() * 2);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 16) {
			skills.addXp(6, event.getAmount() * 4);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}else if(activeStyle == 17) {
			skills.addXp(1, event.getAmount() * 2);
			skills.addXp(6, event.getAmount() * 2);
			skills.addXp(3, event.getAmount() * (1 + 1/3));
			skills.sync((ServerPlayer) player);
		}
	}
	
	@SubscribeEvent
	public static void onDiggingBreakEvent(BlockEvent.BreakEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = diggingXpForBlock(event.getState().getBlock());
		
		skills.addXp(24, xpGained);
		skills.sync((ServerPlayer) player);
	}
	
	@SubscribeEvent
	public static void onMiningBreakEvent(BlockEvent.BreakEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = miningXpForBlock(event.getState().getBlock());
		
		skills.addXp(14, xpGained);
		skills.sync((ServerPlayer) player);
	}
	
	@SubscribeEvent
	public static void onWoodcuttingBreakEvent(BlockEvent.BreakEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = woodcuttingXpForBlock(event.getState().getBlock());
		
		skills.addXp(8, xpGained);
		skills.sync((ServerPlayer) player);
	}
	
	@SubscribeEvent
	public static void onFarmingBreakEvent(BlockEvent.BreakEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = farmingXpForBlock(event.getState().getBlock());
		
		skills.addXp(19, xpGained);
		skills.sync((ServerPlayer) player);
	}
	
	@SubscribeEvent
	public static void onDiggingToolInteractEvent(BlockEvent.BlockToolInteractEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = diggingXpForToolInteract(event.getState().getBlock());
		
		if(!player.level.isClientSide) {
			if(event.getToolAction() == ToolActions.SHOVEL_FLATTEN) {
				skills.addXp(24, xpGained);
				skills.sync((ServerPlayer) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onWoodcuttingToolInteractEvent(BlockEvent.BlockToolInteractEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = woodcuttingXpForToolInteract(event.getState().getBlock());
		
		if(!player.level.isClientSide) {
			if(event.getToolAction() == ToolActions.AXE_STRIP) {
				skills.addXp(8, xpGained);
				skills.sync((ServerPlayer) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onFarmingToolInteractEvent(BlockEvent.BlockToolInteractEvent event) {
		
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = farmingXpForToolInteract(event.getState().getBlock());
		
		if(!player.level.isClientSide) {
			if(event.getToolAction() == ToolActions.DEFAULT_HOE_ACTIONS) {
				skills.addXp(19, xpGained);
				skills.sync((ServerPlayer) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onSmeltingEvent(ItemSmeltedEvent event) {
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		double xpGained = 0;
		Item smeltedItem = event.getSmelting().getItem();
		
		if(smeltedItem == Items.STONE) {
			xpGained = 1.0;
		}else if(smeltedItem == Items.COAL || smeltedItem == Items.CHARCOAL) {
			xpGained = 8.0;
		}else if(smeltedItem == Items.IRON_INGOT) {
			xpGained = 12.5;
		}else if(smeltedItem == Items.QUARTZ) {
			xpGained = 17.5;
		}else if(smeltedItem == Items.GOLD_INGOT) {
			xpGained = 22.5;
		}else if(smeltedItem == Items.REDSTONE) {
			xpGained = 20.0;
		}else if(smeltedItem == Items.LAPIS_LAZULI) {
			xpGained = 30.0;;
		}else if(smeltedItem == Items.EMERALD) {
			xpGained = 37.5;
		}else if(smeltedItem == Items.DIAMOND) {
			xpGained = 50.0;
		}
		
		if(!player.level.isClientSide) {
			skills.addXp(Skills.SMITHING, xpGained * event.getSmelting().getCount());
			skills.sync((ServerPlayer) player);
		}
	}
	
	private static double diggingXpForBlock(Block block) {
		
		if(block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH || block == Blocks.SAND
				|| block == Blocks.COARSE_DIRT|| block == Blocks.MYCELIUM || block == Blocks.RED_SAND || block == Blocks.FARMLAND) {
			return 10;
		}else if(block == Blocks.GRAVEL) {
			return 20;
		}else if(block == Blocks.CRIMSON_NYLIUM || block == Blocks.WARPED_NYLIUM) {
			return 30;
		}else if(block == Blocks.CLAY) {
			return 40;
		}else if(block == Blocks.SOUL_SAND || block == Blocks.SOUL_SOIL) {
			return 50;
		}else {
			return 0;
		}
	}
		
	private static double miningXpForBlock(Block block) {
		
		if(block == Blocks.SANDSTONE || block == Blocks.SANDSTONE_SLAB || block == Blocks.SANDSTONE_STAIRS || block == Blocks.SANDSTONE_WALL
				|| block == Blocks.STONE || block == Blocks.STONE_BRICK_SLAB || block == Blocks.STONE_BRICK_STAIRS || block == Blocks.STONE_BRICK_WALL
				|| block == Blocks.STONE_BRICKS || block == Blocks.STONE_BUTTON || block == Blocks.STONE_PRESSURE_PLATE || block == Blocks.STONE_SLAB
				|| block == Blocks.STONE_STAIRS || block == Blocks.STONECUTTER || block == Blocks.COBBLESTONE || block == Blocks.COBBLESTONE_SLAB
				|| block == Blocks.COBBLESTONE_STAIRS || block == Blocks.COBBLESTONE_WALL || block == Blocks.RED_SANDSTONE
				|| block == Blocks.RED_SANDSTONE_SLAB || block == Blocks.RED_SANDSTONE_STAIRS || block == Blocks.RED_SANDSTONE_WALL
				|| block == Blocks.END_STONE || block == Blocks.END_STONE_BRICK_SLAB || block == Blocks.END_STONE_BRICK_STAIRS
				|| block == Blocks.END_STONE_BRICK_WALL || block == Blocks.END_STONE_BRICKS || block == Blocks.NETHERRACK
				|| block == Blocks.CRACKED_STONE_BRICKS || block == Blocks.INFESTED_CHISELED_STONE_BRICKS || block == Blocks.INFESTED_CRACKED_STONE_BRICKS
				|| block == Blocks.INFESTED_COBBLESTONE || block == Blocks.INFESTED_MOSSY_STONE_BRICKS || block == Blocks.INFESTED_STONE || block == Blocks.INFESTED_STONE_BRICKS) {
			return 5;
		}else if(block == Blocks.ANDESITE || block == Blocks.ANDESITE_SLAB || block == Blocks.ANDESITE_STAIRS || block == Blocks.ANDESITE_WALL
				|| block == Blocks.POLISHED_ANDESITE || block == Blocks.POLISHED_ANDESITE_SLAB || block == Blocks.POLISHED_ANDESITE_STAIRS
				|| block == Blocks.DIORITE || block == Blocks.DIORITE_SLAB || block == Blocks.DIORITE_STAIRS || block == Blocks.DIORITE_WALL
				|| block == Blocks.POLISHED_DIORITE || block == Blocks.POLISHED_DIORITE_SLAB || block == Blocks.POLISHED_DIORITE_STAIRS
				|| block == Blocks.GRANITE || block == Blocks.GRANITE_SLAB || block == Blocks.GRANITE_STAIRS || block == Blocks.GRANITE_WALL
				|| block == Blocks.POLISHED_GRANITE || block == Blocks.POLISHED_GRANITE_SLAB || block == Blocks.POLISHED_GRANITE_STAIRS) {
			return 10;
		}else if(block == Blocks.NETHER_BRICK_FENCE || block == Blocks.NETHER_BRICK_SLAB || block == Blocks.NETHER_BRICK_STAIRS
				|| block == Blocks.NETHER_BRICK_WALL || block == Blocks.NETHER_BRICKS || block == Blocks.RED_NETHER_BRICKS
				|| block == Blocks.RED_NETHER_BRICK_SLAB || block == Blocks.RED_NETHER_BRICK_STAIRS || block == Blocks.RED_NETHER_BRICK_WALL) {
			return 15;
		}else if(block == Blocks.COAL_BLOCK || block == Blocks.COAL_ORE) {
			return 20;
		}else if(block == Blocks.BLACKSTONE || block == Blocks.BLACKSTONE_SLAB || block == Blocks.BLACKSTONE_STAIRS || block == Blocks.BLACKSTONE_WALL
				|| block == Blocks.CHISELED_POLISHED_BLACKSTONE || block == Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS || block == Blocks.GILDED_BLACKSTONE
				|| block == Blocks.POLISHED_BLACKSTONE || block == Blocks.POLISHED_BLACKSTONE_BRICKS
				|| block == Blocks.POLISHED_BLACKSTONE_BRICK_SLAB || block == Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS || block == Blocks.POLISHED_BLACKSTONE_BRICK_WALL) {
			return 25;
		}else if(block == Blocks.IRON_BARS || block == Blocks.IRON_BLOCK || block == Blocks.IRON_DOOR || block == Blocks.IRON_ORE || block == Blocks.IRON_TRAPDOOR) {
			return 35;
		}else if(block == Blocks.LAPIS_BLOCK || block == Blocks.LAPIS_ORE) {
			return 40;
		}else if(block == Blocks.REDSTONE_BLOCK || block == Blocks.REDSTONE_ORE) {
			return 50;
		}else if(block == Blocks.GOLD_BLOCK || block == Blocks.GOLD_ORE || block == Blocks.NETHER_GOLD_ORE) {
			return 65;
		}else if(block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.CHISELED_QUARTZ_BLOCK || block == Blocks.QUARTZ_BLOCK
				|| block == Blocks.QUARTZ_BRICKS || block == Blocks.QUARTZ_PILLAR || block == Blocks.QUARTZ_SLAB || block == Blocks.QUARTZ_STAIRS
				|| block == Blocks.SMOOTH_QUARTZ || block == Blocks.SMOOTH_QUARTZ_SLAB || block == Blocks.SMOOTH_QUARTZ_STAIRS) {
			return 80;
		}else if(block == Blocks.EMERALD_BLOCK || block == Blocks.EMERALD_ORE) {
			return 95;
		}else if(block == Blocks.DIAMOND_BLOCK || block == Blocks.DIAMOND_ORE) {
			return 135;
		}else if(block == Blocks.NETHERITE_BLOCK || block == Blocks.ANCIENT_DEBRIS || block == Blocks.OBSIDIAN || block == Blocks.CRYING_OBSIDIAN) {
			return 240;
		}else {
			return 0;
		}
	}
		
	private static double woodcuttingXpForBlock(Block block) {
		
		if(block == Blocks.OAK_BUTTON || block == Blocks.OAK_DOOR || block == Blocks.OAK_FENCE || block == Blocks.OAK_FENCE_GATE
				|| block == Blocks.OAK_LOG || block == Blocks.OAK_PLANKS || block == Blocks.OAK_PRESSURE_PLATE || block == Blocks.OAK_SIGN
				|| block == Blocks.OAK_SLAB || block == Blocks.OAK_STAIRS || block == Blocks.OAK_TRAPDOOR || block == Blocks.OAK_WALL_SIGN
				|| block == Blocks.OAK_WOOD || block == Blocks.STRIPPED_OAK_LOG || block == Blocks.STRIPPED_OAK_WOOD) {
			return 25;
		}else if(block == Blocks.BIRCH_BUTTON || block == Blocks.BIRCH_DOOR || block == Blocks.BIRCH_FENCE || block == Blocks.BIRCH_FENCE_GATE
				|| block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_PLANKS || block == Blocks.BIRCH_PRESSURE_PLATE || block == Blocks.BIRCH_SIGN
				|| block == Blocks.BIRCH_SLAB || block == Blocks.BIRCH_STAIRS || block == Blocks.BIRCH_TRAPDOOR || block == Blocks.BIRCH_WALL_SIGN
				|| block == Blocks.BIRCH_WOOD || block == Blocks.STRIPPED_BIRCH_LOG || block == Blocks.STRIPPED_BIRCH_WOOD) {
			return 37.5;
		}else if(block == Blocks.SPRUCE_BUTTON || block == Blocks.SPRUCE_DOOR || block == Blocks.SPRUCE_FENCE || block == Blocks.SPRUCE_FENCE_GATE
				|| block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_PLANKS || block == Blocks.SPRUCE_PRESSURE_PLATE || block == Blocks.SPRUCE_SIGN
				|| block == Blocks.SPRUCE_SLAB || block == Blocks.SPRUCE_STAIRS || block == Blocks.SPRUCE_TRAPDOOR || block == Blocks.SPRUCE_WALL_SIGN
				|| block == Blocks.SPRUCE_WOOD || block == Blocks.STRIPPED_SPRUCE_LOG || block == Blocks.STRIPPED_SPRUCE_WOOD) {
			return 67.5;
		}else if(block == Blocks.ACACIA_BUTTON || block == Blocks.ACACIA_DOOR || block == Blocks.ACACIA_FENCE || block == Blocks.ACACIA_FENCE_GATE
				|| block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_PLANKS || block == Blocks.ACACIA_PRESSURE_PLATE || block == Blocks.ACACIA_SIGN
				|| block == Blocks.ACACIA_SLAB || block == Blocks.ACACIA_STAIRS || block == Blocks.ACACIA_TRAPDOOR || block == Blocks.ACACIA_WALL_SIGN
				|| block == Blocks.ACACIA_WOOD || block == Blocks.STRIPPED_ACACIA_LOG || block == Blocks.STRIPPED_ACACIA_WOOD) {
			return 100;
		}else if(block == Blocks.JUNGLE_BUTTON || block == Blocks.JUNGLE_DOOR || block == Blocks.JUNGLE_FENCE || block == Blocks.JUNGLE_FENCE_GATE
				|| block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_PLANKS || block == Blocks.JUNGLE_PRESSURE_PLATE || block == Blocks.JUNGLE_SIGN
				|| block == Blocks.JUNGLE_SLAB || block == Blocks.JUNGLE_STAIRS || block == Blocks.JUNGLE_TRAPDOOR || block == Blocks.JUNGLE_WALL_SIGN
				|| block == Blocks.JUNGLE_WOOD || block == Blocks.STRIPPED_JUNGLE_LOG || block == Blocks.STRIPPED_JUNGLE_WOOD) {
			return 125;
		}else if(block == Blocks.DARK_OAK_BUTTON || block == Blocks.DARK_OAK_DOOR || block == Blocks.DARK_OAK_FENCE || block == Blocks.DARK_OAK_FENCE_GATE
				|| block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_PLANKS || block == Blocks.DARK_OAK_PRESSURE_PLATE || block == Blocks.DARK_OAK_SIGN
				|| block == Blocks.DARK_OAK_SLAB || block == Blocks.DARK_OAK_STAIRS || block == Blocks.DARK_OAK_TRAPDOOR || block == Blocks.DARK_OAK_WALL_SIGN
				|| block == Blocks.DARK_OAK_WOOD || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_DARK_OAK_WOOD) {
			return 175;
		}else if(block == Blocks.WARPED_BUTTON || block == Blocks.WARPED_DOOR || block == Blocks.WARPED_FENCE || block == Blocks.WARPED_FENCE_GATE
				|| block == Blocks.WARPED_HYPHAE || block == Blocks.WARPED_PLANKS || block == Blocks.WARPED_PRESSURE_PLATE || block == Blocks.WARPED_SIGN
				|| block == Blocks.WARPED_SLAB || block == Blocks.WARPED_STAIRS || block == Blocks.WARPED_STEM || block == Blocks.WARPED_TRAPDOOR
				|| block == Blocks.WARPED_WALL_SIGN || block == Blocks.WARPED_WART_BLOCK || block == Blocks.STRIPPED_WARPED_HYPHAE || block == Blocks.STRIPPED_WARPED_STEM) {
			return 250;
		}else if(block == Blocks.CRIMSON_BUTTON || block == Blocks.CRIMSON_DOOR || block == Blocks.CRIMSON_FENCE || block == Blocks.CRIMSON_FENCE_GATE
				|| block == Blocks.CRIMSON_HYPHAE || block == Blocks.CRIMSON_PLANKS || block == Blocks.CRIMSON_PRESSURE_PLATE || block == Blocks.CRIMSON_SIGN
				|| block == Blocks.CRIMSON_SLAB || block == Blocks.CRIMSON_STAIRS || block == Blocks.CRIMSON_STEM || block == Blocks.CRIMSON_TRAPDOOR
				|| block == Blocks.CRIMSON_WALL_SIGN || block == Blocks.NETHER_WART_BLOCK || block == Blocks.STRIPPED_CRIMSON_HYPHAE || block == Blocks.STRIPPED_CRIMSON_STEM) {
			return 380;
		}else {
			return 0;
		}
	}
	
	private static double farmingXpForBlock(Block block) {
		
		//Vegetables & Fruits
		if(block == Blocks.WHEAT) {
			return 9;
		}else if(block == Blocks.POTATOES) {
			return 10.5;
		}else if(block == Blocks.CARROTS) {
			return 11.5;
		}else if(block == Blocks.BEETROOTS) {
			return 14;
		}else if(block == Blocks.NETHER_WART) {
			return 19;
		}else if(block == Blocks.COCOA) {
			return 29;
		}else if(block == Blocks.MELON) {
			return 54.5;
		}else if(block == Blocks.PUMPKIN) {
			return 82;
			
			//Bushes
		}else if(block == Blocks.SWEET_BERRY_BUSH) {
			return 64;
			
			
		}else {
			return 0;
		}
	}
	
	private static double diggingXpForToolInteract(Block block) {
		if(block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.MYCELIUM) {
			return 5;
		}else {
			return 0;
		}
	}
	
	private static double woodcuttingXpForToolInteract(Block block) {
		if(block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD) {
			return 2.5;
		}else if(block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_WOOD) {
			return 4;
		}else if(block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_WOOD) {
			return 7;
		}else if(block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_WOOD) {
			return 10;
		}else if(block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_WOOD) {
			return 12.5;
		}else if(block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD) {
			return 17.5;
		}else if(block == Blocks.WARPED_STEM || block == Blocks.WARPED_HYPHAE) {
			return 25;
		}else if(block == Blocks.CRIMSON_STEM || block == Blocks.CRIMSON_HYPHAE) {
			return 38;
		}else {
			return 0;
		}
	}
	
	private static double farmingXpForToolInteract(Block block) {
		if(block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH || block == Blocks.DIRT || block == Blocks.COARSE_DIRT) {
			return 1;
		}else {
			return 0;
		}
	}

	/*private static double farmingXpForRightClickBlock(Block block) {
		if(block == Blocks.SWEET_BERRY_BUSH) {
			return 4.5;
		}else {
			return 0;
		}
	}*/
}
