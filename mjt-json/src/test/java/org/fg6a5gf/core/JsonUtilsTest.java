package org.fg6a5gf.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.jackson.JacksonJsonUtilsConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JsonUtilsTest
 *
 * @author zhengxx
 * @since 2022/12/15
 */
class JsonUtilsTest {

    @BeforeAll
    static void beforeAll() {
        JacksonJsonUtilsConfig configJackson4Utils = new JacksonJsonUtilsConfig();
        configJackson4Utils.config(new ObjectMapper());
    }

    @Test
    void testObj() {
        String userId = "u1";
        String name = "n1";
        String image = "i1";
        User user = new User(userId, name, image);
        String json = JsonUtils.toJson(user);
        assertNotNull(json);
        User jsonUser = JsonUtils.read(json, User.class);
        assertNotNull(jsonUser);
        assertEquals(userId, jsonUser.userId);
        assertEquals(name, jsonUser.name);
        assertEquals(image, jsonUser.image);
    }

    @Test
    void testList() {
        List<User> users = List.of(
                new User("u1", "n1", "i1"),
                new User("u2", "n2", "i2"),
                new User("u3", "n3", "i3")
        );
        String json = JsonUtils.toJson(users);
        assertNotNull(json);
        List<User> jsons = JsonUtils.readAsList(json, User.class);
        assertEquals(3, jsons.size());
        assertEquals("u3", jsons.get(2).userId);
        assertEquals("n3", jsons.get(2).name);
        assertEquals("i3", jsons.get(2).image);
    }

    static class User {
        String userId;
        String name;
        String image;

        public User() {
        }

        public User(String userId, String name, String image) {
            this.userId = userId;
            this.name = name;
            this.image = image;
        }

        public String getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }
    }
}