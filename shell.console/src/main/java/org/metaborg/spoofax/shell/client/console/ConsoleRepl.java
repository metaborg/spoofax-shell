package org.metaborg.spoofax.shell.client.console;

import java.io.IOException;

import org.fusesource.jansi.Ansi;
import org.metaborg.spoofax.shell.client.IDisplay;
import org.metaborg.spoofax.shell.client.Repl;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ConsoleRepl {
    private static Injector injector;

    static {
        injector = Guice.createInjector(new ConsoleReplModule());
    }

    /**
     * @param args
     *            Unused.
     * @throws IOException
     *             when an IO error occurs.
     */
    public static void main(String[] args) throws IOException {
        injector.getInstance(IDisplay.class)
                .displayResult(Ansi.ansi().a("Welcome to the ").bold().a("Spoofax").reset().a(" REPL").toString());
        injector.getInstance(Repl.class).run();
    }
}
