package edu.cynanthus.domain;

import java.util.Map;
import java.util.TreeMap;

/**
 * El tipo Instructions.
 */
public class Instructions extends TreeMap<String, Map<String, int[]>> {

    /**
     * Instancia un nuevo Instructions.
     */
    public Instructions() {
        super(String::compareTo);
    }

    /**
     * Select instructions.
     *
     * @param groupName       el group name
     * @param instruccionName el instruccion name
     * @return el instructions
     */
    public Instructions select(String groupName, String instruccionName) {
        if (groupName.equals("*")) {
            if (instruccionName.equals("*")) return this;
            else {
                Instructions selection = new Instructions();
                forEach((name, group) -> {
                    int[] selectedVector = group.get(instruccionName);
                    if (selectedVector != null) {
                        Map<String, int[]> groupContainer = new TreeMap<>(String::compareTo);
                        groupContainer.put(instruccionName, selectedVector);
                        selection.put(name, groupContainer);
                    }
                });
                return !selection.isEmpty() ? selection : null;
            }
        } else {
            Map<String, int[]> selectedGroup = get(groupName);
            if (selectedGroup != null)
                if (instruccionName.equals("*")) {
                    Instructions selection = new Instructions();
                    selection.put(groupName, selectedGroup);
                    return selection;
                } else {
                    int[] selectedVector = selectedGroup.get(instruccionName);
                    if (selectedVector != null) {
                        Instructions selection = new Instructions();
                        Map<String, int[]> groupContainer = new TreeMap<>(String::compareTo);
                        groupContainer.put(instruccionName, selectedVector);
                        selection.put(groupName, groupContainer);
                        return selection;
                    }
                }
        }
        return null;
    }

    /**
     * Put all.
     *
     * @param map el map
     */
    @Override
    public void putAll(Map<? extends String, ? extends Map<String, int[]>> map) {
        map.forEach((groupName, group) -> {
            Map<String, int[]> selectedGroup = get(groupName);
            if (selectedGroup != null) selectedGroup.putAll(group);
            else put(groupName, group);
        });
    }

    /**
     * Delete.
     *
     * @param groupName       el group name
     * @param instruccionName el instruccion name
     */
    public void delete(String groupName, String instruccionName) {
        if (groupName.equals("*")) {
            if (instruccionName.equals("*")) clear();
            else forEach((name, group) -> group.remove(instruccionName));
        } else {
            if (instruccionName.equals("*")) remove(groupName);
            else {
                Map<String, int[]> selectedGroup = get(groupName);
                if (selectedGroup != null) selectedGroup.remove(instruccionName);
            }
        }
    }

}
