package com.example.sweater.demo.service.impl;

import com.example.sweater.demo.model.Genre;
import com.example.sweater.demo.service.GenreDeserializer;
import com.example.sweater.demo.service.GenreService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GenreDeserializerImpl extends StdDeserializer<Genre>  implements GenreDeserializer {

    @Autowired
    private GenreService genreService;

    public GenreDeserializerImpl() {
        this(null);
    }

    public GenreDeserializerImpl(Class<?> vc) {
        super(vc);
    }

    @Override
    public Genre deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        return genreService.buildGenre(node.textValue());
    }

    @Override
    public GenreDeserializerImpl getInstance() {
        return this;
    }
}
