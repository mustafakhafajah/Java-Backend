package org.example.subject;

import org.example.observer.Observer;

public interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(String message);
    void uploadVideo(String title);
}
