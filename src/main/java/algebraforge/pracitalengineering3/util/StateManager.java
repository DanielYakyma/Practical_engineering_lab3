package algebraforge.pracitalengineering3.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class StateManager<E extends Node & Interactable> {
    private record EventFilterStorage(EventHandler<MouseEvent> onMouseEntered,
                                      EventHandler<MouseEvent> onMouseExited,
                                      EventHandler<MouseEvent> onMousePressed,
                                      EventHandler<MouseEvent> onMouseReleased) {

    }

    private final EventFilterStorage storage;

    private final E node;


    public StateManager(E node) {
        this.node = node;

        storage = createEventFilterStorage(node);
    }

    public void removeInteraction() {
        removeInteraction(node, storage);
    }

    public void attachInteraction() {
        attachInteraction(node, storage);
    }


    private static <E extends Node & Interactable> EventFilterStorage createEventFilterStorage(E node) {
        return new EventFilterStorage(
                createOnMouseEntered(node),
                createOnMouseExited(node),
                createOnMousePressed(node),
                createOnMouseReleased(node));
    }


    private static <E extends Node & Interactable> EventHandler<MouseEvent> createOnMouseEntered(E node) {
        return _ -> {
            if (node.isPressed())
                return;

            node.setHoverState();
        };
    }

    private static <E extends Node & Interactable> EventHandler<MouseEvent> createOnMouseExited(E node) {
        return _ -> {
            if (node.isPressed())
                return;

            node.setDefaultState();
        };
    }

    private static <E extends Node & Interactable> EventHandler<MouseEvent> createOnMousePressed(E node) {
        return _ -> node.setActiveState();
    }

    private static <E extends Node & Interactable> EventHandler<MouseEvent> createOnMouseReleased(E node) {
        return _ -> {
            if (node.isHover())
                node.setHoverState();
            else {
                node.setDefaultState();
            }
        };
    }

    private static <E extends Node & Interactable> void removeInteraction(E node, EventFilterStorage storage) {
        node.removeEventFilter(MouseEvent.MOUSE_ENTERED, storage.onMouseEntered);
        node.removeEventFilter(MouseEvent.MOUSE_EXITED, storage.onMouseExited);
        node.removeEventFilter(MouseEvent.MOUSE_PRESSED, storage.onMousePressed);
        node.removeEventFilter(MouseEvent.MOUSE_RELEASED, storage.onMouseReleased);
    }

    private static <E extends Node & Interactable> void attachInteraction(E node, EventFilterStorage storage) {
        node.addEventFilter(MouseEvent.MOUSE_ENTERED, storage.onMouseEntered);
        node.addEventFilter(MouseEvent.MOUSE_EXITED, storage.onMouseExited);
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, storage.onMousePressed);
        node.addEventFilter(MouseEvent.MOUSE_RELEASED, storage.onMouseReleased);
    }


    public static <E extends Node & Interactable> void attachInteraction(E node) {
        attachInteraction(node, createEventFilterStorage(node));
    }

    public static <E extends Node & Interactable> void update(E node) {
        if (node.isPressed()) {
            node.setActiveState();
            return;
        }

        if (node.isHover()) {
            node.setHoverState();
            return;
        }

        node.setDefaultState();
    }
}
