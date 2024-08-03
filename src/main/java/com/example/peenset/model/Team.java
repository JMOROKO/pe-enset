package com.example.peenset.model;

import java.util.List;

public record Team(String name, List<String> players, String location, int year) {
}
