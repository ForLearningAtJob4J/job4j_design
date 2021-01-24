package ru.job4j.isp;

import java.util.*;

public class MenuItem implements IPrintable, IMenu, IAction {
    private static final Map<String, Integer> NUMBER_GENERATOR = new HashMap<>();

    private final MenuItem parent;
    private final String key;
    private final List<MenuItem> children = new ArrayList<>();
    private String name;
    private IAction action = () -> System.out.println("Making default action");

    /**
     * Create new menu item
     *
     * @param parent can be null, so it means that the menu item with 'name' parameter will be
     *               on the top of the menu
     * @param name   name of the item
     */
    public MenuItem(MenuItem parent, String name) {
        this.parent = parent;
        this.name = name;
        if (parent != null) {
            String prefix;
            prefix = Objects.requireNonNullElse(parent.key, "");
            int number = NUMBER_GENERATOR.getOrDefault(prefix, 1);
            key = "" + (prefix.isEmpty() ? "" : (prefix + ".")) + number;
            NUMBER_GENERATOR.put(prefix, number + 1);

            parent.children.add(this);
        } else {
            key = "";
        }
    }

    public MenuItem(MenuItem parent, String name, IAction action) {
        this(parent, name);
        setAction(action);
    }

    public MenuItem(String name) {
        this(null, name);
    }

    public MenuItem(String name, IAction action) {
        this(null, name);
        setAction(action);
    }

    public MenuItem() {
        this("ROOT");
    }

    public MenuItem(IAction action) {
        this();
        setAction(action);
    }

    public IAction getAction() {
        return action;
    }

    public MenuItem setAction(IAction action) {
        this.action = action;
        return this;
    }

    @Override
    public IAction getAction(String key) {
        MenuItem found = find(key);
        return found == null ? null : found.action;
    }

    @Override
    public void doAction() {
        if (action != null) {
            action.doAction();
        }
    }

    public MenuItem add(MenuItem parent, String childName) {
        if (parent != null && !"".equals(parent.getKey()) && find(parent.getKey()) == null) {
            return null;
        }
        return new MenuItem(parent, childName);
    }

    public MenuItem add(MenuItem parent, String childName, IAction action) {
        MenuItem item = add(parent, childName);
        item.setAction(action);
        return item;
    }

    public MenuItem add(String childName) {
        return add(this, childName);
    }

    @Override
    public MenuItem add(String childName, IAction action) {
        return add(this, childName, action);
    }

    public void update(String name) {
        this.name = name;
    }

    public void update(String name, IAction action) {
        this.name = name;
        setAction(action);
    }

    public MenuItem removeDeepDownChild(String key) {
        MenuItem found = findDeepDownChild(key);
        if (found != null) {
            if (found.parent != null) {
                found.parent.children.remove(found);
            } else {
                children.remove(found);
            }
        }
        return found;
    }

    @Override
    public void remove(String key) {
        MenuItem found = find(key);
        if (found != null) {
            if (found.parent != null) {
                found.parent.children.remove(found);
            } else {
                children.remove(found);
            }
        }
    }

    public MenuItem findDeepDownChild(String key) {
        MenuItem found = null;
        Queue<MenuItem> queue = new LinkedList<>(children);
        while (!queue.isEmpty()) {
            MenuItem el = queue.poll();
            if (Objects.equals(el.key, key)) {
                found = el;
                break;
            }
            queue.addAll(el.children);
        }
        return found;
    }

    @Override
    public MenuItem find(String key) {
        MenuItem found = null;
        MenuItem root = this;
        while (!"".equals(root.key)) {
            root = root.parent;
            assert root != null;
        }
        Queue<MenuItem> queue = new LinkedList<>(root.children);
        while (!queue.isEmpty()) {
            MenuItem el = queue.poll();
            if (Objects.equals(el.key, key)) {
                found = el;
                break;
            }
            queue.addAll(el.children);
        }
        return found;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public void callPrinter(IPrinter printer) {
        printer.print(makeStringRepresentation(0, children));
    }

    private String makeStringRepresentation(int level, List<MenuItem> list) {
        StringBuilder result = new StringBuilder();
        if (!"".equals(this.key)) {
            result.append(getKey()).append(" ").append(getName())
                    .append(System.lineSeparator());
            level += 1;
        }

        for (MenuItem menuItem : list) {
            result.append("----".repeat(level)).append(menuItem.getKey()).append(" ").append(menuItem.getName())
                    .append(System.lineSeparator());
            if (!menuItem.children.isEmpty()) {
                result.append(makeStringRepresentation(level + 1, menuItem.children));
            }
        }
        return result.toString();
    }
}