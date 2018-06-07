package rpn.Observe;

/**
 *  Observable Interface
 *  this interface define contract to observed
 */
public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();

}
