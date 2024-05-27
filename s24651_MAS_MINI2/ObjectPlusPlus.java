import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {

    /** Stores information about all connections of this object. */
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();

    /** Stores information about all parts connected with any objects. */
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    public ObjectPlusPlus() {
        super();
    }

    private void removeAllLinks(String roleName, String reverseRoleName) throws Exception {
        // should convert it to qualifier aswell

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        Collection<ObjectPlusPlus> linkedObjects = links.get(roleName).values();

        //for debug purpose
        System.out.println("Removing links for "+ this.getClass().getSimpleName());

        for(ObjectPlusPlus object : linkedObjects) {
            object.removeLink(reverseRoleName, roleName, object);
        }

        links.remove(roleName);
    }

    private void removeLink(String roleName, String reverseRoleName, Object qualifier, int counter) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        ObjectPlusPlus targetObject;

        // Protection for the reverse connection
        if(counter < 1) {
            return;
        }

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            // No link for the qualifer
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        targetObject = objectLinks.get(qualifier);
        // remove link

        // for debug purposes
        System.out.println("Removing links for " + targetObject.getClass().getSimpleName());

        objectLinks.remove(qualifier);
        // if this was the last link, then remove the role
        if (objectLinks.isEmpty()) { links.remove(roleName); }
        //only issue would be with the links when removing from qualified object perspective, so it must be run after get linked object in these cases
        targetObject.removeLink(reverseRoleName, roleName, this, counter - 1);
    }

    public void removeLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) throws Exception {
        removeLink(roleName, reverseRoleName, targetObject, 2);
    }

    public void removeLinks(String roleName, String reverseRoleName) throws Exception {

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }
        Collection<ObjectPlusPlus> linkedObjects = links.get(roleName).values();

        System.out.println("Removing links for "+ this.getClass().getSimpleName());

        for(ObjectPlusPlus object : linkedObjects) {
            object.removeLink(this, reverseRoleName);
        }

        links.remove(roleName);
    }

    public void removeLink(ObjectPlusPlus target, String reverseRoleName) {
        links.get(reverseRoleName).remove(target);
        if (links.get(reverseRoleName).isEmpty()) links.remove(reverseRoleName);
    }

    //remove whole handling

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;

        // Protection for the reverse connection
        if(counter < 1) {
            return;
        }

        // Find a collection of links for the role
        if(links.containsKey(roleName)) {
            // Get the links
            objectLinks = links.get(roleName);
        }
        else {
            // No links ==> create them
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        // Check if there is already the connection
        // If yes, then ignore the creation
        if(!objectLinks.containsKey(qualifier)) {
            // Add a link for the target object
            objectLinks.put(qualifier, targetObject);

            // Add the reverse connection
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
        // Check if the part exist somewhere
        if(allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObject);

        // Store adding the object as a part
        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        return objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        Collection col = objectLinks.values();

        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for(Object obj : col) {
            stream.println("   " + obj);
        }
    }

    // can i use this? nah this is only for qualified
    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            // No link for the qualifer
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

}