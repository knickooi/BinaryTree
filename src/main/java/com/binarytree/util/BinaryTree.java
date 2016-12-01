package com.binarytree.util;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.binarytree.util.Node.Side;

public class BinaryTree<T extends Comparable<T>> {
	
	private Node<T> root;
	
	public BinaryTree() {}
	
	public BinaryTree(final List<T> data) {
		setData(data);
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		
		Random random = new Random();
		List<Integer> sample = random.ints(20, 0, 50).boxed().collect(Collectors.toList());
		
		tree.setData(sample);
		
		System.out.println(tree.toString());
		
		System.out.println("\nMax value: " + ((tree.getMax() != null) ? tree.getMax().getValue() : "not found."));
		System.out.println("\nMin value: " + ((tree.getMin() != null) ? tree.getMin().getValue() : "not found."));
		System.out.println(sample.stream().max(Comparator.naturalOrder()).get());
		
		BinaryTree<Integer> second = new BinaryTree<>();
		second.setDataSorted(sample);
		
		System.out.println(second.toString());
	}
	
	public void insertSorted(T t) {
		root = insertSorted(root, t);
	}

	public Node<T> insertSorted(Node<T> node, T t) {
		if (node == null) {
			node = new Node<T>(t); 
		} else {
			if (t.compareTo(node.getValue()) < 0) {
				node.setLeft(insertSorted(node.getLeft(), t));
			} else if (t.compareTo(node.getValue()) > 0) {
				node.setRight(insertSorted(node.getRight(), t));
			}
		}
		
		return node;
	}
	
	public Node<T> getMax() {
		return getMax(root);
	}
	
	public Node<T> getMin() {
		return getMin(root);
	}
	
	public Node<T> getMax(final Node<T> node) {
		
		if (node != null) {
			Node<T> leftNode = node.get(Side.LEFT);
			Node<T> rightNode = node.get(Side.RIGHT);
			
			return Node.max(node, getMax(leftNode), getMax(rightNode));
		} else {
			return null;
		}
	}

	public Node<T> getMin(final Node<T> node) {
		
		if (node != null) {
			Node<T> leftNode = node.get(Side.LEFT);
			Node<T> rightNode = node.get(Side.RIGHT);
			
			return Node.min(node, getMin(leftNode), getMin(rightNode));
		} else {
			return null;
		}
		
	}
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public void setDataSorted(final List<T> data) {
		data.forEach(this::insertSorted);
	}
	
	public void setData(final List<T> data) {
		data.forEach(this::add);
	}

	public void add(@SuppressWarnings("unchecked") final T... values) {
		for (T t : values) {
			add(t);
		}
	}
	
	public void add(final T value) {
		
		Node<T> node = Node.of(value);
		
		if (root == null) {
			root = node;
		} else {
			add(go(), root, node);
		}
	}
	
	private void add(final Side side, final Node<T> parent, final Node<T> node) {

		if (!parent.add(side, node) && !parent.add(go(side), node)) {
			add(go(), parent.get(go()), node);
		}
	}
	
	private Side go() {
		return (nextBoolean() ? Side.LEFT : Side.RIGHT);
	}
	
	private Side go(final Side side) {
		return (side == Side.RIGHT) ? Side.LEFT : Side.RIGHT;
	}
	
	private Boolean nextBoolean() {
		return (new Random().nextBoolean());
	}
	
	@Override
	public String toString() {
//		return (root != null) ? root.toString() : null;
		return TreePrinter.print(this);
	}
}
