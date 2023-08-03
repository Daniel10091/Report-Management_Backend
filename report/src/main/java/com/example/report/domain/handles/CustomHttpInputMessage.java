package com.example.report.domain.handles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

public class CustomHttpInputMessage implements HttpInputMessage {

  private final byte[] body;
  private final HttpHeaders headers;

  public CustomHttpInputMessage(byte[] body, HttpHeaders headers) {
    this.body = body;
    this.headers = headers;
  }

  @Override
  public InputStream getBody() throws IOException {
    return new ByteArrayInputStream(body);
  }

  @Override
  public HttpHeaders getHeaders() {
    return headers;
  }
  
}
