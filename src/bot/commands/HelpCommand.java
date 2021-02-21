package bot.commands;

import bot.HelpDocsBuilder;
import bot.Interactor;

/**
 * Generates documentation for all possible commands
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("HELP");
    }

    @Override
    public void execute(Interactor interactor) {

        StringBuilder docs = new StringBuilder();

        docs.append(new NameCommand().constructDocs().printDocs());
        docs.append(new SquareCommand().constructDocs().printDocs());
        docs.append(new TimerCommand().constructDocs().printDocs());
        docs.append(new HelpCommand().constructDocs().printDocs());

        interactor.sendText(docs.toString());
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Prints all possible commands");
    }
}
