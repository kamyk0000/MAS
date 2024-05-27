package Base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class ObjectPlus implements Serializable {
    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();

    public ObjectPlus() {
        List extent = null;
        Class theClass = this.getClass();

        if(allExtents.containsKey(theClass)) {
            // An extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else {
            // An extent does not exist - create a new one
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }

        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allExtents = (Hashtable) stream.readObject();
    }

    public static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if(allExtents.containsKey(type)) {
            return (Iterable<T>) allExtents.get(type);
        }

        throw new ClassNotFoundException(String.format("%s. Stored extents: %s", type.toString(), allExtents.keySet()));
    }

    // do i need this
    public void removeFromExtent() throws Exception {
        List extent = null;
        if(allExtents.containsKey(this.getClass())) {
            // Extent of this class already exist
            allExtents.remove(this.getClass(), this);
        }
        else {
            throw new Exception("Unknown class " + this.getClass());
        }
    }

    // pretty this up
    public static void removeFromExtent(ObjectPlus object) throws Exception {
        if(allExtents.containsKey(object.getClass())) {
            // Extent of this class does exist
            allExtents.get(object.getClass()).remove(object);
            if(allExtents.get(object.getClass()).isEmpty()) {
                allExtents.remove(object.getClass());
            }
        }
        else {
            throw new Exception("Unknown class " + object.getClass());
        }
    }

    public static void showExtent(Class theClass) throws Exception {
        List extent = null;

        if(allExtents.containsKey(theClass)) {
            // Extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else {
            throw new Exception("Unknown class " + theClass);
        }

        System.out.println("Extent of the class: " + theClass.getSimpleName());

        for(Object obj : extent) {
            System.out.println(obj);
        }
    }
}