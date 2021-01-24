package xyz.kamefrede.advancemenot;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AdvanceMeNot.MODID)
public class AdvanceMeNot {
    public static final String MODID = "advancemenot";
    public static final Logger LOGGER = LogManager.getLogger(MODID);


    public AdvanceMeNot() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        AdvanceMeNotCommand.register(event.getDispatcher());
    }


}
