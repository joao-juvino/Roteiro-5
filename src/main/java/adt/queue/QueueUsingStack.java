package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		while (!this.stack1.isEmpty()) {
			try {
				T removedElement = this.stack1.pop();
				this.stack2.push(removedElement);
			} catch (StackUnderflowException e) {
				throw new QueueUnderflowException();
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}

		T headElement;
		
		try {
			headElement = this.stack2.pop();
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}

		while (!this.stack2.isEmpty()) {
			try {
				T removedElement = this.stack2.pop();
				this.stack1.push(removedElement);
			} catch (StackUnderflowException e) {
				throw new QueueUnderflowException();
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}

		return headElement;
	}

	@Override
	public T head() {
		while (!this.stack1.isEmpty()) {
			try {
				T removedElement = this.stack1.pop();
				this.stack2.push(removedElement);
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}

		T headElement = this.stack2.top();

		while (!this.stack2.isEmpty()) {
			try {
				this.stack1.push(this.stack2.pop());
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}

		return headElement;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
