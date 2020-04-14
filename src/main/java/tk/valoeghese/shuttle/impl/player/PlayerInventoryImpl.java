package tk.valoeghese.shuttle.impl.player;

import net.minecraft.entity.player.PlayerInventory;
import tk.valoeghese.shuttle.api.player.Inventory;

public class PlayerInventoryImpl implements Inventory {
	public PlayerInventoryImpl(PlayerInventory parent) {
		this.parent = parent;
	}

	private final PlayerInventory parent;
}
