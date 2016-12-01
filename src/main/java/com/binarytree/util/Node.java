package com.binarytree.util;

import java.util.Arrays;
import java.util.Objects;

public class Node<T extends Comparable<T>> implements PrintableNode {
	
	public enum Side {LEFT, RIGHT};
	
	private T value;
	private Node<T> left;
	private Node<T> right;
	
	public static <E  extends Comparable<E>> Node<E> of(final E value) {
		return new Node<E>(value);
	}
	
	public Node(final T value) {
		this.value = value;
	}
	
	public boolean add(final Side side, final Node<T> node) {
		
		if (get(side) == null) {
			set(side, node);
			return true;
		}
		
		return false;
	}
	
	public Node<T> get(final Side side) {
		
		if (side == Side.LEFT)
			return getLeft();
		
		if (side == Side.RIGHT)
			return getRight();
		
		return null;
	}

	public void set(final Side side, final Node<T> node) {
		
		if (side == Side.LEFT)
			setLeft(node);
		
		if (side == Side.RIGHT)
			setRight(node);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	@Override
	public String getText() {
		return getValue().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[value=" + value + "]";
	}
	
	@SafeVarargs
	public static <S extends Comparable<S>> Node<S> max(final Node<S> ...nodes) {

		return Arrays.asList(nodes).stream()
							.filter(Objects::nonNull)
							.max((a, b) -> a.getValue().compareTo(b.getValue())).get();
	}
	
	@SafeVarargs
	public static <S extends Comparable<S>> Node<S> min(final Node<S> ...nodes) {

		return Arrays.asList(nodes).stream()
							.filter(Objects::nonNull)
							.max((a, b) -> b.getValue().compareTo(a.getValue())).get();
	}
}