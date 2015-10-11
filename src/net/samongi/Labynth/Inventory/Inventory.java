package net.samongi.Labynth.Inventory;

import java.util.ArrayList;
import java.util.List;

import net.samongi.Labynth.Inventory.InventorySlot.InventorySlotType;

public interface Inventory
{
	/**Returns all slots stored in this inventory.
	 * This will always return an empty array, never will it return null.
	 * 
	 * @return A list of all inventory slots.
	 */
  public List<InventorySlot> getSlots();
  
  /**Returns all slots that match the type of the passed in slot.
   * 
   * @param type The type of the slot.
   * @return A list of slots matched the type.
   */
  public default List<InventorySlot> getSlotByType(InventorySlotType type)
  {
  	List<InventorySlot> ret_slots = new ArrayList<>();
  	List<InventorySlot> slots = this.getSlots();
  	for(InventorySlot s : slots) if(s.getType().equals(type)) ret_slots.add(s);
  	
  	return ret_slots;
  }
}
