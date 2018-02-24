package com.devops.spring;

import org.junit.Test;
import static org.junit.Assert.*;

public class MongoConfigTest {

    @Test
    public void ensureCorrectParsingOfEnvVariablesContainsPortNumber() throws Exception {
        String expectedPortNumber ="27017";
        String MONGO_PORT_ENV_VAR= "tcp://mongodb:27017";
        String[] tokens = MONGO_PORT_ENV_VAR.split(":");
        assertEquals("Not able to correctly extract port number", expectedPortNumber, tokens[2]);
    }

}
