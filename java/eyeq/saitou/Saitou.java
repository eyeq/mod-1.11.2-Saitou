package eyeq.saitou;

import eyeq.saitou.item.ItemSwordSaitou;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static eyeq.saitou.Saitou.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class Saitou {
    public static final String MOD_ID = "eyeq_saitou";

    @Mod.Instance(MOD_ID)
    public static Saitou instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Item saitou;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        saitou = new ItemSwordSaitou().setUnlocalizedName("saitou");

        GameRegistry.register(saitou, resource.createResourceLocation("saitou"));
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(saitou), " P ", "ZSZ", " T ",
                'P', Items.BAKED_POTATO, 'Z', Items.ROTTEN_FLESH, 'T', Items.GHAST_TEAR, 'S', Items.IRON_SWORD);
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(saitou);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-Saitou");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, saitou, "Mr. Saitou");
        language.register(LanguageResourceManager.JA_JP, saitou, "斉藤さん");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, saitou, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
    }
}
