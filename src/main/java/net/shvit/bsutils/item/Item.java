package net.shvit.bsutils.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.shvit.bsutils.chat.Message;
import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;
    private Material material = Material.STONE;
    private int amount = 1,
            damage = 0;
    private Component displayname;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<Component> lore = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();

    public Item() {
        this.itemStack = new ItemStack(material);
    }

    public Item(Material material) {

        if (material == null) {

            material = Material.STONE;

        }

        this.itemStack = new ItemStack(material);
        this.material = material;

    }

    public Item(ItemStack itemStack) {

        Validate.notNull(itemStack, "The itemStack is null.");

        this.itemStack = itemStack;
        this.material = itemStack.getType();
        this.amount = itemStack.getAmount();
        this.enchantments = itemStack.getEnchantments();


        if (itemStack.hasItemMeta()) {

            this.itemMeta = itemStack.getItemMeta();
            this.displayname = itemStack.displayName();
            this.lore = itemMeta.lore();
            this.flags.addAll(itemStack.getItemMeta().getItemFlags());

        }
    }

    public Item(Item item) {

        Validate.notNull(item, "The item is null.");

        this.itemStack = item.itemStack;
        this.itemMeta = item.itemMeta;
        this.material = item.material;
        this.amount = item.amount;
        this.displayname = item.displayname;
        this.enchantments = item.enchantments;
        this.flags = item.flags;
        this.damage = item.damage;
        this.lore = item.lore;

    }

    public Item material(Material material) {

        Validate.notNull(material, "The material is null.");

        this.material = material;
        return this;

    }

    public Item amount(int amount) {

        if (amount > itemStack.getMaxStackSize()) {

            amount = itemStack.getMaxStackSize();

        } else if (amount <= 0) {

            amount = 1;

        }

        this.amount = amount;

        return this;
    }

    public Item damage(int damage) {

        this.damage = damage;
        return this;

    }

    public Item itemMeta(ItemMeta itemMeta) {

        Validate.notNull(itemMeta, "The itemMeta is null.");

        this.itemMeta = itemMeta;

        return this;

    }

    public Item enchantments(Map<Enchantment, Integer> enchantments) {

        Validate.notNull(enchantments, "The enchantment hashMap is null.");

        this.enchantments = enchantments;

        return this;

    }

    public Item displayname(String displayname) {

        Validate.notNull(displayname, "The displayname is null.");

        this.displayname = Component.text().content(displayname).decorate(TextDecoration.BOLD).build();

        return this;

    }

    public Item displayname(String displayname, TextColor color) {

        Validate.notNull(displayname, "The displayname is null.");

        this.displayname = Component.text().content(displayname).color(color).decorate(TextDecoration.BOLD).build();

        return this;

    }

    public Item lore(Message loreLine) {

        Validate.notNull(loreLine, "The lore is null.");

        this.lore.add(loreLine.build());

        return this;

    }

    public Item lore(ArrayList<Message> loreLine) {

        Validate.notNull(loreLine, "The lore is null.");

        for (Message message : loreLine) {

            this.lore.add(message.build());

        }

        return this;

    }

    public Item flag(ItemFlag itemFlag) {

        Validate.notNull(itemFlag, "The flag is null.");

        this.flags.add(itemFlag);

        return this;

    }

    public Item flag(List<ItemFlag> flags) {

        Validate.notNull(flags, "The flags are null.");

        this.flags = flags;

        return this;

    }

    public ItemStack toItemStack() {

        itemStack.setType(material);
        itemStack.setAmount(amount);
        itemMeta = itemStack.getItemMeta();

        if (!enchantments.isEmpty()) {

            itemStack.addEnchantments(enchantments);

        }

        if (displayname != null) {

            itemMeta.displayName(displayname);

        }

        if (!lore.isEmpty()) {

            itemMeta.lore(lore);

        }

        if (!flags.isEmpty()) {

            for (ItemFlag flag : flags) {

                itemMeta.addItemFlags(flag);

            }
        }

        Damageable damageable = (Damageable) itemMeta;
        damageable.setDamage(damage);
        itemStack.setItemMeta(damageable);

        return itemStack;
    }

}
