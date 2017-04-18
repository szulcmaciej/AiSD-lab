package com.asid.foundation.datastructure.symbolTable;

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.datastructure.list.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lenovo on 2017-04-09.
 */
public class BinarySearchTreeST <K extends Comparable, V> extends AbstractSymbolTable {

    private Node root = null;

    @Override
    public int size() {
        if (root != null) {
            return root.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return root.containsKey((K) key);
    }

    @Override
    public Object get(Object key) {
        Node node = root.getNode((K) key);
        if(node != null){
            return root.getNode((K) key).getValue();
        }
        else {
            return null;
        }
    }

    @Override
    public Object put(Comparable key, Object value) {
        if(root == null){
            root = new Node((K) key, (V) value);
            return null;
        }
        else {
            return root.put((K) key, (V) value);
        }
    }

    @Override
    public Object remove(Object key) {
        if (!isEmpty()) {
            return root.remove((K) key).getValue();
        }
        else {
            return null;
        }
    }

    @Override
    public Set keySet() {
        return root.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        return put((Comparable) key, value);
    }

    public void balanceTree(){
        List<Node> list = new CustomArrayList<>(size());
        root.addNodesToListAscending(list);
        for(Node node : list){
            node.setLeftSubtree(null);
            node.setRightSubtree(null);
        }

        Node newRoot = treeFromList(list, 0, list.size() - 1);
        root = newRoot;
    }

    public List<DrawableItem> getDrawableItemsList(List<DrawableItem> list, int begX, int begY, int offsetX, int offsetY, int textHeight){
        root.addDrawableItemsToList(list, begX, begY, offsetX, offsetY, textHeight);
        return list;
    }

    private Node treeFromList(List<Node> list, int beg, int end){
        if(beg < end) {
            /*if (beg == 0) {
                Node newNode = list.get((end + beg) / 2 + 1);
                newNode.setLeftSubtree(treeFromList(list, beg, (end + beg) / 2));
                newNode.setRightSubtree(treeFromList(list, (end + beg) / 2 + 2, end));
                return newNode;
            }
            else {
                Node newNode = list.get((end + beg) / 2);
                newNode.setLeftSubtree(treeFromList(list, beg, (end + beg) / 2 - 1));
                newNode.setRightSubtree(treeFromList(list, (end + beg) / 2 + 1, end));
                return newNode;
            }*/

            //to chyba może nie zadziałać
            Node newNode = list.get((end + beg) / 2);
            newNode.setLeftSubtree(treeFromList(list, beg, (end + beg) / 2 - 1));
            newNode.setRightSubtree(treeFromList(list, (end + beg) / 2 + 1, end));
            return newNode;
        }
        else if(beg == end){
            return list.get(beg);
        }
        else return null;
    }

    private class Node{
        private K key;
        private V value;
        private Node leftSubtree;
        private Node rightSubtree;

        public K getKey() {
            return key;
        }
        public void setKey(K key) {
            this.key = key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public Node getLeftSubtree() {
            return leftSubtree;
        }
        public void setLeftSubtree(Node leftSubtree) {
            this.leftSubtree = leftSubtree;
        }
        public Node getRightSubtree() {
            return rightSubtree;
        }
        public void setRightSubtree(Node rightSubtree) {
            this.rightSubtree = rightSubtree;
        }
        public Node(K key, V value, Node leftSubtree, Node rightSubtree) {

            this.key = key;
            this.value = value;
            this.leftSubtree = leftSubtree;
            this.rightSubtree = rightSubtree;
        }
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int size(){
            if(leftSubtree == null && rightSubtree == null){
                return 1;
            }
            else if(leftSubtree == null){
                return 1 + rightSubtree.size();
            }
            else if(rightSubtree == null){
                return 1 + leftSubtree.size();
            }
            else {
                return 1 + leftSubtree.size() + rightSubtree.size();
            }
        }
        public boolean containsKey(K key){
            if(key.compareTo(this.getKey()) == 0){
                return true;
            }
            else if(key.compareTo(this.getKey()) < 0 && leftSubtree != null){
                return leftSubtree.containsKey(key);
            }
            else if(key.compareTo(this.getKey()) > 0 && rightSubtree != null){
                return rightSubtree.containsKey(key);
            }
            else {
                return false;
            }
        }
        public Node getNode(K key){
            if(key.compareTo(this.getKey()) == 0){
                return this;
            }
            else if(key.compareTo(this.getKey()) < 0 && leftSubtree != null){
                return leftSubtree.getNode(key);
            }
            else if(key.compareTo(this.getKey()) > 0 && rightSubtree != null){
                return rightSubtree.getNode(key);
            }
            else {
                return null;
            }
        }
        public Object put(K key, V value){
            if(key.compareTo(this.getKey()) == 0){
                V oldValue = getValue();
                setValue(value);
                return oldValue;
            }
            else if(key.compareTo(this.getKey()) < 0 && leftSubtree != null){
                return leftSubtree.put(key, value);
            }
            else if(key.compareTo(this.getKey()) > 0 && rightSubtree != null){
                return rightSubtree.put(key, value);
            }
            else if(key.compareTo(this.getKey()) < 0 && leftSubtree == null){
                Node newNode = new Node(key, value);
                setLeftSubtree(newNode);
                return null;
            }
            else if(key.compareTo(this.getKey()) > 0 && rightSubtree == null){
                Node newNode = new Node(key, value);
                setRightSubtree(newNode);
                return null;
            }
            else {
                System.out.println("Something went veeery wrong here!");
                return null;
            }
        }
        public Node remove(Node nodeToRemove){
            //nodeToRemove = root.getNode(key);
            Node parent, temp;
            parent = root.getFatherOfNode(nodeToRemove);

            if(nodeToRemove.rightSubtree != null && nodeToRemove.leftSubtree != null){
                temp = remove(succ(nodeToRemove));
                temp.setLeftSubtree(nodeToRemove.leftSubtree);
                temp.setRightSubtree(nodeToRemove.rightSubtree);
            }
            else {
                temp = (nodeToRemove.rightSubtree != null ? nodeToRemove.rightSubtree : nodeToRemove.leftSubtree);
            }

            if(root == nodeToRemove){
                root = temp;
            }
            else if(parent.leftSubtree == nodeToRemove){
                parent.leftSubtree = temp;
            }
            else {
                parent.rightSubtree = temp;
            }

            return nodeToRemove;

        }
        public Node remove(K key){
            return remove(getNode(key));
        }
        public Node getFatherOfNode(K key){
            if(key.compareTo(root.getKey()) == 0){
                return null;
            }
            else{
                if(key.compareTo(this.getKey()) < 0 && leftSubtree != null){
                    if(key.compareTo(leftSubtree.getKey()) == 0){
                        return leftSubtree;
                    }
                    else{
                        return leftSubtree.getFatherOfNode(key);
                    }
                }
                else if(key.compareTo(this.getKey()) > 0 && rightSubtree != null){
                    if(key.compareTo(rightSubtree.getKey()) == 0){
                        return rightSubtree;
                    }
                    else {
                        return rightSubtree.getFatherOfNode(key);
                    }
                }
                else {
                    return null;
                }
            }
        }
        public Node getFatherOfNode(Node node){
            return getFatherOfNode(node.key);
        }
        public Node succ(Node node){
            if(node.rightSubtree != null){
                return node.rightSubtree.minNode();
            }
            else{
                Node tmp;
                do{
                    tmp = node;
                    node = getFatherOfNode(node);
                }while (node != null && node.leftSubtree != tmp);

                return node;
            }
        }
        public Node minNode(){
            if(leftSubtree == null){
                return this;
            }
            else {
                return leftSubtree.minNode();
            }
        }
        public Set<K> keySet(){
            Set<K> keySet = new HashSet<>();
            addSubtreeToKeySet(keySet);
            return keySet;
        }
        private void addSubtreeToKeySet(Set<K> keySet){
            if(leftSubtree != null){
                leftSubtree.addSubtreeToKeySet(keySet);
            }
            keySet.add(key);
            if(rightSubtree != null){
                rightSubtree.addSubtreeToKeySet(keySet);
            }
        }
        public void addNodesToListAscending(List<Node> list){
            if(leftSubtree != null){
                leftSubtree.addNodesToListAscending(list);
            }
            list.add(this);
            if(rightSubtree != null){
                rightSubtree.addNodesToListAscending(list);
            }
        }
        public List<DrawableItem> addDrawableItemsToList(List<DrawableItem> list, int begX, int begY, int offsetX, int offsetY, int textHeight){
            String textToDraw = key.toString();
            list.add(new DrawableString(begX, begY, textToDraw));
            if(leftSubtree != null){
                list.add(new DrawableLine(begX, begY + textHeight / 5, begX - offsetX, begY + offsetY - textHeight));
                leftSubtree.addDrawableItemsToList(list, begX - offsetX, begY + offsetY, offsetX / 2, offsetY, textHeight);
            }
            if(rightSubtree != null){
                list.add(new DrawableLine(begX, begY + textHeight / 5, begX + offsetX, begY + offsetY - textHeight));
                rightSubtree.addDrawableItemsToList(list, begX + offsetX, begY + offsetY, offsetX / 2, offsetY, textHeight);
            }

            return list;
        }

    }

    public abstract class DrawableItem{
        int xOrigin;

        public int getxOrigin() {
            return xOrigin;
        }

        public void setxOrigin(int xOrigin) {
            this.xOrigin = xOrigin;
        }

        public int getyOrigin() {
            return yOrigin;
        }

        public void setyOrigin(int yOrigin) {
            this.yOrigin = yOrigin;
        }

        int yOrigin;
    }
    public class DrawableString extends DrawableItem{
        String string;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public DrawableString(int xOrigin, int yOrigin, String string) {
            this.xOrigin = xOrigin;
            this.yOrigin = yOrigin;
            this.string = string;
        }
    }
    public class DrawableLine extends DrawableItem{
        int xEnd;
        int yEnd;

        public int getxEnd() {
            return xEnd;
        }

        public void setxEnd(int xEnd) {
            this.xEnd = xEnd;
        }

        public int getyEnd() {
            return yEnd;
        }

        public void setyEnd(int yEnd) {
            this.yEnd = yEnd;
        }

        public DrawableLine(int xOrigin, int yOrigin, int xEnd, int yEnd) {
            this.xOrigin = xOrigin;
            this.yOrigin = yOrigin;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }
    }
}
