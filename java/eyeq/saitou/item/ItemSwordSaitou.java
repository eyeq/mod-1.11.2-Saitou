package eyeq.saitou.item;

import com.google.common.collect.Multimap;
import eyeq.util.item.ItemUtils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;

public class ItemSwordSaitou extends ItemSword {
    public ItemSwordSaitou() {
        super(ItemUtils.TOOL_MATERIAL_DUMMY);
        this.setMaxDamage(100);
    }

    @Override
    public float getDamageVsEntity() {
        float f = itemRand.nextFloat();
        if(f < 0.6F) {
            return 1.0F;
        }
        return 20.0F;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        if(equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 3.0F + this.getDamageVsEntity(), 0));
        }
        return multimap;
    }
}
