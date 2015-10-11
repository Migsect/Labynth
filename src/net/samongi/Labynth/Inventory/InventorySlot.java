package net.samongi.Labynth.Inventory;

import net.samongi.Labynth.Inventory.Item.Item;

public interface InventorySlot
{
	public enum InventorySlotType
	{
		GENERAL, // This type of inventory slot can store any items.
	  EQUIPMENT, // This type can only store equiped items.
	  CONSUMABLE, // This type can only store consumable items.
	  CURRENCY, // THis type can only store currency items.
	  SKILL; // This type can only store a skill items.
	}

	/**This will return the type of the inventory slot.
	 * 
	 * @return The InventorySlotType of this InventorySlot
	 */
  public InventorySlotType getType();
  
  /**Returns true if the inventory slot currently has an item stored in it.
   *   Returns false if an item is not stored within it.
   *   
   * @return True if an item is stored inside it.
   */
  public default boolean hasStoredItem(){return this.getStoredItem() == null;}
  
  /**This will return the stored item in the slot.  Otherwise null
   * 
   * @return The stored item.
   */
  public Item getStoredItem();
  
  public boolean canStoreItem(Item item);
  
  public boolean storeItem(Item item);
  
}
