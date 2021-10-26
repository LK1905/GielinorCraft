package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LevelLockEventHandler {

	@SubscribeEvent
	public static void onDiggingBreakEvent(BlockEvent.BreakEvent event) {
		
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		int levelReq = diggingLevelForBlock(event.getState().getBlock());
		
		if(!player.world.isRemote) {
			if(event.getState().getHarvestTool() == ToolType.SHOVEL && !(player.getHeldItemMainhand().getItem() instanceof ShovelItem)) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("A shovel is required to dig this block."), (ServerPlayerEntity) player);
			}else if(skills.getLevel(24) < levelReq) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("You do not have the Digging level required to dig this block. Level "
						+ levelReq + " required."), (ServerPlayerEntity) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onMiningBreakEvent(BlockEvent.BreakEvent event) {
		
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		int levelReq = miningLevelForBlock(event.getState().getBlock());
		
		if(!player.world.isRemote) {
			if(event.getState().getHarvestTool() == ToolType.PICKAXE && !(player.getHeldItemMainhand().getItem() instanceof PickaxeItem)) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("A pickaxe is required to mine this block."), (ServerPlayerEntity) player);
			}else if(skills.getLevel(14) < levelReq) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("You do not have the Mining level required to mine this block. Level "
						+ levelReq + " required."), (ServerPlayerEntity) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onWoodcuttingBreakEvent(BlockEvent.BreakEvent event) {
		
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		int levelReq = woodcuttingLevelForBlock(event.getState().getBlock());
		
		if(!player.world.isRemote) {
			if(event.getState().getHarvestTool() == ToolType.AXE && !(player.getHeldItemMainhand().getItem() instanceof AxeItem)) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("An axe is required to cut this block."), (ServerPlayerEntity) player);
			}else if(skills.getLevel(8) < levelReq) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("You do not have the Woodcutting level required to chop this block. Level "
						+ levelReq + " required."), (ServerPlayerEntity) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onFarmingBreakEvent(BlockEvent.BreakEvent event) {
		
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		int levelReq = farmingLevelForBlock(event.getState().getBlock());
		
		if(!player.world.isRemote) {
			if(skills.getLevel(19) < levelReq) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("You do not have the Farming level required to harvest this block. Level "
						+ levelReq + " required."), (ServerPlayerEntity) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onWoodcuttingInteractEvent(BlockEvent.BlockToolInteractEvent event) {
		
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		int levelReq = woodcuttingLevelForBlock(event.getState().getBlock());
		
		if(!player.world.isRemote) {
			if(skills.getLevel(8) < levelReq) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("You do not have the Woodcutting level required to strip this block. Level "
					+ levelReq + " required."), (ServerPlayerEntity) player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onFarmingInteractEvent(BlockEvent.BlockToolInteractEvent event) {
		
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		int levelReq = farmingLevelForBlock(event.getState().getBlock());
		
		if(!player.world.isRemote) {
			if(skills.getLevel(19) < levelReq) {
				event.setCanceled(true);
				PacketHandler.sendTo(new StringPacket("You do not have the Farming level required to harvest this block. Level "
					+ levelReq + " required."), (ServerPlayerEntity) player);
			}
		}
	}
	
	private static int diggingLevelForBlock(Block block) {
		
		if(block == Blocks.GRAVEL) {
			return 10;
		}else if(block == Blocks.CRIMSON_NYLIUM || block == Blocks.WARPED_NYLIUM) {
			return 20;
		}else if(block == Blocks.CLAY) {
			return 30;
		}else if(block == Blocks.SOUL_SAND || block == Blocks.SOUL_SOIL) {
			return 40;
		}else {
			return 0;
		}
	}
	
	private static int miningLevelForBlock(Block block) {
		
		if(block == Blocks.ANDESITE || block == Blocks.ANDESITE_SLAB || block == Blocks.ANDESITE_STAIRS || block == Blocks.ANDESITE_WALL
				|| block == Blocks.POLISHED_ANDESITE || block == Blocks.POLISHED_ANDESITE_SLAB || block == Blocks.POLISHED_ANDESITE_STAIRS
				|| block == Blocks.DIORITE || block == Blocks.DIORITE_SLAB || block == Blocks.DIORITE_STAIRS || block == Blocks.DIORITE_WALL
				|| block == Blocks.POLISHED_DIORITE || block == Blocks.POLISHED_DIORITE_SLAB || block == Blocks.POLISHED_DIORITE_STAIRS
				|| block == Blocks.GRANITE || block == Blocks.GRANITE_SLAB || block == Blocks.GRANITE_STAIRS || block == Blocks.GRANITE_WALL
				|| block == Blocks.POLISHED_GRANITE || block == Blocks.POLISHED_GRANITE_SLAB || block == Blocks.POLISHED_GRANITE_STAIRS) {
			return 5;
		}else if(block == Blocks.NETHER_BRICK_FENCE || block == Blocks.NETHER_BRICK_SLAB || block == Blocks.NETHER_BRICK_STAIRS
				|| block == Blocks.NETHER_BRICK_WALL || block == Blocks.NETHER_BRICKS || block == Blocks.RED_NETHER_BRICKS
				|| block == Blocks.RED_NETHER_BRICK_SLAB || block == Blocks.RED_NETHER_BRICK_STAIRS || block == Blocks.RED_NETHER_BRICK_WALL) {
			return 8;
		}else if(block == Blocks.COAL_BLOCK || block == Blocks.COAL_ORE) {
			return 10;
		}else if(block == Blocks.BLACKSTONE || block == Blocks.BLACKSTONE_SLAB || block == Blocks.BLACKSTONE_STAIRS || block == Blocks.BLACKSTONE_WALL
				|| block == Blocks.CHISELED_POLISHED_BLACKSTONE || block == Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS || block == Blocks.GILDED_BLACKSTONE
				|| block == Blocks.POLISHED_BLACKSTONE || block == Blocks.POLISHED_BLACKSTONE_BRICKS
				|| block == Blocks.POLISHED_BLACKSTONE_BRICK_SLAB || block == Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS || block == Blocks.POLISHED_BLACKSTONE_BRICK_WALL) {
			return 12;
		}else if(block == Blocks.IRON_BARS || block == Blocks.IRON_BLOCK || block == Blocks.IRON_DOOR || block == Blocks.IRON_ORE || block == Blocks.IRON_TRAPDOOR) {
			return 15;
		}else if(block == Blocks.LAPIS_BLOCK || block == Blocks.LAPIS_ORE) {
			return 20;
		}else if(block == Blocks.REDSTONE_BLOCK || block == Blocks.REDSTONE_ORE) {
			return 30;
		}else if(block == Blocks.GOLD_BLOCK || block == Blocks.GOLD_ORE || block == Blocks.NETHER_GOLD_ORE) {
			return 40;
		}else if(block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.CHISELED_QUARTZ_BLOCK || block == Blocks.QUARTZ_BLOCK
				|| block == Blocks.QUARTZ_BRICKS || block == Blocks.QUARTZ_PILLAR || block == Blocks.QUARTZ_SLAB || block == Blocks.QUARTZ_STAIRS
				|| block == Blocks.SMOOTH_QUARTZ || block == Blocks.SMOOTH_QUARTZ_SLAB || block == Blocks.SMOOTH_QUARTZ_STAIRS) {
			return 50;
		}else if(block == Blocks.EMERALD_BLOCK || block == Blocks.EMERALD_ORE) {
			return 70;
		}else if(block == Blocks.DIAMOND_BLOCK || block == Blocks.DIAMOND_ORE) {
			return 85;
		}else if(block == Blocks.NETHERITE_BLOCK || block == Blocks.ANCIENT_DEBRIS || block == Blocks.OBSIDIAN || block == Blocks.CRYING_OBSIDIAN) {
			return 90;
		}else {
			return 0;
		}
	}
		
	private static int woodcuttingLevelForBlock(Block block) {
		
		if(block == Blocks.BIRCH_BUTTON || block == Blocks.BIRCH_DOOR || block == Blocks.BIRCH_FENCE || block == Blocks.BIRCH_FENCE_GATE
				|| block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_PLANKS || block == Blocks.BIRCH_PRESSURE_PLATE || block == Blocks.BIRCH_SIGN
				|| block == Blocks.BIRCH_SLAB || block == Blocks.BIRCH_STAIRS || block == Blocks.BIRCH_TRAPDOOR || block == Blocks.BIRCH_WALL_SIGN
				|| block == Blocks.BIRCH_WOOD || block == Blocks.STRIPPED_BIRCH_LOG || block == Blocks.STRIPPED_BIRCH_WOOD) {
			return 15;
		}else if(block == Blocks.SPRUCE_BUTTON || block == Blocks.SPRUCE_DOOR || block == Blocks.SPRUCE_FENCE || block == Blocks.SPRUCE_FENCE_GATE
				|| block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_PLANKS || block == Blocks.SPRUCE_PRESSURE_PLATE || block == Blocks.SPRUCE_SIGN
				|| block == Blocks.SPRUCE_SLAB || block == Blocks.SPRUCE_STAIRS || block == Blocks.SPRUCE_TRAPDOOR || block == Blocks.SPRUCE_WALL_SIGN
				|| block == Blocks.SPRUCE_WOOD || block == Blocks.STRIPPED_SPRUCE_LOG || block == Blocks.STRIPPED_SPRUCE_WOOD) {
			return 30;
		}else if(block == Blocks.ACACIA_BUTTON || block == Blocks.ACACIA_DOOR || block == Blocks.ACACIA_FENCE || block == Blocks.ACACIA_FENCE_GATE
				|| block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_PLANKS || block == Blocks.ACACIA_PRESSURE_PLATE || block == Blocks.ACACIA_SIGN
				|| block == Blocks.ACACIA_SLAB || block == Blocks.ACACIA_STAIRS || block == Blocks.ACACIA_TRAPDOOR || block == Blocks.ACACIA_WALL_SIGN
				|| block == Blocks.ACACIA_WOOD || block == Blocks.STRIPPED_ACACIA_LOG || block == Blocks.STRIPPED_ACACIA_WOOD) {
			return 45;
		}else if(block == Blocks.JUNGLE_BUTTON || block == Blocks.JUNGLE_DOOR || block == Blocks.JUNGLE_FENCE || block == Blocks.JUNGLE_FENCE_GATE
				|| block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_PLANKS || block == Blocks.JUNGLE_PRESSURE_PLATE || block == Blocks.JUNGLE_SIGN
				|| block == Blocks.JUNGLE_SLAB || block == Blocks.JUNGLE_STAIRS || block == Blocks.JUNGLE_TRAPDOOR || block == Blocks.JUNGLE_WALL_SIGN
				|| block == Blocks.JUNGLE_WOOD || block == Blocks.STRIPPED_JUNGLE_LOG || block == Blocks.STRIPPED_JUNGLE_WOOD) {
			return 50;
		}else if(block == Blocks.DARK_OAK_BUTTON || block == Blocks.DARK_OAK_DOOR || block == Blocks.DARK_OAK_FENCE || block == Blocks.DARK_OAK_FENCE_GATE
				|| block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_PLANKS || block == Blocks.DARK_OAK_PRESSURE_PLATE || block == Blocks.DARK_OAK_SIGN
				|| block == Blocks.DARK_OAK_SLAB || block == Blocks.DARK_OAK_STAIRS || block == Blocks.DARK_OAK_TRAPDOOR || block == Blocks.DARK_OAK_WALL_SIGN
				|| block == Blocks.DARK_OAK_WOOD || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_DARK_OAK_WOOD) {
			return 60;
		}else if(block == Blocks.WARPED_BUTTON || block == Blocks.WARPED_DOOR || block == Blocks.WARPED_FENCE || block == Blocks.WARPED_FENCE_GATE
				|| block == Blocks.WARPED_HYPHAE || block == Blocks.WARPED_PLANKS || block == Blocks.WARPED_PRESSURE_PLATE || block == Blocks.WARPED_SIGN
				|| block == Blocks.WARPED_SLAB || block == Blocks.WARPED_STAIRS || block == Blocks.WARPED_STEM || block == Blocks.WARPED_TRAPDOOR
				|| block == Blocks.WARPED_WALL_SIGN || block == Blocks.WARPED_WART_BLOCK || block == Blocks.STRIPPED_WARPED_HYPHAE || block == Blocks.STRIPPED_WARPED_STEM) {
			return 75;
		}else if(block == Blocks.CRIMSON_BUTTON || block == Blocks.CRIMSON_DOOR || block == Blocks.CRIMSON_FENCE || block == Blocks.CRIMSON_FENCE_GATE
				|| block == Blocks.CRIMSON_HYPHAE || block == Blocks.CRIMSON_PLANKS || block == Blocks.CRIMSON_PRESSURE_PLATE || block == Blocks.CRIMSON_SIGN
				|| block == Blocks.CRIMSON_SLAB || block == Blocks.CRIMSON_STAIRS || block == Blocks.CRIMSON_STEM || block == Blocks.CRIMSON_TRAPDOOR
				|| block == Blocks.CRIMSON_WALL_SIGN || block == Blocks.NETHER_WART_BLOCK || block == Blocks.STRIPPED_CRIMSON_HYPHAE || block == Blocks.STRIPPED_CRIMSON_STEM) {
			return 90;
		}else {
			return 0;
		}
	}
	
	private static int farmingLevelForBlock(Block block) {
		
		//Vegetables & Fruits
		if(block == Blocks.POTATOES) {
			return 5;
		}else if(block == Blocks.CARROTS) {
			return 7;
		}else if(block == Blocks.BEETROOTS) {
			return 12;
		}else if(block == Blocks.NETHER_WART) {
			return 20;
		}else if(block == Blocks.COCOA) {
			return 31;
		}else if(block == Blocks.MELON) {
			return 47;
		}else if(block == Blocks.PUMPKIN) {
			return 61;
			
			//Bushes
		}else if(block == Blocks.SWEET_BERRY_BUSH) {
			return 10;
			
			
		}else {
			return 0;
		}
	}
}
