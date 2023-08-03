package com.example.report.domain.handles;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.example.report.domain.annotation.CaseInsensitive;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class CaseInsensitiveHandlerMethodArgumentResolver extends RequestBodyAdviceAdapter {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return methodParameter.getParameterAnnotation(CaseInsensitive.class) != null;
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
      Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
      throws IOException {
    if (converterType == MappingJackson2HttpMessageConverter.class) {
      // Reading the JSON as a String
      String json = StreamUtils.copyToString(inputMessage.getBody(), Charset.defaultCharset());
      // Converting the JSON to lowercase
      json = json.toLowerCase();
      // Creating a new HttpInputMessage with the lowercase JSON
      return new CustomHttpInputMessage(json.getBytes(), inputMessage.getHeaders());
    }
    return inputMessage;
  }
  
}
