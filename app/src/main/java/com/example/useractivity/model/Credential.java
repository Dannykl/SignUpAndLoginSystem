package com.example.useractivity.model;

import com.google.gson.annotations.SerializedName;

public class Credential
{
      @SerializedName("error")
      private boolean errorResult;

      @SerializedName("error_msg")
      private String errorMessage;

      public Credential(boolean errorResult, String errorMessage) {
            this.errorResult = errorResult;
            this.errorMessage = errorMessage;
      }

      public boolean getErrorResult() {
            return errorResult;
      }

      public String getErrorMessage() {
            return errorMessage;
      }

      public void setErrorResult(boolean errorResult) {
            this.errorResult = errorResult;
      }

      public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
      }
}
