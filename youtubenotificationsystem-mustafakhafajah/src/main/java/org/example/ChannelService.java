package org.example;

import org.example.observer.Observer;
import org.example.subject.Channel;
import org.example.subject.Subject;

import java.util.HashMap;
import java.util.Map;

public class ChannelService {
    private final Map<String, Channel> channels = new HashMap<>();
    public boolean createNewChannel(Channel channel) {
        if (channels.containsValue(channel) || channel.getName() == null) {
            return false;
        }
        else {
            channels.put(channel.getName(), channel);
        }
        return true;
    }
    public boolean deleteChannel(String name){
        if (channels.containsKey(name)) {
            channels.remove(name);
            return true;
        }
        return false;
    }

    public boolean subscribeToChannel(Channel channel, Observer subject) {
        if (channels.containsKey(channel.getName())) {
            channel.subscribe(subject);
            return true;
        }
            return false;

    }
    public boolean unsubscribe(String channelName, Observer user){
        if (channels.containsKey(channelName)){
            channels.get(channelName).unsubscribe(user);
            return true;
        }
        return false;
    }
    public boolean upload(String channelName, String videoTitle){
        if (channels.containsKey(channelName)){
            Channel channel = channels.get(channelName);
            channel.uploadVideo(videoTitle);
            return true;
        }
        return false;
    }

    public void newContentNotification(Channel channel, String message) {
        channel.notifyObservers(message);

    }
}
