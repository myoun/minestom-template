package org.example.minestom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.CommandManager;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSkinInitEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.utils.Position;
import org.example.minestom.command.GamemodeCommand;
import org.example.minestom.command.StopCommand;
import org.example.minestom.generation.StoneFlatWorldgen;

public class Main {

    public static void main(String[] args) {
        // Minecraft Server Init
        MinecraftServer minecraftServer = MinecraftServer.init();

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        CommandManager commandManager = MinecraftServer.getCommandManager();
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        // Set Chunk Generator & Register Command
        instanceContainer.setChunkGenerator(new StoneFlatWorldgen());
        commandManager.register(new StopCommand());
        commandManager.register(new GamemodeCommand());

        // Add Event at GlobalEventHandler
        globalEventHandler.addEventCallback(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Position(0, 42, 0));
            player.setGameMode(GameMode.CREATIVE);

            Audiences.all().sendMessage(Component.text(player.getUsername()+" joined the game.",
                    NamedTextColor.YELLOW));
        });

        globalEventHandler.addEventCallback(PlayerSkinInitEvent.class, event -> {
            final Player player = event.getPlayer();
            PlayerSkin skin = PlayerSkin.fromUsername(player.getUsername()); // Get Skin From Mojang Server
            player.setSkin(skin);
        });
    }
}
