package org.metaborg.spoofax.shell.client.console;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;

import org.metaborg.spoofax.shell.client.IDisplay;
import org.metaborg.spoofax.shell.client.IEditor;
import org.metaborg.spoofax.shell.client.Repl;
import org.metaborg.spoofax.shell.client.ReplModule;

import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

/**
 * Bindings for the console repl.
 */
public class ConsoleReplModule extends ReplModule {
    private void configureUserInterface() {
        bind(TerminalUserInterface.class).in(Singleton.class);
        bind(IEditor.class).to(TerminalUserInterface.class);
        bind(IDisplay.class).to(TerminalUserInterface.class);

        bind(InputStream.class).annotatedWith(Names.named("in")).toInstance(System.in);
        bind(OutputStream.class).annotatedWith(Names.named("out")).toInstance(System.out);
        bind(OutputStream.class).annotatedWith(Names.named("err")).toInstance(System.err);

        bind(new TypeLiteral<Consumer<String>>() { }).annotatedWith(Names.named("onSuccess"))
                .to(OnEvalSuccessHook.class).in(Singleton.class);
        bind(new TypeLiteral<Consumer<String>>() { }).annotatedWith(Names.named("onError"))
                .to(OnEvalErrorHook.class).in(Singleton.class);
    }

    @Override
    protected void configure() {
        super.configure();

        configureUserInterface();

        bind(Repl.class).in(Singleton.class);
    }
}
