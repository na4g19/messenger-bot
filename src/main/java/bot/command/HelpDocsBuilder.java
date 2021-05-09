package bot.command;

import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a documentation for a command
 */
public class HelpDocsBuilder {

    private String name;
    private String summary;

    private List<Option> options = new ArrayList<>();

    // FIXME: 04/02/2021 message size limit?
    public String printDocs() {

        StringBuilder builder = new StringBuilder();
        builder.append(name.toLowerCase()).append("     ").append(summary).append('\n');

        if(!options.isEmpty()) {
            builder.append("options: \n");
        }
        for(Option option : options) {
            builder.append(option.name).append("     ").append(option.summary).append('\n');
        }

        builder.append('\n');

        return builder.toString().replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));
    }

    public HelpDocsBuilder name(String name) {
        this.name = name;
        return this;
    }

    public HelpDocsBuilder summary(String summary) {
        this.summary = summary;
        return this;
    }

    public HelpDocsBuilder option(String name, String summary) {
        options.add(new Option(name, summary));
        return this;
    }

    private class Option {

        private String name;
        private String summary;

        public Option(String name, String summary) {
            this.name = name;
            this.summary = summary;
        }
    }
}
