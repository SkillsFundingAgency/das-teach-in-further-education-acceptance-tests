package sfa.das;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

@Data
@Slf4j
public class Environments {
    private Map<String, Environment> environments;

    @Data
    public static class Environment {
        private String url;
        private String name;

            public String getUrl() {
                return url;
            }
    }



    public static Environment get(String env) {
        Yaml yaml = new Yaml();
        return Helper.loadYaml("environments.yml")
                .map(is -> yaml.loadAs(is, Environments.class))
                .map(e -> {
                    return ((Environments) e).getEnvironments().get(env);
                })
                .map(e -> {
                    e.setName(env);
                    return e;
                })
                .orElseThrow(
                        () -> new RuntimeException("Unable to load environment [" + env + "] from environments.yaml"));
    }
}
