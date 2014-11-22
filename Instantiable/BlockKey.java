package Reika.DragonAPI.Instantiable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import Reika.DragonAPI.Interfaces.BlockCheck;
import Reika.DragonAPI.Interfaces.BlockCheck;

public final class BlockKey implements BlockCheck {

	public final Block blockID;
	public final int metadata;

	public BlockKey(Block b) {
		this(b, -1);
	}

	public BlockKey(Block b, int meta) {
		metadata = meta;
		blockID = b;
	}

	public static BlockKey getAt(IBlockAccess world, int x, int y, int z) {
		return new BlockKey(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
	}

	public static BlockKey getAtNoMeta(IBlockAccess world, int x, int y, int z) {
		return new BlockKey(world.getBlock(x, y, z), -1);
	}

	@Override
	public int hashCode() {
		return blockID.hashCode()/* + metadata << 24*/;
	}

	@Override
	public boolean equals(Object o) {
		//ReikaJavaLibrary.pConsole(this+" & "+o);
		if (o instanceof BlockKey) {
			BlockKey b = (BlockKey)o;
			return b.blockID == blockID && (!this.hasMetadata() || !b.hasMetadata() || b.metadata == metadata);
		}
		return false;
	}

	@Override
	public String toString() {
		return blockID+":"+metadata;
	}

	public boolean hasMetadata() {
		return metadata >= 0 && metadata != OreDictionary.WILDCARD_VALUE;
	}

	public ItemStack asItemStack() {
		return new ItemStack(blockID, 1, metadata);
	}

	public boolean match(Block b, int meta) {
		return b == blockID && (!this.hasMetadata() || meta == metadata);
	}

	public boolean matchInWorld(World world, int x, int y, int z) {
		return this.match(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
	}

	@Override
	public void place(World world, int x, int y, int z) {
		world.setBlock(x, y, z, blockID, this.hasMetadata() ? metadata : 0, 3);
	}
}