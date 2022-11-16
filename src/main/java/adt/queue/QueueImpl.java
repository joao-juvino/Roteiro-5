package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	/**
	 * Método que obtém o elemento mais antigo da fila
	 * 
	 * @return headElement/null O elemento mais antigo da fila ou nulo caso a fila estaja vazia
	 */
	@Override
	public T head() {
		T firstElement = null;
		if(this.tail > -1){
			firstElement = this.array[0];
		}

		return firstElement;
	}

	/**
	 * Método que verifica se a fila está vazia
	 * 
	 * @return true/false Indicando se a fila está vazia ou não
	 */
	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	/**
	 * Método que verifica se a fila está cheia
	 * 
	 * @return true/false Indicando se a fila está cheia ou não
	 */
	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}

	/**
	 * Método que realiza o deslocamento dos elementos 1 posição à esquerda, de modo que o primeiro elemento é sobrescrito
	 * 
	 */
	private void shiftLeft() {
		for (int i = 1; i <= this.tail; i++) {
			this.array[i - 1] = this.array[i];
		}
	}

	/**
	 * Método que adiciona um elemento no final de uma fila.
	 * Caso a fila esteja cheia, uma exceção é lançada
	 * 
	 * @param element O elemento a ser adicionado na fila
	 */
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}

		this.array[++this.tail] = element;
	}

	/**
	 * Método que remove o elemento mais antigo da fila.
	 * Caso a fila esteja vazia, uma exceção é lançada
	 * 
	 * @return removedElement O elemento que foi removido da fila
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T removedElement = this.array[0];
		this.shiftLeft();
		this.tail--;

		return removedElement;
	}

}
