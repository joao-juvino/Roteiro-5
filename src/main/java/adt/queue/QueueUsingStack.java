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

	/**
	 * Método que adiciona um elemento no final da pilha, o que equivale a adicionar o elemento na fila.
	 * Caso a pilha esteja cheia, uma exceção correspondente para fila é lançada.
	 * 
	 * @param element O elemento a ser adicionado na pilha
	 */
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	/**
	 * Método que remove o elemento mais antigo da pilha.
	 * Para isso, uma manipulação utilizando a pilha auxiliar é realizada.
	 * Caso a pilha esteja vazia, uma exceção corresponde para a fila é lançada.
	 * 
	 * @return oldestElement O elemento mais antigo da fila, o qual foi removido
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {

		// Os elementos da pilha principal são alocados na pilha auxiliar
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

		T oldestElement;
		
		// O elemento mais antigo, localizado no topo da fila auxiliar, é removido.
		try {
			oldestElement = this.stack2.pop();
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}

		// Os elementos restante da fila auxiliar são alocados de volta na fila principal
		while (!this.stack2.isEmpty()) {
			try {
				T removedElement = this.stack2.pop();
				this.stack1.push(removedElement);
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}

		return oldestElement;
	}

	/**
	 * Método que obtém o elemento mais antigo da pilha principal, equivalente ao elemento mais antigo da fila simulada.
	 * Para isso, uma manipulação utilizando a pilha auxiliar é realizada.
	 * 
	 * @return headElement/null O elemento mais antigo da fila simulada ou null caso a fila estaja vazia
	 */
	@Override
	public T head() {
		// Os elementos da pilha principal são alocados na pilha auxiliar
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

		// O elemento mais antigo, localizado no topo da fila auxiliar, é obtido
		T headElement = this.stack2.top();

		// Todos os elementos são alocados de volta na fila principal
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

	/**
	 * Método que verifica se a fila simulada está cheia, o que equivale a fazer essa verificação para a pilha principal
	 * 
	 * @return true/false indicando se a filha simulada está cheia ou não
	 */
	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	/**
	 * Método que verifica se a fila simulada está vazia, o que equivale a fazer essa verificação para a pilha principal
	 * 
	 * @return true/false indicando se a filha simulada está vazia ou não
	 */
	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
