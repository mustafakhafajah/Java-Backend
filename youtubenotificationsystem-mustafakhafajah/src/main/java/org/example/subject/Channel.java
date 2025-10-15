package org.example.subject;

import org.example.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject{
    private final String name;

    public Channel(String name) {
        this.name = name;
    }
    private final List<Observer> subscribers = new ArrayList<>();

    public void uploadVideo(String title){
        // TODO: Notify all subscribers about new video
        this.notifyObservers(this.name +"uploaded Video " + title);
    }

    @Override
    public void subscribe(Observer observer) {
        for (Observer o : subscribers) {
            if(o == observer){
                break;
            }
        }
        observer.update("Subscribed to "+this.name);
        subscribers.add(observer);}


    @Override
    public void unsubscribe(Observer observer) {
        for (Observer o : subscribers) {
            if(o == observer){
                observer.update("Unsubscribed from "+this.name);
                subscribers.remove(observer);
                break;
            }

            }

    }


    @Override
    public void notifyObservers(String message) {
        for (Observer observer : subscribers) {
            observer.update(message);
        }
    }

    public String getName() {
        return name;
    }
}
