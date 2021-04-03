package org.example.minestom.command;

import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GamemodeCommand extends Command {

    public GamemodeCommand() {
        super("gamemode");

        setCondition(Conditions::playerOnly);
        setDefaultExecutor((sender, context) -> sender.sendMessage("/gamemode <number>"));

        var numberArgument = ArgumentType.Integer("mode");

        addSyntax((sender, context) -> {
            final int number = context.get(numberArgument);
            Player p = sender.asPlayer();
            if (number == 0)
                p.setGameMode(GameMode.SURVIVAL);
            else if (number == 1)
                p.setGameMode(GameMode.CREATIVE);
            else if (number == 2)
                p.setGameMode(GameMode.ADVENTURE);
            else if (number == 3)
                p.setGameMode(GameMode.SPECTATOR);
            else p.sendMessage(Component.text(number));
            },
        numberArgument);
    }
}

