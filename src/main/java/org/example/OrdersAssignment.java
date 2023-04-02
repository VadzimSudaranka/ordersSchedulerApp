package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrdersAssignment {
    String picker;
    String orderId;
    String time;

    @Override
    public String toString() {
        return  picker +" "+ orderId +" "+ time;
    }
}

