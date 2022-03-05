package edu.vn.redditspring.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsePayload {
  private int code;
  private boolean success;
  private String message;
}
