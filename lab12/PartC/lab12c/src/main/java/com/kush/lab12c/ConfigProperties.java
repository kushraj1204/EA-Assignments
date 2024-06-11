package com.kush.lab12c;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

/**
 * @author kush
 */

@Component
@ConfigurationProperties(prefix = "")
@Validated
public class ConfigProperties {

    private ApplicationConfig application = new ApplicationConfig();
    private ServerConfig server = new ServerConfig();
    private UserConfig user = new UserConfig();

    private String[] countries;

    @Valid
    public static class ApplicationConfig {
        @NotBlank
        private String name;
        @NotBlank
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "ApplicationConfig{" +
                    "name='" + name + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

    @Valid
    public static class ServerConfig {
        @NotBlank
        private String port;
        private String url;

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ServerConfig{" +
                    "port=" + port +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Valid
    public static class UserConfig {
        private String firstname;
        private String lastname;

        @NotBlank
        @Size(max = 15, min = 6)
        private String username;

        @NotBlank
        @Size(max = 15, min = 6)
        private String password;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "UserConfig{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public ServerConfig getServer() {
        return server;
    }

    public void setServer(ServerConfig server) {
        this.server = server;
    }

    public UserConfig getUser() {
        return user;
    }

    public void setUser(UserConfig user) {
        this.user = user;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "ConfigProperties{" +
                "application=" + application +
                ", server=" + server +
                ", user=" + user +
                ", countries=" + Arrays.toString(countries) +
                '}';
    }
}
