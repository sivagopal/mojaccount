package com.restapi.moj.account.application.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.restapi.moj.account.application.data.Account;

import java.io.IOException;

public class JsonSchemaValidator {
    public Boolean isValid(String jsonSchemaPath, JsonNode accountNode) throws IOException,
            ProcessingException {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonNode accountScehma = JsonLoader.fromResource(jsonSchemaPath);
        JsonSchema jsonSchema = factory.getJsonSchema(accountScehma);
        if (jsonSchema.validate(accountNode).isSuccess()) {
            return true;
        }
        //return factory.getJsonSchema(accountScehma);
        return false;
    }
}