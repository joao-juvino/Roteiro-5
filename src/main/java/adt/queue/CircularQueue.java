package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
	}

	/**
	 * Método que adiciona um elemento no final de uma fila.
	 * Caso a fila esteja cheia, uma exceção é lançada
	 * 
	 * @param element O elemento a ser adicionado na fila
	 */
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()){
			throw new QueueOverflowException();
		}

		// Na primeira operação de queue, o head é incrementado
		if (this.head == -1 && this.tail == -1){
			this.head++;
		}

		// Tail é incrementado circularmente
		tail = (tail + 1) % this.array.length;

		this.array[tail] = element;
	}

	/**
	 * Método que remove o elemento mais antigo da fila.
	 * Caso a fila esteja vazia, uma exceção é lançada
	 * 
	 * @return removedElement O elemento que foi removido da fila
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()){
			throw new QueueUnderflowException();
		}

		T removedElement = this.array[this.head];
		
		// O head é incrementado circularmente
		head = (head + 1) % array.length;

		return removedElement;
	}

	/**
	 * Método que obtém o elemento mais antigo da fila
	 * 
	 * @return headElement/null o elemento mais antigo da fila ou nulo caso a fila estaja vazia
	 */
	@Override
	public T head() {
		if (this.head == -1) return null;

		T headElement = this.array[this.head];

		return headElement;
	}

	/**
	 * Método que verifica se a fila está vazia
	 * 
	 * @return true/false indicando se a fila está vazia ou não
	 */
	@Override
	public boolean isEmpty() {
		return this.head == this.tail;
	}

	/**
	 * Método que verifica se a fila está cheia
	 * 
	 * @return true/false indicando se a fila está cheia ou não
	 */
	@Override
	public boolean isFull() {
		boolean full = false;

		// Caso head > tail, a fila só estará cheia se head estiver no início e tail no fim
		if (this.head < this.tail){
			if (this.head == 0 && this.tail == this.array.length - 1){
				full = true;
			}
		
		}
		// Caso tail > head, a fila só estará cheia se tail + 1 for igual ao head
		else if(this.head > this.tail){
			if (this.tail + 1 == this.head){
				full = true;
			}
		}

		return full;
	}

}
