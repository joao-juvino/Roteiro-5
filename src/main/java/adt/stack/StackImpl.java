package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	/**
	 * Método que obtém o elemento mais recente da pilha.
	 * 
	 * @return topElement/null o elemento mais recente da pilha ou null caso a pilha esteja vazia
	 */
	@Override
	public T top() {
		T topElement = null;
		if (this.top > -1) {
			topElement = array[this.top];
		}

		return topElement;
	}

	/**
	 * Método que verifica se a pilha está vazia.
	 * 
	 * @return true/false indicando se a pilha está vazia ou não
	 */
	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	/**
	 * Método que verifica se a pilha está cheia.
	 * 
	 * @return true/false indicando se a pilha está cheia ou não
	 */
	@Override
	public boolean isFull() {
		return this.top == this.array.length - 1;
	}

	/**
	 * Método que adiciona um elemento a pilha.
	 * Caso a pilha esteja cheia, uma exceção é lançada.
	 * 
	 * @param element o elemento a ser adicionado na pilha
	 */
	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()){
			throw new StackOverflowException();
		}
		
		this.array[++this.top] = element;
	}

	/**
	 * Método que remove o elemento mais recente da pilha.
	 * Caso a pilha esteja vazia, uma exceção é lançada.
	 * 
	 * @return removedElement o elemento mais recente da pilha
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}

		T removedElement = array[this.top--];

		return removedElement;
	}

}
