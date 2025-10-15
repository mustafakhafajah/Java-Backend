package org.example;

import org.example.observer.User;
import org.example.subject.Channel;

public class Main {
    public static void main(String[] args) {
            ChannelService svc = new ChannelService();
            Channel channel = new Channel("TechWorld");
            Channel channel2 = new Channel("FoodiesUnite");

            svc.createNewChannel(channel);
            svc.createNewChannel(channel2);

            User alice = new User("Alice");
            User bob   = new User("Bob");
            User charlie = new User("Charlie");

            svc.subscribeToChannel(channel, alice);
            svc.subscribeToChannel(channel, bob);

            svc.subscribeToChannel(channel2, alice);
            svc.subscribeToChannel(channel2, charlie);

            svc.upload("TechWorld", "Observer Pattern Explained");
            svc.upload("FoodiesUnite", "Best Pasta Recipe");
        }

    }