package sfa.das;

import com.github.dockerjava.core.dockerfile.DockerfileStatement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;
import java.util.Optional;

@Data
@Slf4j
public class Environments {
    private Map<String, Environment> environments;

    public Map<String,Environment> getEnvironments(){
        return  environments;
    }

    @Data
    public static class Environment {
        private String url;
        private String name;

            public String getUrl() {
                return url;
            }

            public void setName(String name){
                this.name=name;
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
