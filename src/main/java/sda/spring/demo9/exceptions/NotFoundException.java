package sda.spring.demo9.exceptions;

public class NotFoundException extends RuntimeException {
  public NotFoundException(final String message) {
    super(message);
  }
}