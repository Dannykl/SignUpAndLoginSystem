package com.example.useractivity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User
{
      private String firstName;
      private String lastName;
      @Expose
      @SerializedName("email")
      private String email;
      @Expose
      @SerializedName("password")
      private String password;

      private String gender;

      public User() {
      }

      public User(String email, String password) {
            this.email = email;
            this.password = password;
      }

      public User(String firstName, String lastName, String email, String gender, String password) {
            this.firstName = firstName;
            this.lastName=lastName;
            this.email = email;
            this.gender = gender;
            this.password = password;
      }

      public String getFirstName() {
            return firstName;
      }
      public String getLastName() {
            return lastName;
      }
      public String getEmail() {
            return email;
      }

      public String getGender() {
            return gender;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }
      public void setEmail(String email) {
            this.email = email;
      }
}
