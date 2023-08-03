package com.example.report.domain.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CaseInsensitiveDeserializer extends JsonDeserializer<String> {

  // private final String alias;

  // public CaseInsensitiveDeserializer(String alias) {
  //   this.alias = alias;
  // }

  @Override
  public String deserialize(JsonParser p, DeserializationContext ctxt) {
    // Implemente a lógica personalizada aqui, usando o alias dinâmico
    String fieldName;
    try {
      fieldName = p.getCurrentName();
      if (fieldName.equalsIgnoreCase("itin")) {
        return p.getText();
      }
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null; // ou lançar uma exceção ou qualquer outro tratamento desejado
    }
    
  }

}
