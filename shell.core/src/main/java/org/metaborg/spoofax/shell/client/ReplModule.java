package org.metaborg.spoofax.shell.client;

import org.metaborg.spoofax.shell.commands.IReplCommand;
import org.metaborg.spoofax.shell.core.CoreModule;

import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;

public class ReplModule extends CoreModule {
    protected MapBinder<String, IReplCommand> commandBinder;

    protected void configureCommands() {
        commandBinder = MapBinder.newMapBinder(binder(), String.class, IReplCommand.class);
        commandBinder.addBinding("exit").to(Repl.ExitCommand.class).in(Singleton.class);
    }

    @Override
    protected void configure() {
        super.configure();

        configureCommands();
    }

}
