package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {

        List<Orders> ordersList = new ArrayList<>();
        List<OrdersAssignment> ordAssign = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {
            // Parse the data from JSONs
            JSONArray orders = (JSONArray) parser.parse(new FileReader("orders.json"));
            for (Object orderObj : orders) {
                JSONObject order = (JSONObject) orderObj;
                String orderId = (String) order.get("orderId");
                String orderValue = (String) order.get("orderValue");
                String pickingTime = (String) order.get("pickingTime");
                long minutes = Duration.parse(pickingTime).toMinutes();
                String completeBy = (String) order.get("completeBy");
                LocalTime time = LocalTime.parse(completeBy);
                LocalTime newTime = time.minusMinutes(minutes);
                String newTimeString = newTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                ordersList.add(new Orders(orderId, orderValue, minutes, newTimeString));
            }

            JSONObject store = (JSONObject) parser.parse(new FileReader("store.json"));
            JSONArray pickers = (JSONArray) store.get("pickers");
            String pickingStartTime = (String) store.get("pickingStartTime");
            String pickingEndTime = (String) store.get("pickingEndTime");

            // Sort the orders by pick time
            Collections.sort(ordersList);

            // Assign orders to pickers
            int numPickers = pickers.size();
            List<String> timeSpent = new ArrayList<>(Collections.nCopies(numPickers, pickingStartTime));
            for (Orders ord : ordersList) {
                String ordId = ord.getOrderId();
                if (pickers.size() == 1) {
                    ordAssign.add(new OrdersAssignment("P1", ordId, pickingStartTime));
                    break;
                }
                long pickTime = ord.getPickingTime();

                int index = timeSpent.indexOf(Collections.min(timeSpent));
                LocalTime newTime = LocalTime.parse(timeSpent.get(index)).plusMinutes(pickTime);
                // Check if the new time is before pickingEndTime
                if (newTime.isAfter(LocalTime.parse(pickingEndTime))) {
                    break;
                } else {
                    ordAssign.add(new OrdersAssignment("P" + (index + 1), ordId, timeSpent.get(index)));
                    timeSpent.set(index, newTime.toString());
                }
            }

            // Print the orders assignment
            for (OrdersAssignment oa : ordAssign) {
                System.out.println(oa);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}

