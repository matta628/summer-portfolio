package com.google.sps.data;

import com.google.sps.data.Pet;
import java.util.ArrayList;
import java.util.List;

public final class Display{

  private final List<Pet> pets;
  private final String uploadUrl;

  public Display(List<Pet> pets, String uploadUrl) {
    this.pets = pets;
    this.uploadUrl = uploadUrl;
  }
}