package com.narozhnyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomOrder {
    private List<SingleDish> randomOrdersList;
    private User user;
}
