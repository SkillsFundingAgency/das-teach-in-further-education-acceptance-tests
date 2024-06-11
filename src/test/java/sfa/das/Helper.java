package sfa.das;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class Helper {
    /**
     * Reads the yaml file as an InputStream.
     *
     * @param filename to read.
     * @return Optional input stream.
     */
    public static Optional<String> loadYaml(String filename) {
        try {
            var yaml = new String(Environments.class
                    .getClassLoader()
                    .getResourceAsStream(filename)
                    .readAllBytes(), UTF_8);
            return Optional.of(yaml)
                    .map(Helper::interpolateEnvVars);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Replaces ${...} references in a property/yaml file with values derived from environment
     * variables. Also supports environment vars supplied in a .env file located in the root of
     * the project. Where an environment variable exists on the host system AND the .env file,
     * the host system takes precedence.
     *
     * @param text or property file
     * @return interpolated version with env var values supplied.
     */
    public static String interpolateEnvVars(String text) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        Matcher m = Pattern.compile("\\$\\{(.*)}").matcher(text);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            var parts = m.group(1).split(":");
            var replacementValue = parts.length > 1
                    ? dotenv.get(parts[0], parts[1])
                    : dotenv.get(parts[0]);
            if (replacementValue == null) {
                log.info("**** environment variable set to empty string as not defined {} ****",  parts[0]);
                replacementValue = "";
            }
            m.appendReplacement(sb, replacementValue);
        }
        m.appendTail(sb);
        return sb.toString();
    }


}