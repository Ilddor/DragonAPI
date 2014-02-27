/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract.Lua;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import Reika.DragonAPI.Libraries.ReikaInventoryHelper;

public class LuaIsFull extends LuaMethod {

	public LuaIsFull() {
		super("isFull", IInventory.class);
	}

	@Override
	public Object[] invoke(TileEntity te, Object[] args) throws Exception {
		IInventory ii = (IInventory)te;
		return new Object[]{ReikaInventoryHelper.isFull(ii)};
	}

}