package com.coremedia.caas.services.security;

public class AccessControlViolation extends Exception {

  private AccessControlResultCode errorCode;


  public AccessControlViolation(AccessControlResultCode errorCode) {
    this.errorCode = errorCode;
  }

  public AccessControlViolation(AccessControlResultCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }


  public AccessControlResultCode getErrorCode() {
    return errorCode;
  }
}
