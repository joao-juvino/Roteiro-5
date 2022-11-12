package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;
	private boolean begin;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
		this.begin = true;
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		// No início, defini o head para zero
		if (this.head == -1) this.head++;
		// Verifica se o tail chegou à última posição
		if (this.tail == this.array.length - 1) {
			if (this.head > 0) {
				this.tail = -1;
				this.array[++this.tail] = element;
			} else {
				throw new QueueOverflowException();
			}
		} else {
			// Casos em que não há espaço
			this.array[++this.tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T head() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
