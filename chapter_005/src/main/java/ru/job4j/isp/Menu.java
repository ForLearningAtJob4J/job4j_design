package ru.job4j.isp;

import java.util.*;

public class Menu implements Printable, MenuAPI {
    private MenuItem root;

    public Menu() {
        this.root = new MenuItem(null, "ROOT", MenuItem.DEFAULT_ACTION);
    }

    @Override
    public String add(String parentKey, String childName, Action action) {
        if (parentKey == null) {
            parentKey = "";
        }
        MenuItem parentItem = find(parentKey);
        if (parentItem == null) {
            return null;
        }
        MenuItem childItem = new MenuItem(parentItem, childName, action);
        return childItem.key;
    }

    public String add(String parentKey, String childName) {
        return add(parentKey, childName, MenuItem.DEFAULT_ACTION);
    }

    @Override
    public boolean update(String key, String name, Action action) {
        MenuItem found = find(key);
        if (found == null) {
            return false;
        }

        found.name = name;
        found.action = action;
        return true;
    }

    public boolean update(String key, String name) {
        return update(key, name, MenuItem.DEFAULT_ACTION);
    }

    @Override
    public boolean remove(String key) {
        MenuItem found = find(key);
        if (found == null) {
            return false;
        }

        if (found == root) {
            root = null;
            return true;
        }

        found.parent.children.remove(found);
        return true;
    }

    private MenuItem find(String key) {
        if ("".equals(key)) {
            return root;
        }
        MenuItem found = null;
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

    @Override
    public Action getAction(String key) {
        MenuItem menuItem = find(key);
        return menuItem == null ? null : menuItem.action;
    }

    @Override
    public void callPrinter(Printer printer) {
        printer.print(makeStringRepresentation(0, root.children));
    }

    private String makeStringRepresentation(int level, List<MenuItem> list) {
        StringBuilder result = new StringBuilder();

        for (MenuItem menuItem : list) {
            result.append("----".repeat(level)).append(menuItem.key).append(" ").append(menuItem.name)
                    .append(System.lineSeparator());
            if (!menuItem.children.isEmpty()) {
                result.append(makeStringRepresentation(level + 1, menuItem.children));
            }
        }
        return result.toString();
    }

    private static class MenuItem {
        private static final Map<String, Integer> NUMBER_GENERATOR = new HashMap<>();
        private static final Action DEFAULT_ACTION = () -> System.out.println("Making default action");

        MenuItem parent;
        String key;
        String name;
        List<MenuItem> children = new ArrayList<>();
        Action action;

        public MenuItem(MenuItem parent, String name, Action action) {
            this.parent = parent;
            this.name = name;
            this.action = action;
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
    }
}