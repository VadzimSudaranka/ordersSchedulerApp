package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;


@Getter
@Setter
@AllArgsConstructor
public class Orders implements Comparable<Orders> {
    String orderId;
    String orderValue;
    long pickingTime;
    String completeBy;

    @Override
    public String toString() {
        return '{' +
                " orderId: " + orderId + '\n' +
                " orderValue: " + orderValue + '\n' +
                " pickingTime: " + pickingTime + '\n' +
                " completeBy: " + completeBy + '\n' +
                '}';
    }


    @Override
    public int compareTo(Orders other) {
        Comparator<Orders> byCompleteBy = Comparator.comparing(Orders::getCompleteBy);
        Comparator<Orders> byOrderValue = Comparator.comparing(Orders::getOrderValue);
        return byCompleteBy.thenComparing(byOrderValue).compare(this, other);
    }


}
